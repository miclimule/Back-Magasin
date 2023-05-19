package com.mic.limule.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.mic.limule.model.Admin;
import com.mic.limule.model.HistoTransfere;
import com.mic.limule.model.HistoVente;
import com.mic.limule.model.Laptop;
import com.mic.limule.model.Marque;
import com.mic.limule.model.ModelLaptop;
import com.mic.limule.model.PointVente;
import com.mic.limule.model.Vendeur;
import com.mic.limule.repo.AdminRepo;
import com.mic.limule.repo.HistoTransfereRepo;
import com.mic.limule.repo.HistoVenteRepo;
import com.mic.limule.repo.ImgLaptopRepo;
import com.mic.limule.repo.LaptopRepo;
import com.mic.limule.repo.MagasinRepo;
import com.mic.limule.repo.MarqueRepo;
import com.mic.limule.repo.ModelLaptopRepo;
import com.mic.limule.repo.PointVenteRepo;
import com.mic.limule.repo.VendeurRepo;

@CrossOrigin
@Controller
public class PageController {

	@Autowired
	AdminRepo adminRepo;
	@Autowired
	HistoTransfereRepo histoTransfereRepo;
	@Autowired
	HistoVenteRepo histoVenteRepo;
	@Autowired
	ImgLaptopRepo imgLaptopRepo;
	@Autowired
	LaptopRepo laptopRepo;
	@Autowired
	MagasinRepo magasinRepo;
	@Autowired
	MarqueRepo marqueRepo;
	@Autowired
	ModelLaptopRepo modelLaptopRepo;
	@Autowired
	PointVenteRepo pointVenteRepo;
	@Autowired
	VendeurRepo vendeurRepo;
	@Autowired
	JdbcTemplate db;
	
	@GetMapping("/")
	private String login() {
		return "login";
	}
	
	@GetMapping("/index")
	private String index() {
		return "index";
	}
	
	@GetMapping("/error")
	private String error() {
		return "404";
	}
	
	@PostMapping("/")
	private String login(String nom , String mdp) throws NoSuchAlgorithmException {
		Admin admin = new Admin();
		admin.setNom(nom);
		admin.setMdp(mdp);
		System.out.println("login");
		List<Admin> verif = adminRepo.login(admin);
		if (verif.size()>=1) {
			return "index";
		}
		return "login";
	}
	
	@GetMapping("/achat")
	private String achat() {
		return "PageMagasin/achat/achat";
	}
	
	@GetMapping("/affectation")
	private String affectation(Model model) {
		List<Vendeur> vendeurs = vendeurRepo.findAll();
		List<PointVente> pointVentes = pointVenteRepo.findAll();
		model.addAttribute("points", pointVentes);
		model.addAttribute("noms",vendeurs);
		return "PageMagasin/employe/Affectation";
	}
	
	@GetMapping("/insertion")
	private String insertion() {
		return "PageMagasin/employe/insertion";
	}
	
	@GetMapping("/pointvente")
	private String pointvente() {
		return "PageMagasin/employe/PointVente";
	}
	
	@GetMapping("/liste")
	private String liste(Model model) {
		List<Vendeur> vendeurs = vendeurRepo.findAll();
		model.addAttribute("vendeurs",vendeurs);
		return "PageMagasin/employe/liste";
	}
	
	@GetMapping("/listepoint")
	private String listepoint(Model model) {
		List<PointVente> vendeurs = pointVenteRepo.findAll();
		model.addAttribute("points",vendeurs);
		return "PageMagasin/employe/listePoint";
	}
	
	@GetMapping("/laptopinsertion")
	private String laptopinsertion(Model model) {
		List<ModelLaptop> mlaptop = modelLaptopRepo.findAll();
		model.addAttribute("models",mlaptop);
		return "PageMagasin/laptop/laptopinsertion";
	}
	
	@GetMapping("/laptopliste")
	private String laptopliste(Model model) {
		List<Laptop> laptops = laptopRepo.findAll();
		model.addAttribute("laptops", laptops);
		return "PageMagasin/laptop/laptopliste";
	}
	
	@GetMapping("/laptopmodif")
	private String laptopmodif(Model model , long id) {
		Optional<Laptop> laptops = laptopRepo.findById(id);
		model.addAttribute("laptop", laptops.get());
		return "PageMagasin/laptop/laptopmodif";
	}
	
	@GetMapping("/modelinsertion")
	private String modelinsertion(Model model) {
		List<Marque> marque = marqueRepo.findAll();
		model.addAttribute("marques", marque);
		return "PageMagasin/model/modelinsertion";
	}
	
	@GetMapping("/modelliste")
	private String modelliste(Model model) {
		List<ModelLaptop> mlaptop = modelLaptopRepo.findAll();
		model.addAttribute("models",mlaptop);
		return "PageMagasin/model/modelliste";
	}
	
	@GetMapping("/historique")
	private String historique(Model model) {
		List<HistoTransfere> histoTransferes = histoTransfereRepo.findAll();
		model.addAttribute("histo",histoTransferes);
		return "PageMagasin/transfere/historique";
	}
	
