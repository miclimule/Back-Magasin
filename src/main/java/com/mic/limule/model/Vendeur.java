package com.mic.limule.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="admin")
public class Vendeur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
	private String mdp;
	
	@ManyToOne
	@JoinColumn(name = "pointventeid")
	private PointVente pointvente;

	public PointVente getPointvente() {
		return pointvente;
	}

	public void setPointvente(PointVente pointvente) {
		this.pointvente = pointvente;
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

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] hash = digest.digest(mdp.getBytes());
        String hexHash = bytesToHex(hash);
		this.mdp = hexHash;
	}
	
	private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

	public Vendeur(long id, String nom, String mdp) {
		super();
		this.id = id;
		this.nom = nom;
		this.mdp = mdp;
	}

	public Vendeur() {
		super();
	}
	
	
}
