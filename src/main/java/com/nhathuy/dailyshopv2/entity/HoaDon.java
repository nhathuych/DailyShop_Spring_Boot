package com.nhathuy.dailyshopv2.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "hoadon", catalog = "dailyshopv2")
public class HoaDon implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int idHoaDon;
	private String tenKhachHang;
	private String sdt;
	private String diaChiGiao;
	private String hinhThucGiao;
	private String ghiChu;
	private String ngayLap;
	private boolean daThanhToan;
	private Set<ChiTietHoaDon> chitiethoadons = new HashSet<ChiTietHoaDon>(0);

	public HoaDon() {
	}

	public HoaDon(String tenKhachHang, String sdt, String diaChiGiao, String ghiChu, String ngayLap, boolean daThanhToan) {
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
		this.diaChiGiao = diaChiGiao;
		this.ghiChu = ghiChu;
		this.ngayLap = ngayLap;
		this.daThanhToan = daThanhToan;
	}

	public HoaDon(String tenKhachHang, String sdt, String diaChiGiao, String hinhThucGiao, String ghiChu, String ngayLap,
				  Set<ChiTietHoaDon> chitiethoadons) {
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
		this.diaChiGiao = diaChiGiao;
		this.hinhThucGiao = hinhThucGiao;
		this.ghiChu = ghiChu;
		this.ngayLap = ngayLap;
		this.chitiethoadons = chitiethoadons;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdHoaDon", unique = true, nullable = false)
	public int getIdHoaDon() {
		return this.idHoaDon;
	}

	public void setIdHoaDon(int idHoaDon) {
		this.idHoaDon = idHoaDon;
	}

	@Column(name = "TenKhachHang", length = 50)
	public String getTenKhachHang() {
		return this.tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	@Column(name = "Sdt", length = 20)
	public String getSdt() {
		return this.sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	@Column(name = "DiaChiGiao", length = 100)
	public String getDiaChiGiao() {
		return this.diaChiGiao;
	}

	public void setDiaChiGiao(String diaChiGiao) {
		this.diaChiGiao = diaChiGiao;
	}

	@Column(name = "HinhThucGiao", length = 50)
	public String getHinhThucGiao() {
		return this.hinhThucGiao;
	}

	public void setHinhThucGiao(String hinhThucGiao) {
		this.hinhThucGiao = hinhThucGiao;
	}

	@Column(name = "GhiChu", length = 65535)
	public String getGhiChu() {
		return this.ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Column(name = "NgayLap")
	public String getNgayLap() {
		return this.ngayLap;
	}

	public void setNgayLap(String ngayLap) {
		this.ngayLap = ngayLap;
	}

	@Column(name = "DaThanhToan", length = 1)
	public boolean isDaThanhToan() {
		return daThanhToan;
	}

	public void setDaThanhToan(boolean daThanhToan) {
		this.daThanhToan = daThanhToan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "hoadon")
	public Set<ChiTietHoaDon> getChitiethoadons() {
		return this.chitiethoadons;
	}

	public void setChitiethoadons(Set<ChiTietHoaDon> chitiethoadons) {
		this.chitiethoadons = chitiethoadons;
	}
}
