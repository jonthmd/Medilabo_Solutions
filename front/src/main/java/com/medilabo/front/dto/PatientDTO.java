package com.medilabo.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private Long id;

//    @NotBlank(message = "First name is mandatory.")
    private String firstName;

//    @NotBlank(message = "Last name is mandatory.")
    private String lastName;

//    @NotBlank(message = "Birthdate is mandatory.")
    private LocalDate birthDate;

//    @NotBlank(message = "Gender is mandatory.")
    private String gender;

    private String address;
    private String phone;
}
