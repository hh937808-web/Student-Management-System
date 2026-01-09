package com.example.quanlysinhvien.controller;

import com.example.quanlysinhvien.dto.StudentRequest;
import com.example.quanlysinhvien.dto.StudentResponse;
import com.example.quanlysinhvien.entity.StudentStatus;
import com.example.quanlysinhvien.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {
    @Autowired
    private StudentService service;


    @GetMapping
    public List<StudentResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    public StudentResponse create(@Valid @RequestBody StudentRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id,
                                  @Valid @RequestBody StudentRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Xóa thành công");
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam @NotBlank String keyword) {
        return ResponseEntity.ok(service.search(keyword));
    }


    @PatchMapping("/{id}/status")
    public StudentResponse changeStatus(
            @PathVariable Long id,
            @RequestParam StudentStatus status
    ) {
        return service.changeStatus(id, status);
    }


    @GetMapping("/status/{status}")
    public List<StudentResponse> getByStatus(@PathVariable StudentStatus status) {
        return service.getByStatus(status);
    }

}
