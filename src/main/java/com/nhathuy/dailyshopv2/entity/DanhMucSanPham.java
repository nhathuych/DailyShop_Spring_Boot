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
@Table(name = "danhmucsanpham", catalog = "dailyshopv2")
public class DanhMucSanPham implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdDanhMuc", unique = true, nullable = false)
	private int idDanhMuc;

	@Column(name = "TenDanhMuc", length = 45)
	private String tenDanhMuc;

	@Column(name = "MetaTitle", length = 45)
	private String metaTitle;

	@Column(name = "Status")
	private Boolean status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "danhmucsanpham")
	private Set<SanPham> sanphams = new HashSet<SanPham>(0);

	public DanhMucSanPham() {
	}

	public DanhMucSanPham(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public DanhMucSanPham(String tenDanhMuc, Set<SanPham> sanphams) {
		this.tenDanhMuc = tenDanhMuc;
		this.sanphams = sanphams;
	}

	public int getIdDanhMuc() {
		return this.idDanhMuc;
	}

	public void setIdDanhMuc(int idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}

	public String getTenDanhMuc() {
		return this.tenDanhMuc;
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
		return this.sanphams;
	}

	public void setSanphams(Set<SanPham> sanphams) {
		this.sanphams = sanphams;
	}
}
