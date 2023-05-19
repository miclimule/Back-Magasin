package com.mic.limule.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mic.limule.model.HistoVente;

public interface HistoVenteRepo extends JpaRepository<HistoVente, Long>{

	@Query("select EXTRACT(YEAR FROM u.dateVente) as annees , EXTRACT(MONTH FROM u.dateVente) as mois , sum(u.prixv) as prixv from HistoVente u GROUP BY EXTRACT(YEAR FROM u.dateVente), EXTRACT(MONTH FROM u.dateVente)")
	public List<HistoVente> vente();
	
}
