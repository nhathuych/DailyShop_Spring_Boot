package com.nhathuy.dailyshopv2.repository;

import com.nhathuy.dailyshopv2.entity.DanhMucSanPham;
import org.springframework.data.repository.CrudRepository;

public interface DanhMucRepository extends CrudRepository<DanhMucSanPham, Integer> {
	Iterable<DanhMucSanPham> findAll();
	Iterable<DanhMucSanPham> findByStatus(Boolean isEnable);
	DanhMucSanPham findByMetaTitle(String metaTitle);
}
