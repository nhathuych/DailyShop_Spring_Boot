package com.nhathuy.dailyshopv2.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sanpham", catalog = "dailyshopv2")
public class SanPham implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int idSanPham;
	private DanhMucSanPham danhmucsanpham;
	private String tenSanPham;
	private int giaTien;
	private String moTa;
	private String gioiTinh;
	private String hinhAnh;
	private Set<ChiTietSanPham> chitietsanphams;

	public SanPham() {
	}

	public SanPham(DanhMucSanPham danhmucsanpham, String tenSanPham, int giaTien, String moTa, String gioiTinh,
				   String hinhAnh, Set<ChiTietSanPham> chitietsanphams) {
		this.danhmucsanpham = danhmucsanpham;
		this.tenSanPham = tenSanPham;
		this.giaTien = giaTien;
		this.moTa = moTa;
		this.gioiTinh = gioiTinh;
		this.hinhAnh = hinhAnh;
		this.chitietsanphams = chitietsanphams;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdSanPham", unique = true, nullable = false)
	public int getIdSanPham() {
		return this.idSanPham;
	}

	public void setIdSanPham(int idSanPham) {
		this.idSanPham = idSanPham;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IdDanhMuc")
	public DanhMucSanPham getDanhmucsanpham() {
		return this.danhmucsanpham;
	}

	public void setDanhmucsanpham(DanhMucSanPham danhmucsanpham) {
		this.danhmucsanpham = danhmucsanpham;
	}

	@Column(name = "TenSanPham", length = 45)
	public String getTenSanPham() {
		return this.tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	@Column(name = "GiaTien")
	public int getGiaTien() {
		return this.giaTien;
	}

	public void setGiaTien(int giaTien) {
		this.giaTien = giaTien;
	}

	@Column(name = "MoTa", length = 65535)
	public String getMoTa() {
		return this.moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Column(name = "GioiTinh", length = 20)
	public String getGioiTinh() {
		return this.gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Column(name = "HinhAnh", length = 50)
	public String getHinhAnh() {
		return this.hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "IdSanPham")
	public Set<ChiTietSanPham> getChitietsanphams() {
		return this.chitietsanphams;
	}

	public void setChitietsanphams(Set<ChiTietSanPham> chitietsanphams) {
		this.chitietsanphams = chitietsanphams;
	}
}
