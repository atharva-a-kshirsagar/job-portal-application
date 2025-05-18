package com.capgemini.job_application.services;

import com.capgemini.job_application.entities.Company;
import com.capgemini.job_application.repositories.CompanyRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {


	private final CompanyRepository companyRepository;
	
	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getAllCompanies() {
		log.debug("Fetching all companies from the repository");
		return companyRepository.findAll();
	}

	@Override
	public Company getCompanyById(Long id) {
		log.debug("Fetching company by ID: {}", id);
		return companyRepository.findById(id).orElseThrow(() -> {
			log.warn("Company not found with ID: {}", id);
			return new RuntimeException("Company not found with ID: " + id);
		});
	}

	@Override
	public Company createCompany(Company company) {
		log.debug("Saving new company: {}", company);
		return companyRepository.save(company);
	}

	@Override
	public Company updateCompany(Long id, Company company) {
		log.debug("Updating company with ID: {}", id);
		Company existing = getCompanyById(id);
		existing.setCompanyName(company.getCompanyName());
		existing.setUser(company.getUser());
		existing.setCompanyDomain(company.getCompanyDomain());
		existing.setHeadOffice(company.getHeadOffice());
		Company updated = companyRepository.save(existing);
		log.debug("Updated company: {}", updated);
		return updated;
	}

	@Override
	public Company patchCompany(Long id, Company company) {
		log.debug("Patching company with ID: {}", id);
		Company existing = getCompanyById(id);
		if (company.getCompanyName() != null) {
			log.debug("Updating company name to: {}", company.getCompanyName());
			existing.setCompanyName(company.getCompanyName());
		}
		if (company.getUser() != null) {
			log.debug("Updating user ID to: {}", company.getUser());
			existing.setUser(company.getUser());
		}
		if (company.getCompanyDomain() != null) {
			log.debug("Updating domain to: {}", company.getCompanyDomain());
			existing.setCompanyDomain(company.getCompanyDomain());
		}
		if (company.getHeadOffice() != null) {
			log.debug("Updating head office to: {}", company.getHeadOffice());
			existing.setHeadOffice(company.getHeadOffice());
		}
		Company patched = companyRepository.save(existing);
		log.debug("Patched company: {}", patched);
		return patched;
	}

	@Override
	public void deleteCompany(Long id) {
		log.debug("Deleting company with ID: {}", id);
		if (!companyRepository.existsById(id)) {
			log.warn("Company not found with ID: {}", id);
			throw new RuntimeException("Company not found with ID: " + id);
		}
		companyRepository.deleteById(id);
	}

	@Override
	public Company getCompanyByUserId(Long userId) {
		log.debug("Fetching company by user ID: {}", userId);
		return companyRepository.findByUserId(userId).orElseThrow(() -> {
			log.warn("Company not found with ID: {}", userId);
			return new RuntimeException("Company not found with user ID: " + userId);
		});
	}

}
