package com.nhathuy.dailyshopv2.service.impl;

import com.nhathuy.dailyshopv2.entity.ChiTietSanPham;
import com.nhathuy.dailyshopv2.repository.ChiTietSanPhamRepository;
import com.nhathuy.dailyshopv2.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

	@Autowired
	private ChiTietSanPhamRepository chiTietSanPhamRepository;

	@Override
	public Iterable<ChiTietSanPham> findByIdSanPham(int idSanPham) {
		return chiTietSanPhamRepository.findByIdSanPham(idSanPham);
	}

	@Override
	public ChiTietSanPham findById(int idChiTiet) {
		return chiTietSanPhamRepository.findById(idChiTiet).get();
	}

	@Override
	public void save(ChiTietSanPham chiTietSanPham) {
		chiTietSanPhamRepository.save(chiTietSanPham);
	}
}
