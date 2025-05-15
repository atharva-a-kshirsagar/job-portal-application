package com.capgemini.job_application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.job_application.entities.Company;
import com.capgemini.job_application.repositories.CompanyRepository;
import com.capgemini.job_application.services.CompanyServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

	@Mock
	private CompanyRepository companyRepository;

	@InjectMocks
	private CompanyServiceImpl companyService;

	@Test
	@DisplayName("Should return all companies")
	void shouldReturnAllCompanies() {
		Company company1 = new Company(1L, 10L, "ABC Corp", "Tech", "Pune");
		Company company2 = new Company(2L, 11L, "XYZ Inc", "Finance", "Mumbai");

		Mockito.when(companyRepository.findAll()).thenReturn(Arrays.asList(company1, company2));

		List<Company> companies = companyService.getAllCompanies();

		assertEquals(2, companies.size());
		assertEquals("ABC Corp", companies.get(0).getCompanyName());
	}

	@Test
	@DisplayName("Should return company by ID")
	void shouldReturnCompanyById() {
		Company company = new Company(1L, 10L, "ABC Corp", "Tech", "Pune");
		Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

		Company found = companyService.getCompanyById(1L);

		assertNotNull(found);
		assertEquals("ABC Corp", found.getCompanyName());
	}

	@Test
	@DisplayName("Should create a company")
	void shouldCreateCompany() {
		Company company = new Company(1L, 10L, "ABC Corp", "Tech", "Pune");
		Mockito.when(companyRepository.save(company)).thenReturn(company);

		Company saved = companyService.createCompany(company);

		assertEquals("ABC Corp", saved.getCompanyName());
	}

	@Test
	@DisplayName("Should update a company")
	void shouldUpdateCompany() {
		Company old = new Company(1L, 10L, "OldName", "OldDomain", "OldOffice");
		Company updated = new Company(1L, 20L, "NewName", "NewDomain", "NewOffice");

		Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(old));
		Mockito.when(companyRepository.save(Mockito.any())).thenReturn(updated);

		Company result = companyService.updateCompany(1L, updated);

		assertEquals("NewName", result.getCompanyName());
		assertEquals("NewDomain", result.getCompanyDomain());
	}

	@Test
	@DisplayName("Should patch a company")
	void shouldPatchCompany() {
		Company existing = new Company(1L, 10L, "ABC", "Tech", "Pune");
		Company patch = new Company();
		patch.setCompanyDomain("Finance");

		Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(existing));
		Mockito.when(companyRepository.save(Mockito.any())).thenReturn(existing);

		Company result = companyService.patchCompany(1L, patch);

		assertEquals("Finance", result.getCompanyDomain());
		assertEquals("ABC", result.getCompanyName()); 
	}

	@Test
	@DisplayName("Should delete a company")
	void shouldDeleteCompany() {
		Mockito.when(companyRepository.existsById(1L)).thenReturn(true);

		companyService.deleteCompany(1L);

		Mockito.verify(companyRepository).deleteById(1L);
	}
}
