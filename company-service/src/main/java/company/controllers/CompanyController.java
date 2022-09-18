package company.controllers;

import company.dtos.CompanyDto;
import company.dtos.CompanyEmployeesDto;
import company.exception.ResourceNotFoundExceptionHandler;
import company.services.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

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

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto getCompanyById(@PathVariable Integer id) throws ResourceNotFoundExceptionHandler {
        try {
            return companyService.getCompanyById(id);
        } catch (ResourceNotFoundExceptionHandler ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @GetMapping("/company/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto getCompanyByName(@PathVariable String name) throws ResourceNotFoundExceptionHandler {
        try {
            return companyService.getCompanyByName(name);
        } catch (ResourceNotFoundExceptionHandler ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto updateCompany(@RequestBody @Valid CompanyDto companyDto) throws ResourceNotFoundExceptionHandler {
        try {
            return companyService.updateCompany(companyDto);
        } catch (ResourceNotFoundExceptionHandler ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @DeleteMapping("/company/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Integer id) throws ResourceNotFoundExceptionHandler {
        try {
            companyService.deleteCompany(id);
        } catch (ResourceNotFoundExceptionHandler ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/company/{id}/employees")
    @ResponseStatus(HttpStatus.OK)
    public CompanyEmployeesDto getEmployeesByCompanyId(@PathVariable Integer id) throws ResourceNotFoundExceptionHandler {
        try {
            return companyService.getEmployeesByCompany(id);
        } catch (ResourceNotFoundExceptionHandler ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

}
