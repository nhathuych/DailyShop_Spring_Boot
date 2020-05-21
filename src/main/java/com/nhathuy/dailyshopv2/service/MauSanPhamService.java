package com.nhathuy.dailyshopv2.service;

import com.nhathuy.dailyshopv2.entity.MauSanPham;

public interface MauSanPhamService {
	Iterable<MauSanPham> findAll();
	MauSanPham findById(int id);
}
