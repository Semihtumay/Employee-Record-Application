package com.semihtumay.employeerecordapplication.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class LuckyEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date awardDate;
    @ManyToOne
    @JoinColumn(name = "employees_ıd")
    private Employees employees;
}
