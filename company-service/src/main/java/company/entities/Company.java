package company.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Company {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;
}
