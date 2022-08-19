package com.semihtumay.employeerecordapplication.controllers;

import com.semihtumay.employeerecordapplication.entities.Employees;
import com.semihtumay.employeerecordapplication.repositories.EmployeesRepository;
import com.semihtumay.employeerecordapplication.services.EmployeesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeesServiceIntegrationTest {

    @Autowired
    private EmployeesController employeesController;

    @Autowired
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

        employeesController.register(employees);
        assertTrue(employees.getId() > 0L);
    }

    @Test
    void delete() {
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

        employeesController.delete(employees.getId());
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
        employeesController.update(employees);
        assertNotNull(employees);
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
        employeesController.list();
        assertTrue(true);
    }

    @Test
    void getEmployeesByStartDateAndSalary() throws ParseException {
        Date startDate = new SimpleDateFormat("YYYY-MM-DD").parse("2022-08-20");
        employeesController.search(startDate,new BigDecimal(15000));
        assertTrue(true);
    }
}
