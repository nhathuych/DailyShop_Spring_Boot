package com.nhathuy.dailyshopv2.model;

import com.nhathuy.dailyshopv2.entity.SanPham;

import java.util.HashSet;
import java.util.Set;

public class DanhMucDTO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int idDanhMuc;
	private String tenDanhMuc;
	private String metaTitle;
	private Boolean status;
	private Set<SanPham> sanphams = new HashSet<SanPham>(0);

	public DanhMucDTO() {
	}

	public int getIdDanhMuc() {
		return idDanhMuc;
	}

	public void setIdDanhMuc(int idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<SanPham> getSanphams() {
		return sanphams;
	}

	public void setSanphams(Set<SanPham> sanphams) {
		this.sanphams = sanphams;
	}
}
