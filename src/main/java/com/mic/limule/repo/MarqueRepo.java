package com.mic.limule.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mic.limule.model.Marque;

public interface MarqueRepo extends JpaRepository<Marque, Long>{
	Marque findByNom(String nom);
}
