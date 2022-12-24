package com.dosmart.questService.model;

import com.dosmart.questService.dtos.HrDetail;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document
public class CompanyDetails {
    @Id
    private String id;
    private String companyName;
    private String CTC;
    private String location;
    private HrDetail hrDetail;
    private HrDetail alternativeHrDetail;
    private double stipend;
    private String yearOfVisit;
    private String monthOfVisit;
    private Map<Integer,Integer> studentHiredYearWise;
}
