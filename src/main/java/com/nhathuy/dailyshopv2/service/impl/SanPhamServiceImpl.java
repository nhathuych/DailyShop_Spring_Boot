package com.nhathuy.dailyshopv2.service.impl;

import com.nhathuy.dailyshopv2.entity.SanPham;
import com.nhathuy.dailyshopv2.repository.SanPhamRepository;
import com.nhathuy.dailyshopv2.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class SanPhamServiceImpl implements SanPhamService {

	@Autowired
	private SanPhamRepository sanPhamRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public Iterable<SanPham> findSanPham(int startPosition, int maxResult) {
		return entityManager.createQuery("from SanPham", SanPham.class)
				.setFirstResult(startPosition).setMaxResults(maxResult).getResultList();
	}

	@Override
	public Iterable<SanPham> findByIdDanhMuc(int idDanhMuc, int startPosition, int maxResult) {
		return entityManager.createQuery("from SanPham where IdDanhMuc = :id", SanPham.class)
				.setParameter("id", idDanhMuc).setFirstResult(startPosition).setMaxResults(maxResult).getResultList();
	}

	@Override
	public int countByIdDanhMuc(int idDanhMuc) {
		return sanPhamRepository.countByIdDanhMuc(idDanhMuc);
	}

	@Override
	public Iterable<SanPham> findByTenSanPhamContains(String key) {
		return sanPhamRepository.findByTenSanPhamContains(key);
	}

	@Override
	public SanPham findById(int id) {
		return sanPhamRepository.findById(id).get();
	}

	@Override
	public void save(SanPham sanPham) {
		sanPhamRepository.save(sanPham);
	}

	@Override
	public void deleteById(Integer id) {
		sanPhamRepository.deleteById(id);
	}
}
