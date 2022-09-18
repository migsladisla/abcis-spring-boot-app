package company.controllers;

import company.dtos.CompanyDto;
import company.dtos.CompanyEmployeesDto;
import company.exception.ResourceNotFoundExceptionHandler;
import company.services.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDto createCompany(@RequestBody @Valid CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDto> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto getCompanyById(@PathVariable Integer id) throws ResourceNotFoundExceptionHandler {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/company/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto getCompanyByName(@PathVariable String name) throws ResourceNotFoundExceptionHandler {
        return companyService.getCompanyByName(name);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto updateCompany(@RequestBody @Valid CompanyDto companyDto) throws ResourceNotFoundExceptionHandler {
        return companyService.updateCompany(companyDto);
    }

    @DeleteMapping("/company/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Integer id) throws ResourceNotFoundExceptionHandler {
        companyService.deleteCompany(id);
    }

    @GetMapping("/company/{id}/employees")
    @ResponseStatus(HttpStatus.OK)
    public CompanyEmployeesDto getEmployeesByCompanyId(@PathVariable Integer id) throws ResourceNotFoundExceptionHandler {
        return companyService.getEmployeesByCompany(id);
    }

}
