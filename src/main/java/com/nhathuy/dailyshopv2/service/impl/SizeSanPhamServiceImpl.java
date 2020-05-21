package com.nhathuy.dailyshopv2.service.impl;

import com.nhathuy.dailyshopv2.entity.SizeSanPham;
import com.nhathuy.dailyshopv2.repository.SizeSanPhamRepository;
import com.nhathuy.dailyshopv2.service.SizeSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeSanPhamServiceImpl implements SizeSanPhamService {

	@Autowired
	private SizeSanPhamRepository sizeSanPhamRepository;

	@Override
	public Iterable<SizeSanPham> findAll() {
		return sizeSanPhamRepository.findAll();
	}

	@Override
	public SizeSanPham findById(int id) {
		return sizeSanPhamRepository.findById(id).get();
	}
}
