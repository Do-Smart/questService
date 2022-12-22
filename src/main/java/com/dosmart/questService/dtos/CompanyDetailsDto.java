package com.dosmart.questService.dtos;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CompanyDetailsDto {
    private String companyName;
    private String CTC;
    private List<String> location;
    private HrDetail hrDetail;
    private HrDetail alternativeHrDetail;
    private double stipend;
    private String yearOfVisit;
    private String monthOfVisit;
    private Map<Integer,Integer> studentHiredYearWise;
}
