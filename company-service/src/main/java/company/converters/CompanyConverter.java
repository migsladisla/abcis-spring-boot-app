package company.converters;

import company.dtos.CompanyDto;
import company.entities.Company;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyConverter {
    public CompanyDto convertEntityToDto(Company entity) {
        if (entity == null) {
            return null;
        }

        return CompanyDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .build();
    }

    public Company convertDtoToEntity(CompanyDto dto) {
        if (dto == null) {
            return null;
        }

        return Company.builder()
                .id(dto.getId())
                .name(dto.getName())
                .address(dto.getAddress())
                .build();
    }

    public List<CompanyDto> convertEntityListToDto(List<Company> entities) {
        return entities.stream()
                        .map(this::convertEntityToDto)
                        .collect(Collectors.toList());
    }

    public List<Company> convertDtoListToEntity(List<CompanyDto> dtos) {
        return dtos.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }
}
