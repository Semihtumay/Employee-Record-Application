package com.semihtumay.employeerecordapplication.services;

import com.semihtumay.employeerecordapplication.entities.Employees;
import com.semihtumay.employeerecordapplication.repositories.EmployeesRepository;
import com.semihtumay.employeerecordapplication.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.*;

@Service
public class EmployeesService {
    final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public ResponseEntity register(Employees employees) {
        Optional<Employees> optionalEmployees = employeesRepository.findByIdNumber(employees.getIdNumber());
        Map<REnum, Object> hm = new LinkedHashMap();
        if (!optionalEmployees.isPresent()){
            employees.setStartDate(new Date());
            employeesRepository.save(employees);
            hm.put(REnum.status, true);
            hm.put(REnum.result, employees);
            return new ResponseEntity(hm, HttpStatus.OK);
        }else {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "This employee is already registered");
            return new ResponseEntity(hm, HttpStatus.NOT_ACCEPTABLE);
        }
    }
    public ResponseEntity delete(Long id) {
        Map<REnum, Object> hm = new LinkedHashMap();
        try {
            employeesRepository.deleteById(id);
            hm.put(REnum.status, true);
            return new ResponseEntity<>(hm, HttpStatus.OK);
        } catch (Exception exception) {
            hm.put(REnum.status, false);
            return new ResponseEntity<>(hm, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity update(Employees employees) {
        Map<REnum, Object> hm = new LinkedHashMap();
        try {
            Optional<Employees> optionalEmployees = employeesRepository.findById(employees.getId());
            if (optionalEmployees.isPresent()) {
                employees.setStartDate(optionalEmployees.get().getStartDate());
                employeesRepository.saveAndFlush(employees);
                hm.put(REnum.status, true);
                hm.put(REnum.result, employees);
                return new ResponseEntity<>(hm, HttpStatus.OK);
            } else {
                hm.put(REnum.status, false);
                hm.put(REnum.message, "No one found for this ID number");
                return new ResponseEntity<>(hm, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            hm.put(REnum.status, false);
            hm.put(REnum.message,exception);
            return new ResponseEntity<>(hm, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity list() {
        Map<REnum, Object> hm = new HashMap<>();
        List<Employees> employeesList = employeesRepository.findAll();
        hm.put(REnum.status, true);
        hm.put(REnum.result, employeesList);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }


    public ResponseEntity  getEmployeesByStartDateAndSalary(Date startDate, BigDecimal salary) {
        Map<REnum, Object> hm = new HashMap<>();
        List<Employees> employeesList = employeesRepository.findByStartDateAfterAndSalaryGreaterThan(startDate,salary);
        hm.put(REnum.status, true);
        hm.put(REnum.result, employeesList);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }
}
