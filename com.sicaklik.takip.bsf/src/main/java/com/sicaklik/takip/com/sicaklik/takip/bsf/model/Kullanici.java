package com.sicaklik.takip.com.sicaklik.takip.bsf.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="Kullanici")
@Table(name="user_table")
public class Kullanici implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7757645659515489880L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private long id;
	@Column(name="kullanici_adi", nullable = false)
	private String kullanici_adi;
	@Column(name="parola", nullable = false)
	private String parola;
	@Column(name="isim")
	private String isim;
	@Column(name="soy_isim")
	private String soy_isim;
	@Column(name="email", nullable = false)
	private String email;
	
	public Kullanici() {
		
	}
	
	public Kullanici(String kullanici_adi, String parola, String isim, String soy_isim, String email) {
		super();
		this.kullanici_adi = kullanici_adi;
		this.parola = parola;
		this.isim = isim;
		this.soy_isim = soy_isim;
		this.email = email;
	}

}
