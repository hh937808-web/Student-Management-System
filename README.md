QUẢN LÝ SINH VIÊN – Student Management System
1. Giới thiệu project 
Đây là project Quản Lý Sinh Viên được xây dựng bằng Spring Boot, mục tiêu giúp:
Làm quen với Spring Boot
Hiểu cách xây dựng REST API
Thao tác cơ sở dữ liệu bằng Spring Data JPA
Áp dụng Validation và Exception Handling cơ bản
Project phù hợp cho người ôn tập kiến thức Spring Boot mức cơ bản – trung bình.
2. Chức năng chính trong project 
Thêm mới sinh viên
Cập nhật thông tin sinh viên
Xóa sinh viên
Xem danh sách sinh viên
Tìm kiếm sinh viên theo:
Mã sinh viên
Tên sinh viên
Quản lý trạng thái sinh viên (đang quản lý / ngừng quản lý)
3. Công nghệ sử dụng
Java 17+
Spring Boot
Spring Web (REST API)
Spring Data JPA
SQL Server
Maven
4. Chuẩn bị môi trường
Trước khi chạy project, cần đảm bảo:
Đã cài JDK 17+
Đã cài SQL Server
IDE: IntelliJ IDEA 
5. Database để test 
CREATE DATABASE QuanLySinhVien;
GO

USE QuanLySinhVien;
GO

CREATE TABLE students (
id BIGINT IDENTITY(1,1) PRIMARY KEY,
ma_sv VARCHAR(50) NOT NULL UNIQUE,
ten_sv NVARCHAR(100) NOT NULL,
ngay_sinh INT,
email VARCHAR(100)
);

ALTER TABLE students
ADD status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';

INSERT INTO students (ma_sv, ten_sv, ngay_sinh, email) VALUES
('SV001', N'Nguyễn Văn An', 2002, 'an.nguyen@gmail.com'),
('SV002', N'Trần Thị Bình', 2001, 'binh.tran@gmail.com'),
('SV003', N'Lê Văn Cường', 2003, 'cuong.le@gmail.com'),
('SV004', N'Phạm Thị Dung', 2002, 'dung.pham@gmail.com'),
('SV005', N'Hoàng Văn Em', 2001, 'em.hoang@gmail.com'),
('SV006', N'Vũ Thị Hoa', 2003, 'hoa.vu@gmail.com'),
('SV007', N'Đặng Văn Khoa', 2002, 'khoa.dang@gmail.com'),
('SV008', N'Bùi Thị Lan', 2001, 'lan.bui@gmail.com'),
('SV009', N'Phan Văn Minh', 2003, 'minh.phan@gmail.com'),
('SV010', N'Đỗ Thị Ngọc', 2002, 'ngoc.do@gmail.com');

SELECT * FROM students;
6. Cấu hình ứng dụng
Mở file application.properties và chỉnh lại cho đúng máy của bạn:
spring.application.name=QuanLySinhVien
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=file:src/main/resources/templates/
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=QuanLySinhVien;encrypt=true;trustServerCertificate=true;
spring.datasource.username=sa
spring.datasource.password=123
spring.jpa.show-sql=true
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

7. API 
link demo: https://youtu.be/qlfPmxWeUvI