package ort._olen.course.model.dto;

public record StudentDTO(Long id, String name, String surname) implements Comparable<StudentDTO> {

    @Override
    public int compareTo(StudentDTO o) {
        return Long.compare(this.id, o.id);
    }
}
