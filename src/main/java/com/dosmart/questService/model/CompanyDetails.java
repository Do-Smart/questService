package com.dosmart.questService.model;

import com.dosmart.questService.dtos.AdminDetails;
import com.dosmart.questService.dtos.HrDetail;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class CompanyDetails {
    @Id
    private String id;
    private String companyName;
    private long CTC;
    private String location;
    private HrDetail hrDetail;
    private HrDetail alternativeHrDetail;
    private double stipend;
    private String yearOfVisit;
    private String monthOfVisit;
    private Map<Integer,Integer> studentHiredYearWise;
    private AdminDetails entryAddedBy;
    private String entryModifiedDate;
}
