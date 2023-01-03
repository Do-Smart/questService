package com.dosmart.questService.services.impl;

import com.dosmart.questService.model.CompanyDetails;
import com.dosmart.questService.repository.CompanyRepository;
import com.dosmart.questService.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Override
    public List<CompanyDetails> fetchAllCompanies() {
        List<CompanyDetails> optionalCompanyDetailsList = companyRepository.findAll();
        optionalCompanyDetailsList.sort((company1,company2) -> company1.getCompanyName().compareTo(company2.getCompanyName()));
        return optionalCompanyDetailsList;
    }

    @Override
    public List<CompanyDetails> fetchHighRecruitment() {
        List<CompanyDetails> companyDetailsList = companyRepository.findAll(Sort.by(Sort.Direction.DESC,"totalRecruitment"));
        return companyDetailsList;
    }

    @Override
    public List<CompanyDetails> fetchByHighLpa() {
        List<CompanyDetails> companyDetailsList = companyRepository.findAll(Sort.by(Sort.Direction.DESC, "CTC"));
        return companyDetailsList;
    }
}
