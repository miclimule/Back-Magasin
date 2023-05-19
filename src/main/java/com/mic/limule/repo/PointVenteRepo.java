package com.mic.limule.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mic.limule.model.PointVente;

public interface PointVenteRepo extends JpaRepository<PointVente, Long>{
	PointVente findByNom(String nom);
}
