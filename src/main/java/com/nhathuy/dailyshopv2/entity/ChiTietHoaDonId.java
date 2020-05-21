package com.nhathuy.dailyshopv2.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChiTietHoaDonId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int idHoaDon;
	private int idChiTietSanPham;

	public ChiTietHoaDonId() {
	}

	public ChiTietHoaDonId(int idHoaDon, int idChiTietSanPham) {
		this.idHoaDon = idHoaDon;
		this.idChiTietSanPham = idChiTietSanPham;
	}

	@Column(name = "IdHoaDon", nullable = false)
	public int getIdHoaDon() {
		return this.idHoaDon;
	}

	public void setIdHoaDon(int idHoaDon) {
		this.idHoaDon = idHoaDon;
	}

	@Column(name = "IdChiTietSanPham", nullable = false)
	public int getIdChiTietSanPham() {
		return this.idChiTietSanPham;
	}

	public void setIdChiTietSanPham(int idChiTietSanPham) {
		this.idChiTietSanPham = idChiTietSanPham;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ChiTietHoaDonId))
			return false;
		ChiTietHoaDonId castOther = (ChiTietHoaDonId) other;

		return (this.getIdHoaDon() == castOther.getIdHoaDon())
				&& (this.getIdChiTietSanPham() == castOther.getIdChiTietSanPham());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdHoaDon();
		result = 37 * result + this.getIdChiTietSanPham();
		return result;
	}
}
