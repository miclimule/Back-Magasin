package com.mic.limule.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mic.limule.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long>{

	@Query("SELECT u FROM Admin u WHERE u.nom = :#{#admin.nom} AND u.mdp = :#{#admin.mdp}")
	public List<Admin> login(@Param("admin") Admin admin);
	
}
