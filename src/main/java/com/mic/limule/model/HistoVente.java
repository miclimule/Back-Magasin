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
	
	@ManyToOne
	@JoinColumn(name = "pointventeid")
	private PointVente pointvente;

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
