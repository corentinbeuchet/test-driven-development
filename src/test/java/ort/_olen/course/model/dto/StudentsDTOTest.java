package ort._olen.course.model.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ort._olen.course.fixtures.StudentFixture.firstStudent;

class StudentsDTOTest {

    @Test
    void studentsShouldBeSortedById() {
        StudentDTO s1 = new StudentDTO(1L, "A", "A", LocalDate.of(2000, 1, 1));
        StudentDTO s2 = new StudentDTO(2L, "B", "B", LocalDate.of(2000, 1, 1));
        StudentDTO s3 = new StudentDTO(3L, "C", "C", LocalDate.of(2000, 1, 1));

        StudentsDTO studentsDTO = new StudentsDTO(List.of(s3, s1, s2));

        var result = studentsDTO.students();

        assertThat(result).containsExactly(s1, s2, s3);
    }

    @Test
    void studentAgeShouldBeDeterminedByHisBirthdate() {
        int expectedAged = 25;
        StudentDTO student = new StudentDTO(1L, "Marie", "Curie", LocalDate.of(LocalDate.now().getYear() - expectedAged, 1, 1));

        assertThat(student.age()).isEqualTo(expectedAged);
    }
}