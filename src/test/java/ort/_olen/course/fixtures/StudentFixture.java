package ort._olen.course.fixtures;

import ort._olen.course.model.dto.StudentDTO;

import java.time.LocalDate;

public class StudentFixture {
    private StudentFixture() {
    }

    public static StudentDTO firstStudent() {
        return new StudentDTO(1L, "Test", "Student", LocalDate.of(2001, 1, 26));
    }

    public static StudentDTO secondStudent() {
        return new StudentDTO(2L, "Test2", "Student2", LocalDate.of(2003, 6, 12));
    }
}
