package com.mic.limule.controller;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.mic.limule.model.Admin;
import com.mic.limule.model.HistoTransfere;
import com.mic.limule.model.HistoVente;
import com.mic.limule.model.ImgLaptop;
import com.mic.limule.model.Laptop;
import com.mic.limule.model.Magasin;
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
@RestController
public class ServiceController {

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
	
	String baseUrl = "http://localhost:8080";
	
	@GetMapping("/getadmin")
	private List<Admin> getAllAdmin() {
		return adminRepo.findAll();
	}
	
	@PostMapping("/setadmin")
	private void setAdmin(String nom , String mdp) throws NoSuchAlgorithmException {
		Admin admin = new Admin();
		admin.setMdp(mdp);
		admin.setNom(nom);
		adminRepo.save(admin);
	}
	
	@GetMapping("/gethistotransfere")
	private List<HistoTransfere> getAllHistoTransfere() {
		return histoTransfereRepo.findAll();
	}
	
	@PostMapping("/settransfere")
	private void transfere(long pointventeid,long laptopid) {
		Optional<PointVente> pointVente = pointVenteRepo.findById(pointventeid);
		Optional<Laptop> laptop = laptopRepo.findById(laptopid);
		HistoTransfere histoTransfere = new HistoTransfere();
		LocalDate dateTransfere = LocalDate.now();
		UUID uuid = UUID.randomUUID();
		histoTransfere.setUidFacture(uuid.toString());
		histoTransfere.setLaptop(laptop.get());
		histoTransfere.setPointvente(pointVente.get());
		histoTransfere.setDateTransfere(dateTransfere);
	}
	
	@PostMapping("/settrans")
	private RedirectView transfere(String data) {
		String[] split = data.split("--");
		UUID uuid = UUID.randomUUID();
		String iid = uuid.toString();
		LocalDate dateTransfere = LocalDate.now();
		HistoTransfere histoTransfere = new HistoTransfere();
		String point = "";
		List<String> id = new ArrayList<String>();
		for (int i = 0; i < split.length; i++) {
			if(i==split.length -1) {
				String[] last = split[i].split("/&");
				point = last[1];
				id.add(last[0]);
			}
			else {
				id.add(split[i]);
			}
		}
		PointVente pointVente = pointVenteRepo.findByNom(point);
		Optional<Laptop> laptop;
		for (String string : id) {
			String[] i = string.split("-&");
			int qts = Integer.parseInt(i[1]);
			laptop = laptopRepo.findById(Long.parseLong(i[0]));
			
			for (int j = 0; j < qts; j++) {
				histoTransfere = new HistoTransfere();
				histoTransfere.setUidFacture(uuid.toString());
				histoTransfere.setLaptop(laptop.get());
				histoTransfere.setPointvente(pointVente);
				histoTransfere.setDateTransfere(dateTransfere);
				histoTransfereRepo.save(histoTransfere);
			}
			
		}
		return new RedirectView(baseUrl+"/historique");
	}
	
	@PostMapping("/setrecp")
	private void reception(String data) {
		String[] split = data.split("--");
		UUID uuid = UUID.randomUUID();
		String iid = uuid.toString();
		LocalDate dateTransfere = LocalDate.now();
		HistoTransfere histoTransfere = new HistoTransfere();
		List<String> id = new ArrayList<String>();
		for (int i = 0; i < split.length; i++) {
			id.add(split[i]);
			
		}
//		Optional<Laptop> laptop;
//		for (String string : id) {
//			String[] i = string.split("-&");
//			int qts = Integer.parseInt(i[1]);
//			laptop = laptopRepo.findById(Long.parseLong(i[0]));
//			
//			for (int j = 0; j < qts; j++) {
//				histoTransfere = histoTransfereRepo.findByUidFacture(iid);
//				histoTransfere.setLaptop(laptop.get());
//				histoTransfere.setDateTransfere(dateTransfere);
//				histoTransfereRepo.save(histoTransfere);
//			}
//			
//		}
	}
	
	@PostMapping("/setreception")
	private void reception(String id,LocalDate dateReception) {
		HistoTransfere histoTransfere = histoTransfereRepo.findByUidFacture(id);
		histoTransfere.setDateReception(dateReception);
		histoTransfereRepo.save(histoTransfere);
	}
	
