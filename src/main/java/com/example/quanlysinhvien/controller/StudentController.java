package com.example.quanlysinhvien.controller;

import com.example.quanlysinhvien.dto.ApiResponse;
import com.example.quanlysinhvien.dto.StudentRequest;
import com.example.quanlysinhvien.dto.StudentResponse;
import com.example.quanlysinhvien.entity.StudentStatus;
import com.example.quanlysinhvien.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public ApiResponse<List<StudentResponse>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @PostMapping
    public ApiResponse<StudentResponse> create(@Valid @RequestBody StudentRequest req) {
        return ApiResponse.success(service.create(req));
    }

    @PutMapping("/{id}")
    public ApiResponse<StudentResponse> update(@PathVariable Long id, @Valid @RequestBody StudentRequest req) {
        return ApiResponse.success(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.success("Xóa thành công sinh viên có ID: " + id);
    }

    @GetMapping("/search")
    public ApiResponse<List<StudentResponse>> search(@RequestParam @NotBlank String keyword) {
        return ApiResponse.success(service.search(keyword));
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<StudentResponse> changeStatus(@PathVariable Long id, @RequestParam StudentStatus status) {
        return ApiResponse.success(service.changeStatus(id, status));
    }

    @GetMapping("/paging")
    public ApiResponse<Page<StudentResponse>> pageSort(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) StudentStatus status,
            @RequestParam(required = false) String sort) {
        return ApiResponse.success(service.pageSort(page, size, status, sort));
    }
}