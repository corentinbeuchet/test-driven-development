package ort._olen.course.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ort._olen.course.model.Student;

@Repository
public interface StudentEntityRepository extends JpaRepository<Student, Long> {
}