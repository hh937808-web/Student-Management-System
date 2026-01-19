package com.example.quanlysinhvien.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "students",uniqueConstraints = {@UniqueConstraint(columnNames = "ma_sv")})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotBlank(message = "Mã sinh viên không được để trống!")
    @Column(name = "ma_sv", nullable = false, length = 50, unique = true)
    private String maSV;

    @Size(max = 100)
    @Nationalized
    @NotBlank(message = "Tên sinh viên không được để trống!")
    @Column(name = "ten_sv", nullable = false, length = 100)
    private String tenSV;

    @Column(name = "ngay_sinh")
    private Integer ngaySinh;

    @Email(message = "Email không đúng định dạng!")
    @Size(max = 100)
    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StudentStatus status = StudentStatus.ACTIVE;

}