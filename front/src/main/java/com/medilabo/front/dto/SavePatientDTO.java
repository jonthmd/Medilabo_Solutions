package com.medilabo.front.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavePatientDTO {

    @NotBlank(message = "First name is mandatory.")
    private String firstName;

    @NotBlank(message = "Last name is mandatory.")
    private String lastName;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Birth date is mandatory.")
    private String birthDate;

    @NotBlank(message = "Gender is mandatory.")
    private String gender;

    private String address;
    private String phone;
}
