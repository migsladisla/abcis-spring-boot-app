package company.controllers;

import company.dtos.EmployeeDto;
import company.exception.ResourceNotFoundException;
import company.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody EmployeeDto companyDto) {
        return employeeService.createCompany(companyDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getEmployeeById(@PathVariable Integer id) throws ResourceNotFoundException {
        try {
            return employeeService.getCompanyById(id);
        } catch (ResourceNotFoundException ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) throws ResourceNotFoundException {
        try {
            return employeeService.updateCompany(employeeDto);
        } catch (ResourceNotFoundException ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Integer id) throws ResourceNotFoundException {
        try {
            employeeService.deleteCompany(id);
        } catch (ResourceNotFoundException ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

}
