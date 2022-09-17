package company.services;

import company.converters.EmployeeConverter;
import company.dtos.CompanyDto;
import company.dtos.EmployeeDto;
import company.entities.Company;
import company.entities.Employee;
import company.exception.ResourceNotFoundException;
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

    public EmployeeDto getCompanyById(Integer id) throws ResourceNotFoundException {
        Employee record =
                employeeRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Record not found with ID: " + id));

        return employeeConverter.convertEntityToDto(record);
    }

    public EmployeeDto createCompany(EmployeeDto employeeDto) {
        return employeeConverter.convertEntityToDto(employeeRepository.save(employeeConverter.convertDtoToEntity(employeeDto)));
    }

    public EmployeeDto updateCompany(EmployeeDto employeeDto) throws ResourceNotFoundException {
        if (employeeDto.getId() == null) throw new ResourceNotFoundException("The given ID must not be null!");

        Employee existingRecord = employeeRepository.findById(employeeDto.getId()).orElse(null);

        if (existingRecord == null) return null;

        existingRecord.setFirstName(employeeDto.getFirstName());
        existingRecord.setLastName(employeeDto.getLastName());
        existingRecord.setAddress(employeeDto.getAddress());
        existingRecord.setAge(employeeDto.getAge());
        existingRecord.setContactNumber(employeeDto.getContactNumber());
        existingRecord.setJobTitle(employeeDto.getJobTitle());
        existingRecord.setSalary(employeeDto.getSalary());

        if (employeeDto.getCompany() != null) {
            Integer companyId = employeeDto.getCompany().getId();
            CompanyDto existingCompany;

            try {
                // check if the new company supplied exists
                existingCompany = companyService.getCompanyById(companyId);
            } catch (ResourceNotFoundException ex) {
                throw new ResourceNotFoundException("Company record not found with ID: " + companyId);
            }

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

    public void deleteCompany(Integer id) throws ResourceNotFoundException {
        Employee record =
                employeeRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Record not found with ID: " + id));

        employeeRepository.deleteById(record.getId());
    }
}
