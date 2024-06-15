package com.example.QL_NhanSu.controller;

import com.example.QL_NhanSu.model.NHANVIEN;
import com.example.QL_NhanSu.service.NhanVienService;
import com.example.QL_NhanSu.service.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;
    @GetMapping
    public String showNhanVienList(Model model) {
        model.addAttribute("nhanviens", nhanVienService.getAllNhanVien());
        return "/home/nhanvien-list";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanviens", new NHANVIEN());
        model.addAttribute("phongbans", phongBanService.getAllPhongBan());
        return "/home/add-nhanvien";
    }
    @PostMapping("/add")
    public String addProduct(@Valid NHANVIEN nhanvien, BindingResult result) {
        if (result.hasErrors()) {
            return "/products/add-product";
        }
        nhanVienService.addProduct(nhanvien);
        return "redirect:/home";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        NHANVIEN nhanvien = nhanVienService.getNhanVienById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("nhanviens", nhanvien);
        model.addAttribute("phongbans", nhanVienService.getAllNhanVien());
        return "/home/update-nhanvien";
    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable String id, @Valid NHANVIEN nhanvien, BindingResult result) {
        if (result.hasErrors()) {
            nhanvien.setMa_NV(id); // keep the id in the form in case of errors
            return "/home/update-nhanvien";
        }
        nhanVienService.updateNhanVien(nhanvien);
        return "redirect:/home";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        nhanVienService.deleteNhanVienById(id);
        return "redirect:/home";
    }
}
