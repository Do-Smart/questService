package com.dosmart.questService.controller;

import com.dosmart.questService.dtos.AdminDetails;
import com.dosmart.questService.dtos.BaseResponse;
import com.dosmart.questService.model.CompanyDetails;
import com.dosmart.questService.services.BaseService;
import com.dosmart.questService.services.CompanyService;
import com.dosmart.questService.utils.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.dosmart.questService.utils.Constants.AUTHORIZATION;
import static com.dosmart.questService.utils.Urls.*;

@RestController
@CrossOrigin("*")
@RequestMapping(COMPANY_URL)
public class CompanyController {

    @Autowired
    BaseService<CompanyDetails> baseService;

    @Autowired
    CompanyService companyService;

    @Autowired
    TokenValidator tokenValidator;

    //todo: Create Own exception to handle all kind of error.
    @PostMapping(SAVE_COMPANY)
    public BaseResponse<CompanyDetails> saveNewCompany(@RequestBody CompanyDetails companyDetails, @RequestHeader("Authorization") String token)
    {
        try {
            Integer id = tokenValidator.validateByToken(token);
            AdminDetails adminDetails = tokenValidator.getUserById(token,id);
            if(adminDetails.isAuthority()) {
                companyDetails.setEntryAddedBy(adminDetails);
                CompanyDetails details = baseService.save(companyDetails);
                if (Objects.nonNull(details)) {
                    return new BaseResponse<>("Added Successfully", HttpStatus.OK.value(), true, "", details);
                } else {
                    return new BaseResponse<>("Already Exists", HttpStatus.ALREADY_REPORTED.value(), false, "", null);
                }
            }
            else{
                return new BaseResponse<>("Not Authorized User",HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), false,"User not authorized",null);
            }
        }
        catch (Exception exception)
        {
            BaseResponse<CompanyDetails> baseResponse = new BaseResponse<>(exception.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, exception.getMessage(), null);
            if (baseResponse.getError().contains("401")) {
                baseResponse.setCode(401);
            }
            return baseResponse;
        }
    }

    @DeleteMapping(DELETE_COMPANY + "{companyName}/{location}")
    public BaseResponse<CompanyDetails> deleteCompany(@PathVariable("companyName") String companyName, @PathVariable("location") String location, @RequestHeader(AUTHORIZATION) String token)
    {
        try{
            Integer id = tokenValidator.validateByToken(token);
            AdminDetails adminDetails = tokenValidator.getUserById(token,id);
            if(adminDetails.isAuthority()) {
                CompanyDetails details = baseService.delete(companyName, location);
                if (Objects.nonNull(details)) {
                    return new BaseResponse<>("Deleted Successfully", HttpStatus.OK.value(), true, "", details);
                }
                return new BaseResponse<>("Delete unSuccessful", HttpStatus.NO_CONTENT.value(), false, "Company not found", null);
            }
            else{
                return new BaseResponse<>("Not Authorized User",HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), false,"User not authorized",null);
            }
        }
        catch (Exception exception)
        {
            BaseResponse<CompanyDetails> baseResponse = new BaseResponse<>(exception.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, exception.getMessage(), null);
            if (baseResponse.getError().contains("401")) {
                baseResponse.setCode(401);
            }
            return baseResponse;
        }
    }
    @PutMapping(MODIFY + "{companyName}/{location}")
    public BaseResponse<CompanyDetails> modifyCompany(@PathVariable("companyName") String companyName, @PathVariable("location") String location, @RequestHeader(AUTHORIZATION) String token, @RequestBody CompanyDetails companyDetails)
    {
        try {
            Integer id = tokenValidator.validateByToken(token);
            AdminDetails adminDetails = tokenValidator.getUserById(token,id);
            if(adminDetails.isAuthority()) {
                CompanyDetails details = baseService.modify(companyName, location, companyDetails, adminDetails);
                if (Objects.nonNull(details)) {
                    return new BaseResponse<>("Modified", HttpStatus.OK.value(), true, "", details);
                }
                return new BaseResponse<>("Modification unSuccessful", HttpStatus.NO_CONTENT.value(), false, "Company Not found", null);
            }
            else{
                return new BaseResponse<>("Not Authorized User",HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), false,"User not authorized",null);
            }
        }
        catch (Exception exception) {
            BaseResponse<CompanyDetails> baseResponse = new BaseResponse<>(exception.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, exception.getMessage(), null);
            if (baseResponse.getError().contains("401")) {
                baseResponse.setCode(401);
            }
            return baseResponse;
        }
    }
    @GetMapping(LIST_ALL_COMPANIES)
    public BaseResponse<List<CompanyDetails>> getAllCompanies(@RequestHeader(AUTHORIZATION) String token)
    {
        try {
            tokenValidator.validateByToken(token);
            return new BaseResponse<>("Companies in Alphabetic order",HttpStatus.OK.value(), true,"",companyService.fetchAllCompanies());
        }
        catch (Exception exception)
        {
            BaseResponse<List<CompanyDetails>> baseResponse = new BaseResponse<>(exception.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, exception.getMessage(), null);
            if (baseResponse.getError().contains("401")) {
                baseResponse.setCode(401);
            }
            return baseResponse;
        }
    }
    @GetMapping("/list/high-lpa/all")
    public BaseResponse<List<CompanyDetails>> getHighLpaCompany(@RequestHeader(AUTHORIZATION) String token)
    {
        try{
            tokenValidator.validateByToken(token);
            return new BaseResponse<>("Companies in High LPA Order", HttpStatus.OK.value(), true,"",companyService.fetchByHighLpa());
        }
        catch (Exception exception)
        {
            BaseResponse<List<CompanyDetails>> baseResponse = new BaseResponse<>(exception.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, exception.getMessage(), null);
            if (baseResponse.getError().contains("401")) {
                baseResponse.setCode(401);
            }
            return baseResponse;
        }
    }


}
