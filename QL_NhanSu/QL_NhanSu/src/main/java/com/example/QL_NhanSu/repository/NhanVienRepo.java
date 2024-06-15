package com.example.QL_NhanSu.repository;

import com.example.QL_NhanSu.model.NHANVIEN;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhanVienRepo extends JpaRepository<NHANVIEN, String> {
}
