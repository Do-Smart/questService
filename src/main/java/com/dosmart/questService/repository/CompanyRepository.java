package com.dosmart.questService.repository;

import com.dosmart.questService.model.CompanyDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CompanyRepository extends MongoRepository<CompanyDetails,String> {
    Optional<CompanyDetails> findByCompanyNameAndLocation(String companyName,String location);
}
