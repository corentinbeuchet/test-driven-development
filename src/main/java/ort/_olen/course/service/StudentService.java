package ort._olen.course.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ort._olen.course.jpa.repository.StudentEntityRepository;
import ort._olen.course.jpa.entity.Student;
import ort._olen.course.model.dto.StudentDTO;
import ort._olen.course.model.dto.StudentSaveDTO;
import ort._olen.course.model.dto.StudentsDTO;
import ort._olen.course.model.mapper.StudentMapper;
import ort._olen.course.service.repository.StudentRepository;

import java.util.Optional;

import static ort._olen.course.model.mapper.StudentMapper.toDTOs;

@Service
@Transactional
public class StudentService implements StudentRepository {

    private final StudentEntityRepository studentEntityRepository;

    public StudentService(StudentEntityRepository studentEntityRepository) {
        this.studentEntityRepository = studentEntityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentsDTO findAll() {
        return toDTOs(studentEntityRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StudentDTO> findById(Long id) {
        return studentEntityRepository.findById(id).map(StudentMapper::toDTO);
    }

    @Override
    public StudentDTO save(StudentSaveDTO studentDTO) {
        Student entity = StudentMapper.toEntity(studentDTO);
        return StudentMapper.toDTO(studentEntityRepository.save(entity));
    }

    @Override
    public Optional<StudentDTO> update(Long id, StudentSaveDTO studentDetails) {
        return studentEntityRepository.findById(id)
                .map(existingEntity -> {
                    existingEntity.setName(studentDetails.name());
                    existingEntity.setSurname(studentDetails.surname());
                    return StudentMapper.toDTO(studentEntityRepository.save(existingEntity));
                });
    }

    @Override
    public void deleteById(Long id) {
        studentEntityRepository.deleteById(id);
    }
}
