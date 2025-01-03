package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDealerRequest {
    @NotBlank(message = "Field name cannot be null or blank")
    private String name;
    @NotBlank(message = "Field lastName cannot be null or blank")
    private String lastName;
    @NotBlank(message = "Field email cannot be null or blank")
    @Email(message = "Field email must be a valid email")
    private String email;
    @NotBlank(message = "Field phone cannot be null or blank")
    private String phone;
    @NotBlank(message = "Field numberLicense cannot be null or blank")
    private String numberLicense;
    private String numberDocument;
    @NotBlank(message = "Field expirationDateLicense cannot be null or blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Field expirationDateLicense must be in the format yyyy-MM-dd")
    private String expirationDateLicense;
}
