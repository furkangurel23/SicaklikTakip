package com.sicaklik.takip.com.sicaklik.takip.bsf.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="CihazSicaklik")
@Table(name="cihaz_sicaklik")
public class CihazSicaklik implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1588352320712953162L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private long id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cihaz_id", nullable = false)
	private Cihaz cihaz_id;
	@Column(name="olusturma_tarih_saati", nullable = false)
	private Date olusturma_tarih_saati;
	@Column(name="sicaklik", nullable = false)
	private double sicaklik;
	
	public CihazSicaklik() {
		
	}
	
	public CihazSicaklik(Cihaz cihaz_id, double sicaklik) {
		super();
		this.cihaz_id = cihaz_id;
		this.sicaklik = sicaklik;
	}
	
}
