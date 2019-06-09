package com.iavariav.wisbasmartwisatabatangsmart.model;

import com.google.gson.annotations.SerializedName;

public class KeluhanBeritaModel{

	@SerializedName("deskripsi_keluhan")
	private String deskripsiKeluhan;

	@SerializedName("nama_keluhan")
	private String namaKeluhan;

	@SerializedName("id_keluhan")
	private String idKeluhan;

	@SerializedName("registered")
	private String registered;

	@SerializedName("status_keluhan")
	private String statusKeluhan;

	@SerializedName("lat_keluhan")
	private String latKeluhan;

	@SerializedName("long_keluhan")
	private String longKeluhan;

	@SerializedName("id_account")
	private String idAccount;

	@SerializedName("jenis_keluhan")
	private String jenisKeluhan;

	@SerializedName("like_keluhan")
	private Object likeKeluhan;

	@SerializedName("gambar_keluhan")
	private String gambarKeluhan;

	@SerializedName("face_pelaporan_keluhan")
	private String facePelaporanKeluhan;

	@SerializedName("dislike_keluhan")
	private Object dislikeKeluhan;

	public void setDeskripsiKeluhan(String deskripsiKeluhan){
		this.deskripsiKeluhan = deskripsiKeluhan;
	}

	public String getDeskripsiKeluhan(){
		return deskripsiKeluhan;
	}

	public void setNamaKeluhan(String namaKeluhan){
		this.namaKeluhan = namaKeluhan;
	}

	public String getNamaKeluhan(){
		return namaKeluhan;
	}

	public void setIdKeluhan(String idKeluhan){
		this.idKeluhan = idKeluhan;
	}

	public String getIdKeluhan(){
		return idKeluhan;
	}

	public void setRegistered(String registered){
		this.registered = registered;
	}

	public String getRegistered(){
		return registered;
	}

	public void setStatusKeluhan(String statusKeluhan){
		this.statusKeluhan = statusKeluhan;
	}

	public String getStatusKeluhan(){
		return statusKeluhan;
	}

	public void setLatKeluhan(String latKeluhan){
		this.latKeluhan = latKeluhan;
	}

	public String getLatKeluhan(){
		return latKeluhan;
	}

	public void setLongKeluhan(String longKeluhan){
		this.longKeluhan = longKeluhan;
	}

	public String getLongKeluhan(){
		return longKeluhan;
	}

	public void setIdAccount(String idAccount){
		this.idAccount = idAccount;
	}

	public String getIdAccount(){
		return idAccount;
	}

	public void setJenisKeluhan(String jenisKeluhan){
		this.jenisKeluhan = jenisKeluhan;
	}

	public String getJenisKeluhan(){
		return jenisKeluhan;
	}

	public void setLikeKeluhan(Object likeKeluhan){
		this.likeKeluhan = likeKeluhan;
	}

	public Object getLikeKeluhan(){
		return likeKeluhan;
	}

	public void setGambarKeluhan(String gambarKeluhan){
		this.gambarKeluhan = gambarKeluhan;
	}

	public String getGambarKeluhan(){
		return gambarKeluhan;
	}

	public void setFacePelaporanKeluhan(String facePelaporanKeluhan){
		this.facePelaporanKeluhan = facePelaporanKeluhan;
	}

	public String getFacePelaporanKeluhan(){
		return facePelaporanKeluhan;
	}

	public void setDislikeKeluhan(Object dislikeKeluhan){
		this.dislikeKeluhan = dislikeKeluhan;
	}

	public Object getDislikeKeluhan(){
		return dislikeKeluhan;
	}
}