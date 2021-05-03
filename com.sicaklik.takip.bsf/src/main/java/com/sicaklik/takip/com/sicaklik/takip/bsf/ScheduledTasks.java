package com.sicaklik.takip.com.sicaklik.takip.bsf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sicaklik.takip.com.sicaklik.takip.bsf.model.Cihaz;
import com.sicaklik.takip.com.sicaklik.takip.bsf.model.CihazSicaklik;
import com.sicaklik.takip.com.sicaklik.takip.bsf.serviceImpl.CihazServiceImp;
import com.sicaklik.takip.com.sicaklik.takip.bsf.serviceImpl.CihazSicaklikServiceImp;
import com.sicaklik.takip.com.sicaklik.takip.bsf.serviceImpl.KullaniciServiceImp;

@Component
public class ScheduledTasks {

	@Autowired
	private KullaniciServiceImp kullaniciService;
	
	@Autowired
	private CihazServiceImp cihazService;
	
	@Autowired
	private CihazSicaklikServiceImp cihazSicaklikService;
	
//		 "0 0 * * * *" = the top of every hour of every day.
//		 "*/10 * * * * *" = every ten seconds.
//		 "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//		 "0 0 8,10 * * *" = 8 and 10 o'clock of every day.
//		 "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
//		 "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
//		 "0 0 0 25 12 ?" = every Christmas Day at midnight
//		
//		Cron expression is represented by six fields:
//		second, minute, hour, day of month, month, day(s) of week
//
//	@Scheduled(fixedRate = 86400000) --> 24 saatte 1
//	@Scheduled(cron = "0 6 14 * * *") --> 14:06

	@Scheduled(cron = "*/10 * * * * *")	//  --> 06:00
	public void anlikDegerAl() throws IOException {
		File file = new File("C:\\Users\\furka\\OneDrive\\Masaüstü\\denemeDeger.txt");
		FileWriter myWriter = new FileWriter("C:\\Users\\furka\\OneDrive\\Masaüstü\\okunanDeger.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file));

		
		/*
		 * Simdilik tek cihaz ustunden calisiyoruz ileride daha dinamik hale getirilebilir
		 * */

		Cihaz cihaz = cihazService.getir(1L);
		
		String st;
		String bagilNem = "";
		String ortamSicakligi = "";
		String betonSicakligi = "";
		CihazSicaklik xs = null;
		while ((st = br.readLine()) != null) {
			if(st.startsWith("*"))
				continue;
			else {
				String[] elemans = st.split("\t");
				if(elemans.length > 1) {
					bagilNem = elemans[0].substring(elemans[0].indexOf("%")+1, elemans[0].length()).trim();
					ortamSicakligi = elemans[1].substring(elemans[1].indexOf(":")+1, elemans[1].indexOf("°")).trim();
					betonSicakligi = elemans[2].substring(elemans[2].indexOf(":")+1, elemans[2].indexOf("°")).trim();
					System.out.println(Arrays.toString(elemans));
					System.out.print(bagilNem + " - "+ ortamSicakligi + " - " + betonSicakligi);
					System.out.println();
					xs = new CihazSicaklik();
					xs.setBagil_nem(Double.parseDouble(bagilNem));
					xs.setSicaklik_hava(Double.parseDouble(ortamSicakligi));
					xs.setSicaklik_beton(Double.parseDouble(betonSicakligi));
					xs.setCihaz_id(cihaz);
					cihazSicaklikService.kaydet(xs);
				}
			}
			myWriter.write(st);
			myWriter.write("\n");
	  	}
		
		FileOutputStream fooStream = new FileOutputStream(file, false);

		fooStream.write("".getBytes());
		fooStream.close();
		
		myWriter.close();  
	}

}