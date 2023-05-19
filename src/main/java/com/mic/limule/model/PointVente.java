package com.mic.limule.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="pointvente")
public class PointVente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
	private float distance;
	
	@OneToMany(mappedBy = "pointvente")
    private List<Vendeur> vendeur = new ArrayList<>();

	
	public List<Vendeur> getVendeur() {
		return vendeur;
	}

	public void setVendeur(List<Vendeur> vendeur) {
		this.vendeur = vendeur;
	}

	public long getId() {
		return id;
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

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public PointVente(long id, String nom, float distance) {
		super();
		this.id = id;
		this.nom = nom;
		this.distance = distance;
	}

	public PointVente() {
		super();
	}
	
	
}
