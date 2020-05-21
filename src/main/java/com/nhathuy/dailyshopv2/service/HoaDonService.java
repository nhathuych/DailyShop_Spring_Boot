package com.nhathuy.dailyshopv2.service;

import com.nhathuy.dailyshopv2.entity.HoaDon;

public interface HoaDonService {
	void save(HoaDon hoaDon);
	Iterable<HoaDon> findAll();
}
