package com.nhathuy.dailyshopv2.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chitiethoadon", catalog = "dailyshopv2")
public class ChiTietHoaDon implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private ChiTietHoaDonId id;
	private ChiTietSanPham chitietsanpham;
	private HoaDon hoadon;
	private int soLuong;
	private int giaTien;

	public ChiTietHoaDon() {
	}

	public ChiTietHoaDon(ChiTietHoaDonId id, ChiTietSanPham chitietsanpham, HoaDon hoadon) {
		this.id = id;
		this.chitietsanpham = chitietsanpham;
		this.hoadon = hoadon;
	}

	public ChiTietHoaDon(ChiTietHoaDonId id, ChiTietSanPham chitietsanpham, HoaDon hoadon, int soLuong,
						 int giaTien) {
		this.id = id;
		this.chitietsanpham = chitietsanpham;
		this.hoadon = hoadon;
		this.soLuong = soLuong;
		this.giaTien = giaTien;
	}

	@EmbeddedId
	@AttributeOverrides({@AttributeOverride(name = "idHoaDon", column = @Column(name = "IdHoaDon", nullable = false)),
			@AttributeOverride(name = "idChiTietSanPham", column = @Column(name = "IdChiTietSanPham", nullable = false))})
	public ChiTietHoaDonId getId() {
		return this.id;
	}

	public void setId(ChiTietHoaDonId id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "IdChiTietSanPham", nullable = false, insertable = false, updatable = false)
	public ChiTietSanPham getChitietsanpham() {
		return this.chitietsanpham;
	}

	public void setChitietsanpham(ChiTietSanPham chitietsanpham) {
		this.chitietsanpham = chitietsanpham;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdHoaDon", nullable = false, insertable = false, updatable = false)
	public HoaDon getHoadon() {
		return this.hoadon;
	}

	public void setHoadon(HoaDon hoadon) {
		this.hoadon = hoadon;
	}

	@Column(name = "SoLuong")
	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Column(name = "GiaTien")
	public int getGiaTien() {
		return this.giaTien;
	}

	public void setGiaTien(int giaTien) {
		this.giaTien = giaTien;
	}

}
