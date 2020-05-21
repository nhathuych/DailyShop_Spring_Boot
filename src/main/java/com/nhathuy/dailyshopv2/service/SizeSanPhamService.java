package com.nhathuy.dailyshopv2.service;

import com.nhathuy.dailyshopv2.entity.SizeSanPham;

public interface SizeSanPhamService {
	Iterable<SizeSanPham> findAll();
	SizeSanPham findById(int id);
}
