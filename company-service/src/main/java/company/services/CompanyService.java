package company.services;

import company.converters.EmployeeConverter;
import company.dtos.CompanyDto;
import company.dtos.CompanyEmployeesDto;
import company.entities.Company;
import company.entities.Employee;
import company.exception.ResourceNotFoundExceptionHandler;
import company.repository.CompanyRepository;
import company.converters.CompanyConverter;
import company.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final CompanyConverter companyConverter;
    private final EmployeeConverter employeeConverter;


    public List<CompanyDto> getAllCompanies() {
        return companyConverter.convertEntityListToDto(companyRepository.findAll());
    }

    public CompanyDto getCompanyById(Integer id) throws ResourceNotFoundExceptionHandler {
        Company record =
                companyRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundExceptionHandler("Company record not found with ID: " + id));

        return companyConverter.convertEntityToDto(record);
    }

    public CompanyDto getCompanyByName(String name) throws ResourceNotFoundExceptionHandler {
        Company record = companyRepository.findByName(name);

        if (record == null) throw new ResourceNotFoundExceptionHandler("Company record not found with name: " + name);

        return companyConverter.convertEntityToDto(record);
    }

    public CompanyDto createCompany(CompanyDto companyDto) {
        return companyConverter.convertEntityToDto(companyRepository.save(companyConverter.convertDtoToEntity(companyDto)));
    }

    public CompanyDto updateCompany(CompanyDto companyDto) throws ResourceNotFoundExceptionHandler {
        if (companyDto.getId() == null) throw new ResourceNotFoundExceptionHandler("The given ID must not be null!");

        Company existingRecord = companyRepository.findById(companyDto.getId()).orElse(null);

        if (existingRecord == null) throw new ResourceNotFoundExceptionHandler("Company record not found with ID: " + companyDto.getId());

        existingRecord.setName(companyDto.getName());
        existingRecord.setAddress(companyDto.getAddress());

        return companyConverter.convertEntityToDto(companyRepository.save(existingRecord));
    }

    public void deleteCompany(Integer id) throws ResourceNotFoundExceptionHandler {
        Company record =
                companyRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundExceptionHandler("Company record not found with ID: " + id));

        companyRepository.deleteById(record.getId());
    }

    public CompanyEmployeesDto getEmployeesByCompany(Integer id) throws ResourceNotFoundExceptionHandler {
        List<Employee> employees = employeeRepository.findByCompanyId(id);
        CompanyDto companyDto = getCompanyById(id);

        CompanyEmployeesDto companyEmployees = CompanyEmployeesDto.builder()
                .company(companyDto)
                .employees(new ArrayList<>())
                .build();

        if (employees != null) {
            employees.forEach(employee -> {
                companyEmployees.getEmployees().add(employeeConverter.convertEntityToDtoNoCompany(employee));
            });
        }

        return companyEmployees;
    }

}
