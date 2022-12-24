package com.dosmart.questService.services;

import com.dosmart.questService.dtos.CompanyDetailsDto;
import com.dosmart.questService.model.CompanyDetails;

public interface BaseService<T> {
    T save(CompanyDetails details);
    T delete(String companyName, String location);
    T modify(String companyName, String location,CompanyDetails companyDetails);
}
