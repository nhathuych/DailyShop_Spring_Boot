package com.nhathuy.dailyshopv2.service;

import com.nhathuy.dailyshopv2.entity.DanhMucSanPham;

public interface DanhMucService {
	Iterable<DanhMucSanPham> findAll();
	Iterable<DanhMucSanPham> findByStatus(Boolean isEnable);
	DanhMucSanPham findById(int id);
	DanhMucSanPham findByMetaTitle(String metaTitle);
	void save(DanhMucSanPham danhmuc);
}
