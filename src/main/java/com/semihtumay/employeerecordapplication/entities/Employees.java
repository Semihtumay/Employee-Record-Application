package com.semihtumay.employeerecordapplication.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank(message = "First name can not be blank")
    @Length(message = "First name must contain min 2 max 50 character.", min = 2, max = 50)
    private String firstName;
    @NotBlank(message = "Lastname can not be blank")
    @Length(message = "Lastname must contain min 2 max 50 character.", min = 2, max = 50)
    private String lastName;
    @Column(unique = true)
    @NotBlank(message = "IDNumber can not be blank")
    @Length(message = "IDNumber must contain 11 character.",min = 11, max = 11)
    @Digits(message = "ID number must consist of numbers.",integer = 11, fraction = 0)
    private String idNumber;
    @NotBlank(message = "Phone number can not be blank")
    @Length(message = "Phone number must contain min 2 max 50 character.", min = 2, max = 50)
    private String phone;
    @NotBlank(message = "Address can not be blank")
    @Length(message = "Address must contain min 2 max 50 character.", min = 2, max = 100)
    private String address;
    @NotBlank(message = "Department can not be blank")
    @Length(message = "Department must contain min 2 max 50 character.", min = 2, max = 100)
    private String department;
    @Positive(message = "The value you entered is not acceptable")
    @NotNull(message = "Salary can not be blank")
    private BigDecimal salary;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

}
