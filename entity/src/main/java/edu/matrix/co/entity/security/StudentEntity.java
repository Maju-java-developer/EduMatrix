package edu.matrix.co.entity.security;

import enums.GenderEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentId")
    private Integer studentId;

    @Column(name = "AdmissionNo", length = 100, nullable = false, unique = true)
    private String admissionNo;

    @Column(name = "StudentName", length = 100, nullable = false)
    private String studentName;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", length = 50)
    private GenderEnum gender;

    @Column(name = "DOB")
    private LocalDate dob;

    @Column(name = "CNIC", length = 20)
    private String cnic;

    @Column(name = "BloodGroup", length = 20)
    private String bloodGroup;

    @Column(name = "Religion", length = 50)
    private String religion;

    @Column(name = "Nationality", length = 50)
    private String nationality;

    @Column(name = "BirthPlace", length = 100)
    private String birthPlace;

    @Column(name = "MotherTongue", length = 50)
    private String motherTongue;

    @Column(name = "MobileForSMS", length = 20)
    private String mobileForSMS;

    @Column(name = "PhoneNumber", length = 20)
    private String phoneNumber;

    @Lob
    @Column(name = "StudentPicture")
    private String studentPicture; // If storing Base64 or URL

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AcademicYearId", nullable = false)
    private AcademicYearEntity academicYear;

    @Column(name = "AdmissionDate")
    private LocalDate admissionDate;

    @Column(name = "AdmittedInCourse", length = 100)
    private String admittedInCourse;

    @Column(name = "CurrentCourse", length = 100)
    private String currentCourse;

    @Column(name = "ReferredBy", length = 100)
    private String referredBy;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    // Relation with family
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentFamilyEntity> families;
}
