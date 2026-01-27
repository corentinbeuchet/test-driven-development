package ort._olen.course.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import ort._olen.course.configuration.IntegrationTest;
import ort._olen.course.model.dto.StudentDTO;
import ort._olen.course.model.dto.StudentSaveDTO;
import ort._olen.course.model.dto.StudentsDTO;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static ort._olen.course.fixtures.StudentFixture.firstStudent;
import static ort._olen.course.fixtures.StudentFixture.secondStudent;

@IntegrationTest
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"classpath:datasets/repository/student-before.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:datasets/repository/student-after.sql"}),
})
class StudentServiceIT {

    @Autowired
    private StudentService studentService;

    @Test
    void shouldFindAllInitialStudents() {
        StudentsDTO students = studentService.findAll();
        assertThat(students.students())
                .contains(
                        firstStudent(),
                        secondStudent()
                );
    }

    @Test
    void shouldFindStudentById() {
        Optional<StudentDTO> student = studentService.findById(1L);

        assertThat(student).isPresent();
        assertThat(student.get()).isEqualTo(firstStudent());
    }

    @Test
    void shouldReturnEmptyWhenFindingNonExistentStudent() {
        Optional<StudentDTO> student = studentService.findById(999L);
        assertThat(student).isEmpty();
    }

    @Test
    void shouldSaveNewStudent() {
        StudentSaveDTO toSave = new StudentSaveDTO("Marie", "Curie", LocalDate.of(LocalDate.now().getYear() - 25, 1, 1));

        StudentDTO saved = studentService.save(toSave);

        assertThat(saved.id()).isNotNull();
        assertThat(saved.name()).isEqualTo("Marie");
        assertThat(saved.surname()).isEqualTo("Curie");
        assertThat(saved.age()).isEqualTo(25);

        assertThat(studentService.findById(saved.id())).isPresent();
    }

    @Test
    void shouldUpdateExistingStudent() {
        StudentSaveDTO updateDetails = new StudentSaveDTO("UpdatedName", "UpdatedSurname", LocalDate.of(LocalDate.now().getYear() - 10, 1, 1));

        Optional<StudentDTO> updated = studentService.update(1L, updateDetails);

        assertThat(updated).isPresent();
        assertThat(updated.get().name()).isEqualTo("UpdatedName");
        assertThat(updated.get().surname()).isEqualTo("UpdatedSurname");
        assertThat(updated.get().age()).isEqualTo(10);

        assertThat(studentService.findById(1L).get().name()).isEqualTo("UpdatedName");
    }

    @Test
    void shouldDeleteStudent() {
        studentService.deleteById(2L);

        assertThat(studentService.findById(2L)).isEmpty();
    }
}