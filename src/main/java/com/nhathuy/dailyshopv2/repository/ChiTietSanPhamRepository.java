package com.nhathuy.dailyshopv2.repository;

import com.nhathuy.dailyshopv2.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChiTietSanPhamRepository extends CrudRepository<ChiTietSanPham, Integer> {
	@Query("from ChiTietSanPham where IdSanPham = ?1")
	Iterable<ChiTietSanPham> findByIdSanPham(int idSanPham);
}
