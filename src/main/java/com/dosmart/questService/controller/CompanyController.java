package com.dosmart.questService.controller;

import com.dosmart.questService.dtos.BaseResponse;
import com.dosmart.questService.model.CompanyDetails;
import com.dosmart.questService.services.BaseService;
import com.dosmart.questService.utils.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.dosmart.questService.utils.Constants.AUTHORIZATION;
import static com.dosmart.questService.utils.Urls.*;

@RestController
@RequestMapping(COMPANY_URL)
public class CompanyController {

    @Autowired
    BaseService<CompanyDetails> baseService;

    @Autowired
    TokenValidator tokenValidator;

    @PostMapping(SAVE_COMPANY)
    public BaseResponse<CompanyDetails> saveNewCompany(@RequestBody CompanyDetails companyDetails, @RequestHeader("Authorization") String token)
    {
        try {
            tokenValidator.validateByToken(token);
            CompanyDetails details = baseService.save(companyDetails);
            if (Objects.nonNull(details)) {
                return new BaseResponse<>("Added Successfully", HttpStatus.OK.value(), true, "", details);
            } else {
                return new BaseResponse<>("Already Exists", HttpStatus.ALREADY_REPORTED.value(), false, "", null);
            }
        }
        catch (Exception exception)
        {
            return new BaseResponse<>(exception.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(), false, exception.getMessage(),null);
        }
    }

    @DeleteMapping(DELETE_COMPANY + "{companyName}/{location}")
    public BaseResponse<CompanyDetails> deleteCompany(@PathVariable("companyName") String companyName, @PathVariable("location") String location, @RequestHeader(AUTHORIZATION) String token)
    {
        try{
            tokenValidator.validateByToken(token);
            CompanyDetails details = baseService.delete(companyName,location);
            if(Objects.nonNull(details))
            {
                return new BaseResponse<>("Deleted Successfully",HttpStatus.OK.value(), true,"",details);
            }
            return new BaseResponse<>("Delete unSuccessful",HttpStatus.NO_CONTENT.value(), false,"Company not found",null);

        }
        catch (Exception exception)
        {
            return new BaseResponse<>("Error occurred",HttpStatus.INTERNAL_SERVER_ERROR.value(), false,exception.getMessage(),null);
        }
    }
    @PutMapping(MODIFY + "{companyName}/{location}")
    public BaseResponse<CompanyDetails> modifyCompany(@PathVariable("companyName") String companyName, @PathVariable("location") String location, @RequestHeader(AUTHORIZATION) String token, @RequestBody CompanyDetails companyDetails)
    {
        try {
            tokenValidator.validateByToken(token);
            CompanyDetails details = baseService.modify(companyName,location,companyDetails);
            if(Objects.nonNull(details))
            {
                return new BaseResponse<>("Modified",HttpStatus.OK.value(), true,"",details);
            }
            return new BaseResponse<>("Modification unSuccessful", HttpStatus.NO_CONTENT.value(), false, "Company Not found", null);
        }
        catch (Exception exception)
        {
            return new BaseResponse<>("Error occurred",HttpStatus.INTERNAL_SERVER_ERROR.value(), false,exception.getMessage(),null);
        }
    }



}
