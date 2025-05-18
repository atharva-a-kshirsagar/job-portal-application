package com.capgemini.job_application.services;

import com.capgemini.job_application.repositories.ApplicationRepository;
import com.capgemini.job_application.repositories.JobRepository;
import com.capgemini.job_application.dtos.ChartDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    public ChartService(ApplicationRepository applicationRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    public List<ChartDTO> getApplicationsByLocationForUser(Long userId) {
        return applicationRepository.countApplicationsByLocationForUser(userId)
            .stream()
            .map(obj -> new ChartDTO((String) obj[0], (Long) obj[1]))
            .collect(Collectors.toList());
    }

    public List<ChartDTO> getApplicationsByJobTitleForUser(Long userId) {
        return applicationRepository.countApplicationsByJobTitleForUser(userId)
            .stream()
            .map(obj -> new ChartDTO((String) obj[0], (Long) obj[1]))
            .collect(Collectors.toList());
    }

    public List<ChartDTO> getJobsByCompanyDomainForUser(Long userId) {
        return jobRepository.countJobsByCompanyDomainForUser(userId)
            .stream()
            .map(obj -> new ChartDTO((String) obj[0], (Long) obj[1]))
            .collect(Collectors.toList());
    }

}
