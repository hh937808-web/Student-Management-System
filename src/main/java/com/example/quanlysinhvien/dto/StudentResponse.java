package com.example.quanlysinhvien.dto;

import com.example.quanlysinhvien.entity.Student;
import com.example.quanlysinhvien.entity.StudentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String maSV;
    private String tenSV;
    private Integer ngaySinh;
    private String email;
    private StudentStatus status;

    public StudentResponse(Student sv) {
        this.id = sv.getId();
        this.maSV = sv.getMaSV();
        this.tenSV = sv.getTenSV();
        this.ngaySinh = sv.getNgaySinh();
        this.email = sv.getEmail();
        this.status = sv.getStatus();
    }
}

