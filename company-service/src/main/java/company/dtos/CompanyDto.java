package company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private Integer id;

    @NotEmpty(message = "Invalid company name")
    private String name;

    @NotEmpty(message = "Invalid company address")
    private String address;

}
