package com.dosmart.questService.repository;

import com.dosmart.questService.model.CompanyDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<CompanyDetails,String> {
}
