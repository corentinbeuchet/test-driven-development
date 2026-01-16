package ort._olen.course.model.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudentsDTOTest {

    @Test
    void studentsShouldBeSortedById() {
        StudentDTO s1 = new StudentDTO(1L, "A", "A");
        StudentDTO s2 = new StudentDTO(2L, "B", "B");
        StudentDTO s3 = new StudentDTO(3L, "C", "C");

        StudentsDTO studentsDTO = new StudentsDTO(List.of(s3, s1, s2));

        var result = studentsDTO.students();

        assertThat(result).containsExactly(s1, s2, s3);
    }
}