package company.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Integer id;

    @Valid
    @NotEmpty(groups = { CompanyDto.class })
    private CompanyDto company;

    @NotEmpty(message = "Invalid first name")
    private String firstName;

    @NotEmpty(message = "Invalid last name")
    private String lastName;

    @NotEmpty(message = "Invalid age")
    private String age;

    @NotEmpty(message = "Email address is required")
    @Email(message = "Invalid email address format")
    private String email;

    @NotEmpty(message = "Invalid address")
    private String address;

    @NotEmpty(message = "Invalid contact number")
    private String contactNumber;

    @NotEmpty(message = "Invalid job title")
    private String jobTitle;

    @JsonProperty(required = true)
    @NotEmpty(message = "Invalid salary")
    private String salary;

    private Date createdAt;
    private Date updatedAt;
}
