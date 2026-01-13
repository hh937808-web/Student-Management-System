package com.example.quanlysinhvien.service;

import com.example.quanlysinhvien.dto.StudentRequest;
import com.example.quanlysinhvien.dto.StudentResponse;
import com.example.quanlysinhvien.entity.Student;
import com.example.quanlysinhvien.entity.StudentStatus;
import com.example.quanlysinhvien.exception.DuplicateResourceException;
import com.example.quanlysinhvien.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public List<StudentResponse> getAll() {
        return repository.findAll().stream()
                .map(StudentResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse create(StudentRequest req) {
        if (repository.existsByMaSV(req.getMaSV())) {
            throw new DuplicateResourceException("Mã sinh viên " + req.getMaSV() + " đã tồn tại!");
        }
        Student s = new Student();
        s.setMaSV(req.getMaSV());
        s.setTenSV(req.getTenSV());
        s.setNgaySinh(req.getNgaySinh());
        s.setEmail(req.getEmail());
        s.setStatus(StudentStatus.ACTIVE);
        return new StudentResponse(repository.save(s));
    }

    @Override
    public StudentResponse update(Long id, StudentRequest req) {
        Student s = repository.findById(id)
                .orElseThrow(() -> new DuplicateResourceException("Không tìm thấy sinh viên ID: " + id));

        if (!s.getMaSV().equals(req.getMaSV()) && repository.existsByMaSV(req.getMaSV())) {
            throw new DuplicateResourceException("Mã sinh viên mới đã tồn tại trên hệ thống!");
        }

        s.setMaSV(req.getMaSV());
        s.setTenSV(req.getTenSV());
        s.setNgaySinh(req.getNgaySinh());
        s.setEmail(req.getEmail());
        return new StudentResponse(repository.save(s));
    }

    @Override
    public void delete(Long id) {
        Student s = repository.findById(id)
                .orElseThrow(() -> new DuplicateResourceException("Không tìm thấy sinh viên"));
        s.setStatus(StudentStatus.INACTIVE);
        repository.save(s);
    }

    @Override
    public List<StudentResponse> search(String keyword) {
        return repository.findByMaSVContainingOrTenSVContaining(keyword, keyword)
                .stream()
                .map(StudentResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse changeStatus(Long id, StudentStatus status) {
        Student s = repository.findById(id)
                .orElseThrow(() -> new DuplicateResourceException("Không tìm thấy sinh viên"));
        s.setStatus(status);
        return new StudentResponse(repository.save(s));
    }

    @Override
    public List<StudentResponse> getByStatus(StudentStatus status) {
        return repository.findByStatus(status).stream()
                .map(StudentResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Page<StudentResponse> pageSort(int page, int size, StudentStatus status, String sort) {
        Sort sortObj = (sort != null && sort.equalsIgnoreCase("desc"))
                ? Sort.by("tenSV").descending() : Sort.by("tenSV").ascending();

        Pageable pageable = PageRequest.of(page, size, sortObj);

        if (status != null) {
            return repository.findByStatus(status, pageable).map(StudentResponse::new);
        }
        return repository.findAll(pageable).map(StudentResponse::new);
    }

}