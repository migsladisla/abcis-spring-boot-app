package company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Integer id;
    private CompanyDto company;
    private String firstName;
    private String lastName;
    private String age;
    private String address;
    private String contactNumber;
    private String jobTitle;
    private String salary;
    private Date createdAt;
    private Date updatedAt;
}
