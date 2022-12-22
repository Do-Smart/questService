package com.dosmart.questService.services;

import com.dosmart.questService.dtos.CompanyDetailsDto;
import com.dosmart.questService.model.CompanyDetails;

public interface BaseService<T> {
    T save(CompanyDetails details);
}
