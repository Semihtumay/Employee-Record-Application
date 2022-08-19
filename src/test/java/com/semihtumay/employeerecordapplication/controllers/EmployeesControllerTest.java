package com.semihtumay.employeerecordapplication.controllers;

import com.semihtumay.employeerecordapplication.entities.Employees;
import com.semihtumay.employeerecordapplication.services.EmployeesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeesControllerTest {

    @InjectMocks
    private EmployeesController employeesController;

    @Mock
    private EmployeesService employeesService;

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
        ResponseEntity responseEntity = employeesService.register(employees);
        when(employeesService.register(employees)).thenReturn(responseEntity);
        employeesController.register(employees);
    }

    @Test
    void delete() {
        ResponseEntity responseEntity = employeesService.delete(1L);
        when(employeesService.delete(1L)).thenReturn(responseEntity);
         employeesController.delete(1L);
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
        employees.setSalary(BigDecimal.valueOf(15000));
        employees.setStartDate(new Date());
        ResponseEntity responseEntity = employeesService.update(employees);
        when(employeesService.update(employees)).thenReturn(responseEntity);
        employeesController.update(employees);
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
        ResponseEntity responseEntity = employeesService.list();
        when(employeesService.list()).thenReturn(responseEntity);
        employeesController.list();
    }

    @Test
    void search() throws ParseException {
        List<Employees> employeesList = new ArrayList<>();
        Date startDate = new SimpleDateFormat("YYYY-MM-DD").parse("2022-08-20");
        ResponseEntity responseEntity = employeesService.getEmployeesByStartDateAndSalary(startDate,new BigDecimal(15000));
        when(employeesService.getEmployeesByStartDateAndSalary( startDate,new BigDecimal(15000))).thenReturn(responseEntity);
        employeesController.search(startDate,new BigDecimal(15000));
    }
}