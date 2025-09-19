package edu.matrix.co.entity.security;

import enums.CityEnum;
import enums.ProvinceEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "StudentFamily")
public class StudentFamilyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FamilyId")
    private Integer familyId;

    @Column(name = "Name", length = 100, nullable = false)
    private String name;

    @Column(name = "CNIC", length = 20)
    private String cnic;

    @Column(name = "Occupation", length = 100)
    private String occupation;

    @Column(name = "MobileForSMS", length = 20)
    private String mobileForSms;

    @Column(name = "PhoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "Nationality", length = 50)
    private String nationality;

    @Column(name = "RelationToStudent", length = 50)
    private String relationToStudent;

    @Column(name = "Address1", length = 200)
    private String address1;

    @Column(name = "Address2", length = 200)
    private String address2;

    @Enumerated(EnumType.STRING)
    @Column(name = "City", length = 100)
    private CityEnum cityEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "Province", length = 100)
    private ProvinceEnum provinceEnum;

    @Column(name = "EmergencyContact", length = 20)
    private String emergencyContact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentId", nullable = false)
    private StudentEntity student;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "MaritalStatus", length = 50)
    private String maritalStatus;
}