	@GetMapping("/transfere")
	private String transfere(Model model) {
		List<Laptop> laptops = laptopRepo.findAll();
		model.addAttribute("laptops", laptops);
		List<PointVente> vendeurs = pointVenteRepo.findAll();
		model.addAttribute("points",vendeurs);
		return "PageMagasin/transfere/transfere";
	}
	
	@GetMapping("/historeception")
	private String historeception(Model model) {
		List<HistoTransfere> histoTransferes = histoTransfereRepo.historeception();
		model.addAttribute("histo",histoTransferes);
		return "PagePointVente/reception/historeception";
	}
	
	@GetMapping("/reception")
	private String reception(Model model) {
		List<HistoTransfere> histoTransferes = histoTransfereRepo.findAll();
		model.addAttribute("histo",histoTransferes);
		return "PagePointVente/reception/reception";
	}
	
	@GetMapping("/renvoie")
	private String renvoie(Model model) {
		List<HistoTransfere> histoTransferes = histoTransfereRepo.historeception();
		model.addAttribute("histo",histoTransferes);
		return "PagePointVente/reception/renvoie";
	}
	
	@GetMapping("/listeVente")
	private String listeVente(Model model) {
		List<HistoVente> histoVentes = histoVenteRepo.findAll();
		model.addAttribute("vente", histoVentes);
		List<Laptop> laptops = laptopRepo.findAll();
		model.addAttribute("laptops", laptops);
 		return "PagePointVente/vente/listeVente";
	}
	
	@GetMapping("/vente")
	private String vente(Model model) {
		List<Laptop> laptops = laptopRepo.findAll();
		model.addAttribute("laptops", laptops);
		model.addAttribute("points",pointVenteRepo.findAll());
		return "PagePointVente/vente/vente";
	}
	
	@GetMapping("/stat")
	private String stat(Model model) {
		List<Laptop> laptops = laptopRepo.findAll();
		model.addAttribute("laptops", laptops);
		model.addAttribute("points",pointVenteRepo.findAll());
		List<HistoVente> vente = db.query("select EXTRACT(YEAR FROM u.date_vente) as annees , EXTRACT(MONTH FROM u.date_vente) as mois , sum(u.prixv - u.prixa) as sumv from HistoVente u GROUP BY EXTRACT(YEAR FROM u.date_vente), EXTRACT(MONTH FROM u.date_vente)",  new BeanPropertyRowMapper<HistoVente>(HistoVente.class));
		List<HistoVente> benef = db.query("select annees , mois , sum(sumv) as sumv from (\r\n"
				+ "select \r\n"
				+ "EXTRACT(YEAR FROM u.date_vente) as annees , \r\n"
				+ "EXTRACT(MONTH FROM u.date_vente) as mois , \r\n"
				+ "case \r\n"
				+ "    when l.perdu = false then 0\r\n"
				+ "    else (u.prixv-u.prixa) \r\n"
				+ "end as sumv\r\n"
				+ "from HistoVente u\r\n"
				+ "join laptop l on l.id = u.laptopid \r\n"
				+ " ) as v group by annees , mois",  new BeanPropertyRowMapper<HistoVente>(HistoVente.class));

		model.addAttribute("vente",vente);
		model.addAttribute("benef",benef);
		
		return "PagePointVente/stat/stat";
	}
	
	@PostMapping("/stat2")
	private String stat2(Model model , String point) {
		List<Laptop> laptops = laptopRepo.findAll();
		PointVente pointVente = pointVenteRepo.findByNom(point);
		model.addAttribute("laptops", laptops);
		model.addAttribute("points",pointVenteRepo.findAll());
		List<HistoVente> vente = db.query("select EXTRACT(YEAR FROM u.date_vente) as annees , EXTRACT(MONTH FROM u.date_vente) as mois , sum(u.prixv - u.prixa) as sumv from HistoVente u where u.pointventeid="+pointVente.getId()+" GROUP BY EXTRACT(YEAR FROM u.date_vente), EXTRACT(MONTH FROM u.date_vente)",  new BeanPropertyRowMapper<HistoVente>(HistoVente.class));
		List<HistoVente> benef = db.query("select annees , mois , sum(sumv) as sumv from (\r\n"
				+ "select \r\n"
				+ "EXTRACT(YEAR FROM u.date_vente) as annees , \r\n"
				+ "EXTRACT(MONTH FROM u.date_vente) as mois , \r\n"
				+ "case \r\n"
				+ "    when l.perdu = false then 0\r\n"
				+ "    else (u.prixv-u.prixa) \r\n"
				+ "end as sumv\r\n"
				+ "from HistoVente u\r\n"
				+ "join laptop l on l.id = u.laptopid \r\n"
				+ "where u.pointventeid="+pointVente.getId()+" ) as v group by annees , mois",  new BeanPropertyRowMapper<HistoVente>(HistoVente.class));

		model.addAttribute("vente",vente);
		model.addAttribute("benef",benef);
		return "PagePointVente/stat/stat";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
