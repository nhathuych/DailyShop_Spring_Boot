package com.nhathuy.dailyshopv2.service.impl;

import com.nhathuy.dailyshopv2.entity.DanhMucSanPham;
import com.nhathuy.dailyshopv2.repository.DanhMucRepository;
import com.nhathuy.dailyshopv2.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DanhMucServiceImpl implements DanhMucService {

	@Autowired
	private DanhMucRepository danhMucRepository;

	@Override
	public Iterable<DanhMucSanPham> findAll() {
		return danhMucRepository.findAll();
	}

	@Override
	public Iterable<DanhMucSanPham> findByStatus(Boolean isEnable) {
		return danhMucRepository.findByStatus(isEnable);
	}

	@Override
	public DanhMucSanPham findById(int id) {
		return danhMucRepository.findById(id).get();
	}

	@Override
	public DanhMucSanPham findByMetaTitle(String metaTitle) {
		return danhMucRepository.findByMetaTitle(metaTitle);
	}

	@Override
	public void save(DanhMucSanPham danhmuc) {
		danhMucRepository.save(danhmuc);
	}
}
