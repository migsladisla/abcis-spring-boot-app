package company.converters;

import company.dtos.CompanyDto;
import company.dtos.EmployeeDto;
import company.entities.Company;
import company.entities.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeConverter {
    public EmployeeDto convertEntityToDto(Employee entity) {
        if (entity == null) {
            return null;
        }

        var dto = EmployeeDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .age(entity.getAge())
                .address(entity.getAddress())
                .contactNumber(entity.getContactNumber())
                .jobTitle(entity.getJobTitle())
                .salary(entity.getSalary())
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();

        if (entity.getCompany() != null) {
            CompanyDto companyDto = CompanyDto.builder()
                    .id(entity.getCompany().getId())
                    .name(entity.getCompany().getName())
                    .address(entity.getCompany().getAddress())
                    .build();
            dto.setCompany(companyDto);
        }

        return dto;
    }

    public EmployeeDto convertEntityToDtoNoCompany(Employee entity) {
        if (entity == null) {
            return null;
        }

        return EmployeeDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .age(entity.getAge())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .contactNumber(entity.getContactNumber())
                .jobTitle(entity.getJobTitle())
                .salary(entity.getSalary())
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public Employee convertDtoToEntity(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }

        var entity = Employee.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .contactNumber(dto.getContactNumber())
                .jobTitle(dto.getJobTitle())
                .salary(dto.getSalary())
                .build();

        if (dto.getCompany() != null) {
            Company company = Company.builder()
                    .id(dto.getCompany().getId())
                    .name(dto.getCompany().getName())
                    .address(dto.getCompany().getAddress())
                    .build();
            entity.setCompany(company);
        }

        return entity;
    }

    public List<EmployeeDto> convertEntityListToDto(List<Employee> entities) {
        return entities.stream()
                        .map(this::convertEntityToDto)
                        .collect(Collectors.toList());
    }

    public List<Employee> convertDtoListToEntity(List<EmployeeDto> dtos) {
        return dtos.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }
}
