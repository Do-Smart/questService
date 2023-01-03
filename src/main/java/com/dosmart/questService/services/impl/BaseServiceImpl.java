package com.dosmart.questService.services.impl;

import com.dosmart.questService.dtos.AdminDetails;
import com.dosmart.questService.model.CompanyDetails;
import com.dosmart.questService.repository.CompanyRepository;
import com.dosmart.questService.services.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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
        AtomicInteger totalRecruitment= new AtomicInteger(0);
        details.getStudentHiredYearWise().forEach((key,value) -> totalRecruitment.updateAndGet(v -> v + value));
        details.setTotalRecruitment(totalRecruitment.intValue());
        details.setEntryModifiedDate(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date()));
        return companyRepository.save(details);

    }

    @Override
    public CompanyDetails delete(String companyName, String location) {
        Optional<CompanyDetails> optionalCompanyDetails = companyRepository.findByCompanyNameAndLocation(companyName,location);
        CompanyDetails companyDetails = null;
        if(optionalCompanyDetails.isPresent())
        {
            companyDetails = optionalCompanyDetails.get();
            companyRepository.delete(companyDetails);
        }
        return companyDetails;
    }

    @Override
    public CompanyDetails modify(String companyName, String location, CompanyDetails companyDetails, AdminDetails adminDetails) {
        Optional<CompanyDetails> optionalCompanyDetails = companyRepository.findByCompanyNameAndLocation(companyName,location);

        if(optionalCompanyDetails.isPresent()){
            AtomicInteger totalRecruitment= new AtomicInteger(0);
            companyRepository.delete(optionalCompanyDetails.get());
            BeanUtils.copyProperties(companyDetails,optionalCompanyDetails.get());
            optionalCompanyDetails.get().setEntryAddedBy(adminDetails);
            optionalCompanyDetails.get().getStudentHiredYearWise().forEach((key,value) -> totalRecruitment.updateAndGet(v -> v + value));
            optionalCompanyDetails.get().setTotalRecruitment(totalRecruitment.intValue());
            optionalCompanyDetails.get().setEntryModifiedDate(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date()));
            return companyRepository.save(optionalCompanyDetails.get());
        }
        return null;
    }
}