	@GetMapping("/gethistovente")
	private List<HistoVente> getAllHistoVente() {
		return histoVenteRepo.findAll();
	}
	
	@PostMapping("/setvente")
	private void vente(Long[] laptopid,String nomclient,Long pointVenteId) {
		List<Long> listId = new ArrayList<Long>();
		listId.addAll(Arrays.asList(laptopid));
		List<Laptop> listLaptop = laptopRepo.findAllById(listId);
		List<HistoVente> listHistoVente = new ArrayList<HistoVente>();
		LocalDate now = LocalDate.now();
		Optional<PointVente> pointvente = pointVenteRepo.findById(pointVenteId);
		UUID uuid = UUID.randomUUID();
		for (Laptop laptop : listLaptop) {
			laptop.setVendu(true);
			listHistoVente.add(new HistoVente(laptop, now, nomclient,uuid.toString() , pointvente.get()));
		}
		histoVenteRepo.saveAll(listHistoVente);
	}
	
	@GetMapping("/imglaptop")
	private List<ImgLaptop> getAllImg() {
		return imgLaptopRepo.findAll();
	}
	
	@PostMapping("/addimg")
	private void addImg(String base64 , long id) {
		Optional<Laptop> laptop = laptopRepo.findById(id);
		ImgLaptop imgLaptop = new ImgLaptop();
		imgLaptop.setBase64(base64);
		imgLaptop.setLaptop(laptop.get());
		imgLaptopRepo.save(imgLaptop);
	}
	
	@GetMapping("/getlaptop")
	private List<Laptop> getAllLaptop() {
		return laptopRepo.findAll();
	}
	
	@PostMapping("/addlaptop")
	private RedirectView setLaptop(String modelid,String nom , String ram , int rom , float pouce , String couleur , float prix) {
		Laptop laptop = new Laptop();
		ModelLaptop model = modelLaptopRepo.findByNom(modelid);
		laptop.setCouleur(couleur);
		laptop.setEnvente(false);
		laptop.setModellaptop(model);
		laptop.setNom(nom);
		laptop.setPouce(pouce);
		laptop.setPrix(prix);
		laptop.setRam(ram);
		laptop.setRom(rom);
		laptop.setVendu(false);
		laptopRepo.save(laptop);
		return new RedirectView(baseUrl+"/laptopinsertion");
	}
	
	@GetMapping("/deletelaptop")
	private RedirectView deletelaptop(Long id) {
		laptopRepo.deleteById(id);
		return new RedirectView(baseUrl+"/laptopliste");
	}
	
	@PostMapping("/modiflaptop")
	private RedirectView modifLaptop(Long id ,String modelid,String nom , String ram , int rom , float pouce , String couleur , float prix) {
		Laptop laptop = new Laptop();
		ModelLaptop model = modelLaptopRepo.findByNom(modelid);
		laptop.setId(id);
		laptop.setCouleur(couleur);
		laptop.setEnvente(false);
		laptop.setModellaptop(model);
		laptop.setNom(nom);
		laptop.setPouce(pouce);
		laptop.setPrix(prix);
		laptop.setRam(ram);
		laptop.setRom(rom);
		laptop.setVendu(false);
		laptopRepo.save(laptop);
		return new RedirectView(baseUrl+"/laptopliste");
	}
	
	@GetMapping("/perdu")
	private RedirectView perdu(Long id ,String uid ) {
		Optional<Laptop> lap = laptopRepo.findById(id);
		HistoTransfere histoTransfere = histoTransfereRepo.findByUidFacture(uid);
		Laptop laptop = lap.get();
		laptop.setPerdu(true);
		laptopRepo.save(laptop);
		System.out.println(uid);
		histoTransfere.setPerdu(true);
		histoTransfereRepo.save(histoTransfere);
		return new RedirectView(baseUrl+"/reception");
	}
	@GetMapping("/recpt")
	private RedirectView reception(Long id , String uid) {
		Optional<Laptop> lap = laptopRepo.findById(id);
		HistoTransfere histoTransfere = histoTransfereRepo.findByUidFacture(uid);
		Laptop laptop = lap.get();
		laptop.setEnvente(true);
		laptopRepo.save(laptop);
		System.out.println(uid);
		histoTransfere.setDateReception(LocalDate.now());
		histoTransfereRepo.save(histoTransfere);
		return new RedirectView(baseUrl+"/reception");
	}
	@GetMapping("/renvoi")
	private RedirectView renvoie(String id) {
		HistoTransfere histoTransfere = histoTransfereRepo.findByUidFacture(id);
		System.out.println(id);
		histoTransfere.setRenvoie(true);
		histoTransfereRepo.save(histoTransfere);
		return new RedirectView(baseUrl+"/reception");
	}
	
