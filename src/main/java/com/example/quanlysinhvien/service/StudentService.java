package com.example.quanlysinhvien.service;

import com.example.quanlysinhvien.dto.StudentRequest;
import com.example.quanlysinhvien.dto.StudentResponse;
import com.example.quanlysinhvien.entity.Student;
import com.example.quanlysinhvien.entity.StudentStatus;
import com.example.quanlysinhvien.exception.DuplicateResourceException;
import com.example.quanlysinhvien.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    private SinhVienRepository repository;

    public StudentResponse create(StudentRequest req) {

        if (repository.existsByMaSV(req.getMaSV())) {
            throw new DuplicateResourceException("Mã sinh viên đã tồn tại");
        }

        Student s = new Student();
        s.setMaSV(req.getMaSV());
        s.setTenSV(req.getTenSV());
        s.setNgaySinh(req.getNgaySinh());
        s.setEmail(req.getEmail());
        s.setStatus(StudentStatus.valueOf("ACTIVE"));

        return new StudentResponse(repository.save(s));
    }

    public StudentResponse update(Long id, StudentRequest req) {

        Student s = repository.findById(id)
                .orElseThrow(() -> new DuplicateResourceException("Không tìm thấy sinh viên"));

        if (!s.getMaSV().equals(req.getMaSV())
                && repository.existsByMaSV(req.getMaSV())) {
            throw new DuplicateResourceException("Mã sinh viên đã tồn tại");
        }

        s.setMaSV(req.getMaSV());
        s.setTenSV(req.getTenSV());
        s.setNgaySinh(req.getNgaySinh());
        s.setEmail(req.getEmail());

        return new StudentResponse(repository.save(s));
    }

    public void delete(Long id) {
        Student s = repository.findById(id)
                .orElseThrow(() -> new DuplicateResourceException("Không tìm thấy sinh viên"));
        s.setStatus(StudentStatus.valueOf("INACTIVE"));
        repository.save(s);
        ;
    }

    public List<StudentResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(StudentResponse::new)
                .toList();
    }


    public Object search(String keyword) {

        List<StudentResponse> result = repository
                .findByMaSVContainingOrTenSVContaining(keyword, keyword)
                .stream()
                .map(StudentResponse::new)
                .toList();

        if (!result.isEmpty()) {
            return result;
        }
        if (keyword.toUpperCase().startsWith("SV")) {
            return Map.of("message", "Mã sinh viên không tồn tại");
        } else {
            return Map.of("message", "Tên không tồn tại");
        }
    }

    public StudentResponse changeStatus(Long id, StudentStatus status) {
        Student s = repository.findById(id)
                .orElseThrow(() -> new DuplicateResourceException("Không tìm thấy sinh viên"));

        s.setStatus(status);
        return new StudentResponse(repository.save(s));
    }


    public List<StudentResponse> getByStatus(StudentStatus status) {
        return repository.findByStatus(status)
                .stream()
                .map(StudentResponse::new)
                .toList();
    }

}
