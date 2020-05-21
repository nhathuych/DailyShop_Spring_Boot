package com.nhathuy.dailyshopv2.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mausanpham", catalog = "dailyshopv2")
public class MauSanPham implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int idMau;
	private String mau;
	private Set<ChiTietSanPham> chitietsanphams = new HashSet<ChiTietSanPham>(0);

	public MauSanPham() {
	}

	public MauSanPham(String mau, Set<ChiTietSanPham> chitietsanphams) {
		this.mau = mau;
		this.chitietsanphams = chitietsanphams;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdMau", unique = true, nullable = false)
	public int getIdMau() {
		return this.idMau;
	}

	public void setIdMau(int idMau) {
		this.idMau = idMau;
	}

	@Column(name = "Mau", length = 30)
	public String getMau() {
		return this.mau;
	}

	public void setMau(String mau) {
		this.mau = mau;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mausanpham")
	public Set<ChiTietSanPham> getChitietsanphams() {
		return this.chitietsanphams;
	}

	public void setChitietsanphams(Set<ChiTietSanPham> chitietsanphams) {
		this.chitietsanphams = chitietsanphams;
	}

}
