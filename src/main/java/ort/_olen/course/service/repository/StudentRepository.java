package ort._olen.course.service.repository;

import ort._olen.course.model.dto.StudentDTO;
import ort._olen.course.model.dto.StudentSaveDTO;
import ort._olen.course.model.dto.StudentsDTO;

import java.util.Optional;
import java.util.Set;

public interface StudentRepository {

    StudentsDTO findAll();

    Optional<StudentDTO> findById(Long id);

    StudentDTO save(StudentSaveDTO student);

    Optional<StudentDTO> update(Long id, StudentSaveDTO studentDetails);

    void deleteById(Long id);
}
