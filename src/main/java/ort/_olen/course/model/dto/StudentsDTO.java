package ort._olen.course.model.dto;

import java.util.Collection;

public record StudentsDTO(Collection<StudentDTO> students) {

    @Override
    public Collection<StudentDTO> students() {
        return students.stream().sorted().toList();
    }
}
