package com.semihtumay.employeerecordapplication.services;

import com.semihtumay.employeerecordapplication.controllers.EmployeesController;
import com.semihtumay.employeerecordapplication.entities.Employees;
import com.semihtumay.employeerecordapplication.repositories.EmployeesRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeesServiceTest {

    @InjectMocks
    private EmployeesService employeesService;

    @Mock
    private EmployeesRepository employeesRepository;

    @Test
    void register() {
        Employees employees = new Employees();
        employees.setId(1L);
        employees.setFirstName("Test-Name");
        employees.setLastName("Test-Lastname");
        employees.setIdNumber("14822356905");
        employees.setPhone("+90 555 55 55");
        employees.setAddress("Sisli/Istanbul");
        employees.setDepartment("IT");
        employees.setSalary(BigDecimal.valueOf(15000));
        employees.setStartDate(new Date());

        when(employeesRepository.save(employees)).thenReturn(employees);
        employeesService.register(employees);
        System.out.println(employeesRepository.findAll().size());
        assertNotNull(employeesRepository.findById(1L));
    }

    @Test
    void update() {
        Employees employees = new Employees();
        employees.setId(1L);
        employees.setFirstName("Test-Name");
        employees.setLastName("Test-Lastname");
        employees.setIdNumber("14822356905");
        employees.setPhone("+90 555 55 55");
        employees.setAddress("Sisli/Istanbul");
        employees.setDepartment("IT");
        employees.setSalary(BigDecimal.valueOf(17000));
        employees.setStartDate(employees.getStartDate());

        lenient().when(employeesRepository.saveAndFlush(employees)).thenReturn(employees);
        employeesService.update(employees);
        assertNotNull(employees);
    }

    @Test
    void delete() {
        employeesRepository.deleteById(1L);
        employeesService.delete(1L);
        Optional<Employees> optionalEmployees =employeesRepository.findById(1L);
        assertFalse(optionalEmployees.isPresent());
    }


    @Test
    void list() {
        List<Employees> employeesList = new ArrayList<>();
        Employees employees = new Employees();
        employees.setId(1L);
        employees.setFirstName("Test-Name");
        employees.setLastName("Test-Lastname");
        employees.setIdNumber("14822356905");
        employees.setPhone("+90 555 55 55");
        employees.setAddress("Sisli/Istanbul");
        employees.setDepartment("IT");
        employees.setSalary(BigDecimal.valueOf(17000));
        employees.setStartDate(new Date());
        Employees employees1 = new Employees();
        employees1.setId(2L);
        employees1.setFirstName("Test-Name");
        employees1.setLastName("Test-Lastname");
        employees1.setIdNumber("14822356906");
        employees1.setPhone("+90 555 55 55");
        employees1.setAddress("Sisli/Istanbul");
        employees1.setDepartment("IT");
        employees1.setSalary(BigDecimal.valueOf(15000));
        employees1.setStartDate(new Date());
        employeesList.add(employees);
        employeesList.add(employees1);
        when(employeesRepository.findAll()).thenReturn(employeesList);
        employeesService.list();
    }

    @Test
    void getEmployeesByStartDateAndSalary() throws ParseException {
        List<Employees> employeesList = new ArrayList<>();
        Date startDate = new SimpleDateFormat("YYYY-MM-DD").parse("2022-08-20");
        when(employeesRepository.findByStartDateAfterAndSalaryGreaterThan(startDate,new BigDecimal(15000))).thenReturn(employeesList);
        employeesService.getEmployeesByStartDateAndSalary(startDate,new BigDecimal(15000));
    }
}