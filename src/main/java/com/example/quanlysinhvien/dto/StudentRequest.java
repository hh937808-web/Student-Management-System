package com.example.quanlysinhvien.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
    private Long id;
    @NotBlank(message = "Mã sinh viên không được để trống!")
    private String maSV;

    @NotBlank(message = "Tên sinh viên không được để trống!")
    private String tenSV;

    @NotNull(message = "Ngày sinh không được để trống!")
    private Integer ngaySinh;

    @Email(message = "Email không đúng định dạng!")
    @NotBlank(message = "Email không được để trống!")
    private String email;

    @NotBlank(message = "Trạng thái không được để trống")
    private String status;
}
