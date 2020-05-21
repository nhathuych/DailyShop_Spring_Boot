package com.nhathuy.dailyshopv2.service.impl;

import com.nhathuy.dailyshopv2.entity.ChiTietHoaDon;
import com.nhathuy.dailyshopv2.repository.ChiTietHoaDonRepository;
import com.nhathuy.dailyshopv2.service.ChiTietHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChiTietHoaDonServiceImpl implements ChiTietHoaDonService {

	@Autowired
	private ChiTietHoaDonRepository chiTietHoaDonRepository;

	@Override
	public void save(ChiTietHoaDon chiTietHoaDon) {
		chiTietHoaDonRepository.save(chiTietHoaDon);
	}
}
