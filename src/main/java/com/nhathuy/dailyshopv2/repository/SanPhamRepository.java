package com.nhathuy.dailyshopv2.repository;

import com.nhathuy.dailyshopv2.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
	@Query("select count(IdDanhMuc) from SanPham where IdDanhMuc = ?1")
	int countByIdDanhMuc(int idDanhMuc);
	Iterable<SanPham> findByTenSanPhamContains(String key);
}
