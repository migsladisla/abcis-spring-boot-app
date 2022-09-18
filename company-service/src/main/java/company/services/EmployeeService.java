package company.services;

import company.converters.EmployeeConverter;
import company.dtos.CompanyDto;
import company.dtos.EmployeeDto;
import company.entities.Company;
import company.entities.Employee;
import company.exception.ResourceNotFoundExceptionHandler;
import company.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final CompanyService companyService;
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;

    public List<EmployeeDto> getAllEmployees() {
        return employeeConverter.convertEntityListToDto(employeeRepository.findAll());
    }

    public EmployeeDto getEmployeeById(Integer id) throws ResourceNotFoundExceptionHandler {
        Employee record =
                employeeRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundExceptionHandler("Employee record not found with ID: " + id));

        return employeeConverter.convertEntityToDto(record);
    }

    public List<EmployeeDto> getEmployeesByCompanyId(Integer id) {
        List<Employee> employees = employeeRepository.findByCompanyId(id);

        return employeeConverter.convertEntityListToDto(employees);
    }

    public EmployeeDto createEmployee(EmployeeDto employeeDto) throws ResourceNotFoundExceptionHandler {
        CompanyDto companyDto = companyService.getCompanyById(employeeDto.getCompany().getId());

        if (companyDto == null) throw new ResourceNotFoundExceptionHandler("Employee record not found with ID: " + employeeDto.getCompany().getId());

        return employeeConverter.convertEntityToDto(employeeRepository.save(employeeConverter.convertDtoToEntity(employeeDto)));
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) throws ResourceNotFoundExceptionHandler {
        if (employeeDto.getId() == null) throw new ResourceNotFoundExceptionHandler("The given ID must not be null!");

        Employee existingRecord = employeeRepository.findById(employeeDto.getId()).orElse(null);

        if (existingRecord == null) throw new ResourceNotFoundExceptionHandler("Employee record not found with ID: " + employeeDto.getCompany().getId());

        existingRecord.setFirstName(employeeDto.getFirstName());
        existingRecord.setLastName(employeeDto.getLastName());
        existingRecord.setAddress(employeeDto.getAddress());
        existingRecord.setAge(employeeDto.getAge());
        existingRecord.setContactNumber(employeeDto.getContactNumber());
        existingRecord.setJobTitle(employeeDto.getJobTitle());
        existingRecord.setSalary(employeeDto.getSalary());

        if (employeeDto.getCompany() != null) {
            Integer companyId = employeeDto.getCompany().getId();

            // check if the new company supplied exists
            CompanyDto existingCompany = companyService.getCompanyById(companyId);
            if (existingCompany == null) throw new ResourceNotFoundExceptionHandler("Employee record not found with ID: " + companyId);

            if (existingCompany.getId() != null) {
                Company company = Company.builder()
                        .id(companyId)
                        .name(employeeDto.getCompany().getName())
                        .address(employeeDto.getCompany().getAddress())
                        .build();

                existingRecord.setCompany(company);
            }
        }

        return employeeConverter.convertEntityToDto(employeeRepository.save(existingRecord));
    }

    public void deleteEmployee(Integer id) throws ResourceNotFoundExceptionHandler {
        Employee record =
                employeeRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundExceptionHandler("Employee record not found with ID: " + id));

        employeeRepository.deleteById(record.getId());
    }
}
