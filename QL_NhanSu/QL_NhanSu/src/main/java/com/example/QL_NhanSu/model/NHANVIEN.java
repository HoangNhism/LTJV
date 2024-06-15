package com.example.QL_NhanSu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhanviens")
public class NHANVIEN {
    @Id
    @Column(name = "ma_NV")
    private String ma_NV;
    private String Ten_NV;
    private String Phai;
    private String NoiSinh;
    @ManyToOne
    @JoinColumn(name = "phong_id")
    private PHONGBAN MaPhong;
    private int Luong;
}
