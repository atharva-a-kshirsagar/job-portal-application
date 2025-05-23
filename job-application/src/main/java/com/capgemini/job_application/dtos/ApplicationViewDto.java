package com.capgemini.job_application.dtos;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationViewDto {
    private Long applicationId;
    private String status;
    private LocalDate appliedDate;
    private Long jobId;
    private String jobTitle;
    private String companyName;
    private String jobLocation;
    private Double salary;

}


