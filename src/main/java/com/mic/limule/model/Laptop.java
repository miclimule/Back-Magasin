package com.mic.limule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="laptop")
public class Laptop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
	private String ram;
	
	private int rom;
	
	private int pouce;
	
	private String couleur;
	
	private float prix;
	
	private boolean envente;
	
	private boolean vendu;
	
	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	@ManyToOne
	@JoinColumn(name = "modelid")
	private ModelLaptop modellaptop;

	public long getId() {
		return id;
	}
	
	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public int getRom() {
		return rom;
	}

	public void setRom(int rom) {
		this.rom = rom;
	}

	public int getPouce() {
		return pouce;
	}

	public void setPouce(int pouce) {
		this.pouce = pouce;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public boolean isEnvente() {
		return envente;
	}

	public void setEnvente(boolean envente) {
		this.envente = envente;
	}

	public boolean isVendu() {
		return vendu;
	}

	public void setVendu(boolean vendu) {
		this.vendu = vendu;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ModelLaptop getModellaptop() {
		return modellaptop;
	}

	public void setModellaptop(ModelLaptop modellaptop) {
		this.modellaptop = modellaptop;
	}

	public Laptop(long id, String nom, ModelLaptop modellaptop) {
		super();
		this.id = id;
		this.nom = nom;
		this.modellaptop = modellaptop;
	}

	public Laptop() {
		super();
	}
	
	
}
