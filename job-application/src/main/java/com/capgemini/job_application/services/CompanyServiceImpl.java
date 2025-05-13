package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.Company;
import com.capgemini.job_application.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;

	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Company getCompanyById(Long id) {
		return companyRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Company not found with ID: " + id));
	}

	@Override
	public Company createCompany(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public Company updateCompany(Long id, Company company) {
		Company existing = getCompanyById(id);
		existing.setCompName(company.getCompName());
		existing.setCompLocation(company.getCompLocation());
		existing.setCompEmail(company.getCompEmail());
		return companyRepository.save(existing);
	}

	@Override
	public Company patchCompany(Long id, Company company) {
		Company existing = getCompanyById(id);
		if (company.getCompName() != null) {
			existing.setCompName(company.getCompName());
		}
		if (company.getCompLocation() != null) {
			existing.setCompLocation(company.getCompLocation());
		}
		if (company.getCompEmail() != null) {
			existing.setCompEmail(company.getCompEmail());
		}
		return companyRepository.save(existing);
	}

	@Override
	public void deleteCompany(Long id) {
		if (!companyRepository.existsById(id)) {
			throw new RuntimeException("Company not found with ID: " + id);
		}
		companyRepository.deleteById(id);
	}

}
