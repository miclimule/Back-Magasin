package com.mic.limule.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="modellaptop")
public class ModelLaptop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
	@ManyToOne
	@JoinColumn(name = "marqueid")
	private Marque marque;
	
	private String description;
	
	@OneToMany(mappedBy = "modellaptop")
    private List<Laptop> laptop = new ArrayList<>();

	public ModelLaptop(long id, String nom, Marque marque, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.marque = marque;
		this.description = description;
	}

	public ModelLaptop() {
		super();
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

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
