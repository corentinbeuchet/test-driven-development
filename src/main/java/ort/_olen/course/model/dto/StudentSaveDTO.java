package ort._olen.course.model.dto;

import java.time.LocalDate;

public record StudentSaveDTO(String name, String surname, LocalDate birthdate) {
}
