package com.nhathuy.dailyshopv2.service.impl;

import com.nhathuy.dailyshopv2.entity.MauSanPham;
import com.nhathuy.dailyshopv2.repository.MauSanPhamRepository;
import com.nhathuy.dailyshopv2.service.MauSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MauSanPhamServiceImpl implements MauSanPhamService {

	@Autowired
	private MauSanPhamRepository mauSanPhamRepository;

	@Override
	public Iterable<MauSanPham> findAll() {
		return mauSanPhamRepository.findAll();
	}

	@Override
	public MauSanPham findById(int id) {
		return mauSanPhamRepository.findById(id).get();
	}
}
