package com.sicaklik.takip.com.sicaklik.takip.bsf.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="Cihaz")
@Table(name="cihaz")
public class Cihaz implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7506477410204126671L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private Long id;
	@Column(name="bagli_sirket")
	private String bagli_sirket;
	@Column(name="kayit_tarihi", nullable = false)
	private Date kayit_tarihi;
	
	public Cihaz() {
		
	}

	public Cihaz(String bagli_sirket, Date kayit_tarihi) {
		super();
		this.bagli_sirket = bagli_sirket;
		this.kayit_tarihi = kayit_tarihi;
	}
}
