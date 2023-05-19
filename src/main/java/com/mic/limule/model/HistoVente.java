package com.mic.limule.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="histovente")
public class HistoVente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "laptopid")
	private Laptop laptop;
	
	private LocalDate dateVente;
	
	private String nomClient;
	
	private String uidFacture;
	
	private float prixa;
	
	private float prixv;
	
	private String mois;
	
	private String annees;
	
	private String sumv;
	
	

	public String getSumv() {
		return sumv;
	}

	public void setSumv(String sumv) {
		this.sumv = sumv;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public String getAnnees() {
		return annees;
	}

	public void setAnnees(String annees) {
		this.annees = annees;
	}

	@ManyToOne
	@JoinColumn(name = "pointventeid")
	private PointVente pointvente;

	public float getPrixa() {
		return prixa;
	}

	public void setPrixa(float prixa) {
		this.prixa = prixa;
	}

	public float getPrixv() {
		return prixv;
	}

	public void setPrixv(float prixv) {
		this.prixv = prixv;
	}

	public HistoVente(Laptop laptop, LocalDate dateVente, String nomClient, String uidFacture, PointVente pointvente) {
		super();
		this.laptop = laptop;
		this.dateVente = dateVente;
		this.nomClient = nomClient;
		this.uidFacture = uidFacture;
		this.pointvente = pointvente;
	}

	public String getUidFacture() {
		return uidFacture;
	}

	public void setUidFacture(String uidFacture) {
		this.uidFacture = uidFacture;
	}

	public PointVente getPointvente() {
		return pointvente;
	}

	public void setPointvente(PointVente pointvente) {
		this.pointvente = pointvente;
	}

	public HistoVente(Laptop laptop, LocalDate dateVente, String nomClient, PointVente pointvente) {
		super();
		this.laptop = laptop;
		this.dateVente = dateVente;
		this.nomClient = nomClient;
		this.pointvente = pointvente;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Laptop getLaptop() {
		return laptop;
	}

	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}

	public LocalDate getDateVente() {
		return dateVente;
	}

	public void setDateVente(LocalDate dateVente) {
		this.dateVente = dateVente;
	}

	public HistoVente() {
		super();
	}
	
	
	
}
