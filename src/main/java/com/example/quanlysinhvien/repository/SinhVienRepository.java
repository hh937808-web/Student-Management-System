package com.example.quanlysinhvien.repository;

import com.example.quanlysinhvien.entity.Student;
import com.example.quanlysinhvien.entity.StudentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SinhVienRepository extends JpaRepository<Student, Long> {
    boolean existsByMaSV(String maSV);

    List<Student> findByMaSVContainingOrTenSVContaining(String maSV, String tenSV);

    List<Student> findByStatus(StudentStatus status);

    Page<Student> findByStatus(StudentStatus status, Pageable pageable);
}
