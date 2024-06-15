package com.example.QL_NhanSu.service;

import com.example.QL_NhanSu.model.NHANVIEN;
import com.example.QL_NhanSu.repository.NhanVienRepo;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class NhanVienService {
    private final NhanVienRepo nhanvienRepository;
    // Retrieve all products from the database
    public List<NHANVIEN> getAllNhanVien() {
        return nhanvienRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<NHANVIEN> getNhanVienById(String id) {
        return nhanvienRepository.findById(id);
    }
    // Add a new product to the database
    public NHANVIEN addProduct(NHANVIEN nhanvien) {
        return nhanvienRepository.save(nhanvien);
    }
    // Update an existing product
    public NHANVIEN updateNhanVien(@NotNull NHANVIEN nhanvien) {
        NHANVIEN existingNhanVien = nhanvienRepository.findById(nhanvien.getMa_NV()).orElseThrow(() -> new IllegalStateException("Nhan Vien with ID " + nhanvien.getMa_NV() + " does not exist."));
        existingNhanVien.setMa_NV(nhanvien.getMa_NV());
        existingNhanVien.setTen_NV(nhanvien.getTen_NV());
        existingNhanVien.setLuong(nhanvien.getLuong());
        existingNhanVien.setPhai(nhanvien.getPhai());
        existingNhanVien.setNoiSinh(nhanvien.getNoiSinh());
        existingNhanVien.setMaPhong(nhanvien.getMaPhong());
        return nhanvienRepository.save(existingNhanVien);
    }
    // Delete a product by its id
    public void deleteNhanVienById(String id) {
        if (!nhanvienRepository.existsById(id)) {
            throw new IllegalStateException("Nhan Vien with ID " + id + " does not exist.");
        }
        nhanvienRepository.deleteById(id);
    }
}
