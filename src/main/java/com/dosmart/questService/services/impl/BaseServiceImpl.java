package com.dosmart.questService.services.impl;

import com.dosmart.questService.dtos.CompanyDetailsDto;
import com.dosmart.questService.model.CompanyDetails;
import com.dosmart.questService.repository.CompanyRepository;
import com.dosmart.questService.services.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BaseServiceImpl implements BaseService<CompanyDetails> {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public CompanyDetails save(CompanyDetails details) {
        details.setLocation(details.getLocation().substring(0,1).toUpperCase() + details.getLocation().substring(1).toLowerCase());
        details.setCompanyName(details.getCompanyName().substring(0,1).toUpperCase() + details.getCompanyName().substring(1).toLowerCase());
        Optional<CompanyDetails> optionalCompanyDetails = companyRepository.findByCompanyNameAndLocation(details.getCompanyName(),details.getLocation());
        if(optionalCompanyDetails.isPresent())
        {
            return null;
        }
        return companyRepository.save(details);

    }

    @Override
    public CompanyDetails delete(String companyName, String location) {
        Optional<CompanyDetails> optionalCompanyDetails = companyRepository.findByCompanyNameAndLocation(companyName,location);
        if(optionalCompanyDetails.isPresent())
        {
            companyRepository.delete(optionalCompanyDetails.get());
        }
        return optionalCompanyDetails.get();
    }

    @Override
    public CompanyDetails modify(String companyName, String location,CompanyDetails companyDetails) {
        Optional<CompanyDetails> optionalCompanyDetails = companyRepository.findByCompanyNameAndLocation(companyName,location);
        if(optionalCompanyDetails.isPresent())
        {
            companyRepository.delete(optionalCompanyDetails.get());
            BeanUtils.copyProperties(companyDetails,optionalCompanyDetails.get());
            return companyRepository.save(optionalCompanyDetails.get());
        }
        return null;
    }
}
