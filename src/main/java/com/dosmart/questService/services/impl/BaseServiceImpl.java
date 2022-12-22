package com.dosmart.questService.services.impl;

import com.dosmart.questService.dtos.CompanyDetailsDto;
import com.dosmart.questService.model.CompanyDetails;
import com.dosmart.questService.repository.CompanyRepository;
import com.dosmart.questService.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BaseServiceImpl implements BaseService<CompanyDetails> {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public CompanyDetails save(CompanyDetails details) {
        Optional<CompanyDetails> optionalCompanyDetails = companyRepository.findByCompanyName(details.getCompanyName());
        if(optionalCompanyDetails.isPresent())
        {
            return null;
        }
        return companyRepository.save(details);

    }
}
