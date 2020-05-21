package com.nhathuy.dailyshopv2.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "chitietsanpham", catalog = "dailyshopv2")
public class ChiTietSanPham implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int idChiTietSanPham;
	private MauSanPham mausanpham;
	private SanPham sanpham;
	private SizeSanPham sizesanpham;
	private int soLuong;
	private String ngayNhap;
	private ChiTietHoaDon chitiethoadon;

	public ChiTietSanPham() {
	}

	public ChiTietSanPham(MauSanPham mausanpham, SanPham sanpham, SizeSanPham sizesanpham, int soLuong, String ngayNhap) {
		this.mausanpham = mausanpham;
		this.sanpham = sanpham;
		this.sizesanpham = sizesanpham;
		this.soLuong = soLuong;
		this.ngayNhap = ngayNhap;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdChiTietSanPham", unique = true, nullable = false)
	public int getIdChiTietSanPham() {
		return this.idChiTietSanPham;
	}

	public void setIdChiTietSanPham(int idChiTietSanPham) {
		this.idChiTietSanPham = idChiTietSanPham;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IdMau")
	public MauSanPham getMausanpham() {
		return this.mausanpham;
	}

	public void setMausanpham(MauSanPham mausanpham) {
		this.mausanpham = mausanpham;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IdSanPham")
	public SanPham getSanpham() {
		return this.sanpham;
	}

	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IdSize")
	public SizeSanPham getSizesanpham() {
		return this.sizesanpham;
	}

	public void setSizesanpham(SizeSanPham sizesanpham) {
		this.sizesanpham = sizesanpham;
	}

	@Column(name = "SoLuong")
	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Column(name = "NgayNhap")
	public String getNgayNhap() {
		return this.ngayNhap;
	}

	public void setNgayNhap(String ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "chitietsanpham")
	public ChiTietHoaDon getChitiethoadon() {
		return chitiethoadon;
	}

	public void setChitiethoadon(ChiTietHoaDon chitiethoadon) {
		this.chitiethoadon = chitiethoadon;
	}
}
