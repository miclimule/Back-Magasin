package com.mic.limule.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mic.limule.model.HistoTransfere;

public interface HistoTransfereRepo extends JpaRepository<HistoTransfere, Long>{

	@Query("SELECT u FROM HistoTransfere u WHERE u.dateTransfere = null")
	public List<HistoTransfere> stockMagasin();
	
	@Query("SELECT u FROM HistoTransfere u WHERE u.dateTransfere != null And u.dateReception != null")
	public List<HistoTransfere> stockPointVente();
	
	@Query("SELECT u FROM HistoTransfere u WHERE u.dateReception != null")
	public List<HistoTransfere> historeception();
	
	HistoTransfere findByUidFacture(String uidFacture);
}
