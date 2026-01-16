package ort._olen.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ort._olen.course.model.dto.StudentDTO;
import ort._olen.course.model.dto.StudentSaveDTO;
import ort._olen.course.service.StudentService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Set<StudentDTO>> getStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentSaveDTO student) {
        return studentService.save(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentSaveDTO studentDetails) {
        return studentService.update(id, studentDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        return studentService.findById(id)
                .map(student -> {
                    studentService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
