package ort._olen.course.model.mapper;

import ort._olen.course.model.Student;
import ort._olen.course.model.dto.StudentDTO;
import ort._olen.course.model.dto.StudentSaveDTO;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentMapper {

    private StudentMapper() {
    }

    public static StudentDTO toDTO(Student student) {
        return new StudentDTO(student.getId(), student.getName(), student.getSurname());
    }

    public static Set<StudentDTO> toDTOs(Collection<Student> students) {
        return students.stream().map(StudentMapper::toDTO).collect(Collectors.toSet());
    }

    public static Student toEntity(StudentSaveDTO studentDTO) {
        return Student.of(null, studentDTO.name(), studentDTO.surname());
    }
}
