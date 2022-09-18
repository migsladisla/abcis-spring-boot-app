package company.controllers;

import company.dtos.EmployeeDto;
import company.exception.ResourceNotFoundExceptionHandler;
import company.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto companyDto) throws ResourceNotFoundExceptionHandler {
        return employeeService.createEmployee(companyDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getEmployeeById(@PathVariable Integer id) throws ResourceNotFoundExceptionHandler {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateEmployee(@RequestBody @Valid EmployeeDto employeeDto) throws ResourceNotFoundExceptionHandler {
        return employeeService.updateEmployee(employeeDto);
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Integer id) throws ResourceNotFoundExceptionHandler {
        employeeService.deleteEmployee(id);
    }

}
