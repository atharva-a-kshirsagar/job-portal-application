package com.capgemini.job_application.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.job_application.entities.Company;
import com.capgemini.job_application.services.CompanyService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private final CompanyService companyService;

	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> companies = companyService.getAllCompanies();
		return ResponseEntity.status(HttpStatus.OK).body(companies);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompany(@PathVariable Long id) {
		return ResponseEntity.ok(companyService.getCompanyById(id));
	}

	@PostMapping
	public ResponseEntity<Company> createCompany(@RequestBody Company company) {
		Company saved = companyService.createCompany(company);
		return ResponseEntity.created(URI.create("/api/companies/" + saved.getCompanyId())).body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
		return ResponseEntity.ok(companyService.updateCompany(id, company));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Company> patchCompany(@PathVariable Long id, @RequestBody Company company) {
		return ResponseEntity.ok(companyService.patchCompany(id, company));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
		companyService.deleteCompany(id);
		return ResponseEntity.noContent().build();
	}

}
