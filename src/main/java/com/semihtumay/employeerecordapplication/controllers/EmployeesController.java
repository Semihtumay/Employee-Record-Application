package com.semihtumay.employeerecordapplication.controllers;

import com.semihtumay.employeerecordapplication.entities.Employees;
import com.semihtumay.employeerecordapplication.services.EmployeesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("employees")
@Validated
public class EmployeesController {
    final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody Employees employees){
        return employeesService.register(employees);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return employeesService.delete(id);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Employees employees){
        return employeesService.update(employees);
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        return employeesService.list();
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam BigDecimal salary){
        return employeesService.getEmployeesByStartDateAndSalary(startDate,salary);
    }
}
