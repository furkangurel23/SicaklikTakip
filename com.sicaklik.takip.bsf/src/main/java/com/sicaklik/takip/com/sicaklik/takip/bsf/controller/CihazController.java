package com.sicaklik.takip.com.sicaklik.takip.bsf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sicaklik.takip.com.sicaklik.takip.bsf.model.Cihaz;
import com.sicaklik.takip.com.sicaklik.takip.bsf.scpoe.ScopeName;

import lombok.Data;

@Data
@Component
@Scope(ScopeName.VIEW)
public class CihazController {
	
	private Cihaz sorguEnt;
	
	private List<Cihaz> cihazList;
	
	private Cihaz secilen;
	
	@PostConstruct
	public void cihazListesiDoldur() {
		secilen = null;
		sorguEnt = new Cihaz();
		cihazList = new ArrayList<Cihaz>();
		
		for(int i = 0; i < 50; i++) {
			Cihaz c1 = new Cihaz("Şirket " + i, new Date());
			c1.setId((long) i);
			cihazList.add(c1);
		}
		
		System.out.println("cihaz listesi = ");
		System.out.println(cihazList);
	}
}
