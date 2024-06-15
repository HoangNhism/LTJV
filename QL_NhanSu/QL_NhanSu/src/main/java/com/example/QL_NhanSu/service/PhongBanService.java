package com.example.QL_NhanSu.service;

import com.example.QL_NhanSu.model.PHONGBAN;
import com.example.QL_NhanSu.repository.PhongBanRepo;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
    private final PhongBanRepo phongBanRepo;

    public List<PHONGBAN> getAllPhongBan(){
        return phongBanRepo.findAll();
    }
    public Optional<PHONGBAN> getPhongBanById(String id){
        return phongBanRepo.findById(id);
    }
    public void addPhongBan(PHONGBAN phongban){
        phongBanRepo.save(phongban);
    }
    public void updatePhongBan(@NotNull PHONGBAN phongban){
        PHONGBAN existingPhongBan = phongBanRepo.findById(phongban.getMaPhong()).orElseThrow(() -> new IllegalStateException("Phong Ban voi ma" + phongban.getMaPhong() + "khong ton tai."));
        existingPhongBan.setMaPhong(phongban.getMaPhong());
        existingPhongBan.setTenPhong(phongban.getTenPhong());
        phongBanRepo.save(existingPhongBan);
    }
    public void deletePhongBanById(String id) {
        if (!phongBanRepo.existsById(id)) {
            throw new IllegalStateException("Phong ban with ID " + id + " does not exist.");
        }
        phongBanRepo.deleteById(id);
    }
}
