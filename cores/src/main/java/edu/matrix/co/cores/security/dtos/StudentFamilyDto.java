package edu.matrix.co.cores.security.dtos;

import enums.CityEnum;
import enums.ProvinceEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentFamilyDto {
    private Integer familyId;
    private String name;
    private String cnic;
    private String occupation;
    private String mobileForSms;
    private String phoneNumber;
    private String nationality;
    private String relationToStudent;
    private String address1;
    private String address2;
    private CityEnum city;
    private ProvinceEnum province;
    private String emergencyContact;
    private String email;
    private String maritalStatus;
    private StudentDto student;
    // We normally don’t expose StudentId directly here
    // Because this will be nested under StudentDto
}

