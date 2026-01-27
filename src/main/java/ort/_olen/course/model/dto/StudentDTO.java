package ort._olen.course.model.dto;

import java.time.LocalDate;
import java.time.Period;

public record StudentDTO(Long id, String name, String surname, LocalDate birthdate)
        implements Comparable<StudentDTO> {

    @Override
    public int compareTo(StudentDTO o) {
        return Long.compare(this.id, o.id);
    }

    public int age() {
        if (birthdate == null) return 0;
        LocalDate today = LocalDate.now();
        if (birthdate.isAfter(today)) return 0;
        return Period.between(birthdate, today).getYears();
    }
}