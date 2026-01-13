package com.example.quanlysinhvien.service;

import com.example.quanlysinhvien.dto.StudentRequest;
import com.example.quanlysinhvien.dto.StudentResponse;
import com.example.quanlysinhvien.entity.Student;
import com.example.quanlysinhvien.entity.StudentStatus;
import com.example.quanlysinhvien.exception.DuplicateResourceException;
import com.example.quanlysinhvien.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface StudentService {
    StudentResponse create(StudentRequest req);
    StudentResponse update(Long id, StudentRequest req);
    void delete(Long id);
    List<StudentResponse> getAll();
    List<StudentResponse> search(String keyword);
    StudentResponse changeStatus(Long id, StudentStatus status);
    List<StudentResponse> getByStatus(StudentStatus status);
    Page<StudentResponse> pageSort(int page, int size, StudentStatus status, String sort);
}
