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
@Table(name = "sizesanpham", catalog = "dailyshopv2")
public class SizeSanPham implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int idSize;
	private String size;
	private Set<ChiTietSanPham> chitietsanphams = new HashSet<ChiTietSanPham>(0);

	public SizeSanPham() {
	}

	public SizeSanPham(String size, Set<ChiTietSanPham> chitietsanphams) {
		this.size = size;
		this.chitietsanphams = chitietsanphams;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdSize", unique = true, nullable = false)
	public int getIdSize() {
		return this.idSize;
	}

	public void setIdSize(int idSize) {
		this.idSize = idSize;
	}

	@Column(name = "Size", length = 10)
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sizesanpham")
	public Set<ChiTietSanPham> getChitietsanphams() {
		return this.chitietsanphams;
	}

	public void setChitietsanphams(Set<ChiTietSanPham> chitietsanphams) {
		this.chitietsanphams = chitietsanphams;
	}

}
