package com.sicaklik.takip.com.sicaklik.takip.bsf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.sicaklik.takip.com.sicaklik.takip.bsf.SicaklikJsfController;
import com.sicaklik.takip.com.sicaklik.takip.bsf.model.Cihaz;
import com.sicaklik.takip.com.sicaklik.takip.bsf.model.CihazSicaklik;
import com.sicaklik.takip.com.sicaklik.takip.bsf.serviceImpl.CihazServiceImp;
import com.sicaklik.takip.com.sicaklik.takip.bsf.serviceImpl.CihazSicaklikServiceImp;
import com.sicaklik.takip.com.sicaklik.takip.bsf.util.Util;

import lombok.Data;

//@ManagedBean
//@Component
//@Scope(ScopeName.VIEW)

@Data
@SicaklikJsfController
public class CihazController {
	
	private Cihaz sorguEnt;
	
	private List<Cihaz> cihazList;
	
	private Cihaz secilen;
	
	private Cihaz ekleCihaz;
	
	private BarChartModel barModel;
	
	private double anlikSicaklik;
	
	@Autowired
	private CihazServiceImp cihazService;
	
	@Autowired
	private CihazSicaklikServiceImp cihazSicaklikService;
	
	@PostConstruct
	public void cihazListesiDoldur() {
		secilen = null;
		sorguEnt = new Cihaz();
		ekleCihaz = new Cihaz();
//		cihazList = new ArrayList<Cihaz>();
		
		cihazList = cihazService.getAll();
		
		
//		System.out.println("cihaz listesi = ");
//		System.out.println(cihazList);
		
//		cihazService.saveAll(cihazList);
	}
	
	
	public String hello() {
		return "hello world";
	}
	
	public void cihazKaydet() {
		try {
			cihazService.kaydet(ekleCihaz);
			cihazList = cihazService.getAll();
			PrimeFaces.current().executeScript("basariliMessage();");
		}
		catch (Exception e) {
//			PrimeFaces.current().executeScript("PF('messages').renderMessage({summary:'Hata', detail: 'Cihaz eklenirken beklenmedik hata.', severity: 'error'});");
		}
	}
	
	public void sorgula() {
		System.out.println(sorguEnt); //&& sorguEnt.getBagli_sirket() != 0 && !Util.empty(sorguEnt.getBagli_sirket())
		if(sorguEnt.getId() != null && sorguEnt.getId() != 0) {
			Cihaz chz = cihazService.getir(sorguEnt.getId());
			cihazList = new ArrayList<Cihaz>();
			cihazList.add(chz);
		}
		else if(!Util.empty(sorguEnt.getBagli_sirket()) && sorguEnt.getKayit_tarihi() != null) {
			cihazList = cihazService.findByCompanyDateRegistration(sorguEnt.getBagli_sirket(), sorguEnt.getKayit_tarihi());
		}
		else if(!Util.empty(sorguEnt.getBagli_sirket()) && sorguEnt.getKayit_tarihi() == null) {
			cihazList = cihazService.findByCompany(sorguEnt.getBagli_sirket());
		}
		else if(Util.empty(sorguEnt.getBagli_sirket()) && sorguEnt.getKayit_tarihi() != null) {
			cihazList = cihazService.findByDateRegistration(sorguEnt.getKayit_tarihi());
		}
		else {
			cihazList = cihazService.getAll();
		}
	}
	
	public void temizle() {
		cihazList = cihazService.getAll();
		sorguEnt = new Cihaz();
	}
	
	
	public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Cihaz Sıcaklıkları");
        
        List<CihazSicaklik> cs = cihazSicaklikService.findByCihazId(secilen.getId());
         
        List<Number> values = new ArrayList<>();
        
        for (CihazSicaklik c : cs) {
        	values.add(c.getSicaklik());
		}
        
        barDataSet.setData(values);
         
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);
         
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
         
        data.addChartDataSet(barDataSet);
         
        List<Date> labels = new ArrayList<>();
        
        for (CihazSicaklik c : cs) {
        	labels.add(c.getOlusturma_tarih_saati());
		}
        
        data.setLabels(labels);
        barModel.setData(data);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Sıcaklık Chart");
        options.setTitle(title);
 
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
 
        barModel.setOptions(options);
    }
	
//	@Scheduled(fixedDelay=5000)
	public void sicaklikGetir() {
		 if(secilen != null) {
			 CihazSicaklik cz = cihazSicaklikService.sonSicaklikGetir(secilen.getId());
			 if(cz != null && cz.getSicaklik() != 0)
				 anlikSicaklik = cz.getSicaklik();
		 }
	}
	
	public void sil() {
		if(secilen != null) {
			cihazService.sil(secilen);
			secilen = null;
			cihazList = cihazService.getAll();
		}
			
	}
}
