package com.dosmart.questService.controller;

import com.dosmart.questService.dtos.BaseResponse;
import com.dosmart.questService.model.CompanyDetails;
import com.dosmart.questService.services.BaseService;
import com.dosmart.questService.utils.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    BaseService<CompanyDetails> baseService;

    @Autowired
    TokenValidator tokenValidator;

    @PostMapping("/new/save")
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

}
