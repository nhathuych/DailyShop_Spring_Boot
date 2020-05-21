package com.nhathuy.dailyshopv2.service.impl;

import com.nhathuy.dailyshopv2.entity.HoaDon;
import com.nhathuy.dailyshopv2.repository.HoaDonRepository;
import com.nhathuy.dailyshopv2.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoaDonServiceImpl implements HoaDonService {

	@Autowired
	private HoaDonRepository hoaDonRepository;

	@Override
	public void save(HoaDon hoaDon) {
		hoaDonRepository.save(hoaDon);
	}

	@Override
	public Iterable<HoaDon> findAll() {
		return hoaDonRepository.findAll();
	}
}
