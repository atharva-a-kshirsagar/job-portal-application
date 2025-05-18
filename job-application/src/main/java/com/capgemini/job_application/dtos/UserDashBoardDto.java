package com.capgemini.job_application.dtos;

import java.util.List;

import com.capgemini.job_application.entities.Job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDashBoardDto {

	// cont by 
	private Long appliedCount;
	private Long shortListed;
	private Long rejected;
	private Long offered;
	
	List<JobDto> jobsToApply;
	
}
