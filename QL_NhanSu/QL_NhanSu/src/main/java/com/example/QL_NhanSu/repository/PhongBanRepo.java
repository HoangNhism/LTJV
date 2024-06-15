package com.example.QL_NhanSu.repository;

import com.example.QL_NhanSu.model.NHANVIEN;
import com.example.QL_NhanSu.model.PHONGBAN;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhongBanRepo extends JpaRepository<PHONGBAN, String> {

}
