package com.capgemini.job_application;
import com.capgemini.job_application.controllers.CompanyController;
import com.capgemini.job_application.entities.Company;
import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.services.CompanyService;
import com.capgemini.job_application.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    @Mock
    private UserService userService;

    @InjectMocks
    private CompanyController companyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a new company")
    void shouldCreateCompany() {
        User user = new User();
        user.setUserId(100L);
        Company company = new Company(1L, user, "Capgemini", "IT", "Pune");

        when(companyService.createCompany(any())).thenReturn(company);

        // Simulate valid input (BindingResult has no errors)
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Company> response = companyController.createCompany(company, bindingResult);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCompanyName()).isEqualTo("Capgemini");
    }

    @Test
    @DisplayName("Should get company by ID")
    void shouldGetCompanyById() {
        User user = new User();
        user.setUserId(101L);
        Company company = new Company(1L, user, "Infosys", "IT", "Bangalore");

        when(companyService.getCompanyById(1L)).thenReturn(company);

        ResponseEntity<Company> response = companyController.getCompany(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCompanyName()).isEqualTo("Infosys");
        assertThat(response.getBody().getHeadOffice()).isEqualTo("Bangalore");
    }

    @Test
    @DisplayName("Should return all companies")
    void shouldGetAllCompanies() {
        User user1 = new User();
        user1.setUserId(1L);
        User user2 = new User();
        user2.setUserId(2L);
        Company c1 = new Company(1L, user1, "A", "X", "Y");
        Company c2 = new Company(2L, user2, "B", "Y", "Z");

        when(companyService.getAllCompanies()).thenReturn(List.of(c1, c2));

        ResponseEntity<List<Company>> response = companyController.getAllCompanies();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody().get(0).getCompanyName()).isEqualTo("A");
        assertThat(response.getBody().get(1).getCompanyDomain()).isEqualTo("Y");
    }

    @Test
    @DisplayName("Should update company with PUT")
    void shouldUpdateCompany() {
        User user = new User();
        user.setUserId(200L);
        Company updated = new Company(1L, user, "UpdatedCo", "NewDomain", "NewHQ");

        when(companyService.updateCompany(eq(1L), any())).thenReturn(updated);

        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Company> response = companyController.updateCompany(1L, updated, bindingResult);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCompanyName()).isEqualTo("UpdatedCo");
        assertThat(response.getBody().getCompanyDomain()).isEqualTo("NewDomain");
    }

    @Test
    @DisplayName("Should delete company")
    void shouldDeleteCompany() {
        doNothing().when(companyService).deleteCompany(1L);

        ResponseEntity<Void> response = companyController.deleteCompany(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}