	@GetMapping("/getmagasin")
	private List<Magasin> getAllMagasin() {
		return magasinRepo.findAll();
	}
	
	@PostMapping("/addmagasin")
	private void addMagasin(String nom) {
		Magasin magasin = new Magasin();
		magasin.setNom(nom);
		magasinRepo.save(magasin);
	}
	
	@GetMapping("/getmarque")
	private List<Marque> getAllMarque() {
		return marqueRepo.findAll();
	}
	
	@PostMapping("/addmarque")
	private void addMarque(String nom) {
		Marque marque = new Marque();
		marque.setNom(nom);
		marqueRepo.save(marque);
	}
	
	@GetMapping("/getmodellaptop")
	private List<ModelLaptop> getAllModel() {
		return modelLaptopRepo.findAll();
	}
	
	@PostMapping("/addmodel")
	private RedirectView addModel(String nom , String marqueid , String desc, String processeur) {
		ModelLaptop modelLaptop = new ModelLaptop();
		Marque marque = marqueRepo.findByNom(marqueid);
		modelLaptop.setDescription(desc);
		modelLaptop.setNom(nom);
		modelLaptop.setProcesseur(processeur);
		modelLaptop.setMarque(marque);
		modelLaptopRepo.save(modelLaptop);
		return new RedirectView(baseUrl+"/modelinsertion");
	}
	
	@GetMapping("/getpointvente")
	private List<PointVente> getAllPointVente() {
		return pointVenteRepo.findAll();
	}
	
	@PostMapping("/addpointvente")
	private RedirectView addPointVente(String nom , float distance) {
		PointVente pointVente = new PointVente();
		pointVente.setDistance(distance);
		pointVente.setNom(nom);
		pointVenteRepo.save(pointVente);
		return new RedirectView(baseUrl+"/listepoint");
	}
	
	@PostMapping("/addvendeur")
	private RedirectView addVendeur(String nom,String mdp) throws NoSuchAlgorithmException {
		Vendeur vendeur = new Vendeur();
		PointVente pointVente = pointVenteRepo.findByNom("magasin");
		vendeur.setMdp(mdp);
		vendeur.setNom(nom);
		vendeur.setPointvente(pointVente);
		vendeurRepo.save(vendeur);
		return new RedirectView(baseUrl+"/liste");
	}
	
	@GetMapping("/deletevendeur")
	private RedirectView deletevendeur(Long id) {
		vendeurRepo.deleteById(id);
		return new RedirectView(baseUrl+"/liste");
	}
	@GetMapping("/deletepoint")
	private RedirectView dpoint(Long id) {
		pointVenteRepo.deleteById(id);
		return new RedirectView(baseUrl+"/listepoint");
	}
	
	@GetMapping("/getvendeur")
	private List<Vendeur> getAllVendeur() {
		return vendeurRepo.findAll();
	}
	
	@PostMapping("/affectionvendeur")
	private RedirectView affectionvendeur(String employe , String point){
		Vendeur vendeur = vendeurRepo.findByNom(employe);
		PointVente pointVente = pointVenteRepo.findByNom(point);
		vendeur.setPointvente(pointVente);
		vendeurRepo.save(vendeur);
		return new RedirectView(baseUrl+"/liste");
	}
	
	@PostMapping("/vent")
	private RedirectView vent(String id,String point) {
		Optional<Laptop> lap = laptopRepo.findById(Long.parseLong(id));
		Laptop laptop = lap.get();
		PointVente pointVente = pointVenteRepo.findByNom(point);
		laptop.setVendu(true);
		laptopRepo.save(laptop);
		HistoVente histoVente = new HistoVente();
		UUID uuid = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();
		histoVente.setLaptop(laptop);
		histoVente.setPointvente(pointVente);
		histoVente.setDateVente(LocalDate.now());
		histoVente.setNomClient(uuid.toString());
		histoVente.setUidFacture(uuid2.toString());
		histoVenteRepo.save(histoVente);
		return new RedirectView(baseUrl+"/vente");
	}
	
}
