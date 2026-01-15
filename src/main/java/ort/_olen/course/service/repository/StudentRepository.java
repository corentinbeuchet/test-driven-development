package ort._olen.course.service.repository;

import ort._olen.course.model.dto.StudentDTO;

import java.util.Optional;
import java.util.Set;

public interface StudentRepository {

    Set<StudentDTO> findAll();

    Optional<StudentDTO> findById(Long id);

    StudentDTO save(StudentDTO student);

    Optional<StudentDTO> update(Long id, StudentDTO studentDetails);

    void deleteById(Long id);
}
