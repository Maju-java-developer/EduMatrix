package edu.matrix.co.cores.security.dtos;

import enums.GenderEnum;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentDto {
    private Integer studentId;

    @NotBlank(message = "Admission number is required")
    private String admissionNo;

    @NotBlank(message = "Student name is required")
    private String studentName;

    @NotNull(message = "Gender is required")
    private GenderEnum gender;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotBlank(message = "CNIC is required")
    @Pattern(regexp = "^[0-9]{13}$", message = "CNIC must be 13 digits")
    private String cnic;

    @NotNull(message = "bloodGroup is required!")
    private String bloodGroup;

    private String religion;

    private String nationality;

    private String birthPlace;

    private String motherTongue;

    @NotBlank(message = "Mobile number for SMS is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid mobile number")
    private String mobileForSMS;

    private String phoneNumber;

    private String studentPicture;

    @NotNull(message = "Admission date is required")
    private LocalDate admissionDate;

    @NotBlank(message = "Admitted course is required")
    private String admittedInCourse;

    @NotBlank(message = "Current course is required")
    private String currentCourse;

    private String referredBy;

    private String description;

    @NotNull(message = "Academic year is required")
    private AcademicYearDto academicYear;

    @NotEmpty(message = "At least one family record is required")
    private List<StudentFamilyDto> families;
}
