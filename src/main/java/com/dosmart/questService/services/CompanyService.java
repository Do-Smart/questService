package com.dosmart.questService.services;

import com.dosmart.questService.model.CompanyDetails;

import java.util.List;

public interface CompanyService {
    List<CompanyDetails> fetchAllCompanies();
    List<CompanyDetails> fetchHighRecruitment();
    List<CompanyDetails> fetchByHighLpa();
}
