package ort._olen.course.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ort._olen.course.jpa.entity.Student;

@Repository
public interface StudentEntityRepository extends JpaRepository<Student, Long> {
}