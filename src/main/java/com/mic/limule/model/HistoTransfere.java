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
@Table(name="histotransfere")
public class HistoTransfere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "laptopid")
	private Laptop laptop;
	
	private LocalDate dateTransfere;
	
	private LocalDate dateReception;
	
	private String uidFacture;
	
	private boolean renvoie;
	
	private boolean perdu;
	
	public String getUidFacture() {
		return uidFacture;
	}

	public boolean isPerdu() {
		return perdu;
	}

	public void setPerdu(boolean perdu) {
		this.perdu = perdu;
	}

	public void setUidFacture(String uidFacture) {
		this.uidFacture = uidFacture;
	}

	@ManyToOne
	@JoinColumn(name = "pointventeid")
	private PointVente pointvente;

	public HistoTransfere() {
		super();
	}

	public boolean isRenvoie() {
		return renvoie;
	}

	public void setRenvoie(boolean renvoie) {
		this.renvoie = renvoie;
	}

	public HistoTransfere(long id, Laptop laptop, LocalDate dateTransfere, LocalDate dateReception,
			PointVente pointvente) {
		super();
		this.id = id;
		this.laptop = laptop;
		this.dateTransfere = dateTransfere;
		this.dateReception = dateReception;
		this.pointvente = pointvente;
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

	public LocalDate getDateTransfere() {
		return dateTransfere;
	}

	public void setDateTransfere(LocalDate dateTransfere) {
		this.dateTransfere = dateTransfere;
	}

	public LocalDate getDateReception() {
		return dateReception;
	}

	public void setDateReception(LocalDate dateReception) {
		this.dateReception = dateReception;
	}

	public PointVente getPointvente() {
		return pointvente;
	}

	public void setPointvente(PointVente pointvente) {
		this.pointvente = pointvente;
	}

}
