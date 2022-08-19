package com.semihtumay.employeerecordapplication.services;

import com.semihtumay.employeerecordapplication.entities.Employees;
import com.semihtumay.employeerecordapplication.entities.LuckyEmployee;
import com.semihtumay.employeerecordapplication.repositories.EmployeesRepository;
import com.semihtumay.employeerecordapplication.repositories.LuckyEmployeeRepository;
import com.semihtumay.employeerecordapplication.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LuckyEmployeeService {

    final LuckyEmployeeRepository luckyEmployeeRepository;
    final EmployeesRepository employeesRepository;

    public LuckyEmployeeService(LuckyEmployeeRepository luckyEmployeeRepository, EmployeesRepository employeesRepository) {
        this.luckyEmployeeRepository = luckyEmployeeRepository;
        this.employeesRepository = employeesRepository;
    }


    @Scheduled(cron = "0 0 11 15 * *")
    public ResponseEntity getWinner() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<Employees> employeeList = employeesRepository.findAll();
        Random random = new Random();
        Employees employees = employeeList.get(random.nextInt(employeeList.size()));
        LuckyEmployee luckyEmployee = new LuckyEmployee();
        Date date = new Date(System.currentTimeMillis());
        luckyEmployee.setAwardDate(date);
        luckyEmployee.setId(employees.getId());
        luckyEmployee.setEmployees(employees);
        luckyEmployeeRepository.save(luckyEmployee);
        hm.put(REnum.status, true);
        hm.put(REnum.result, luckyEmployee);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity list() {
        Map<REnum, Object> hm = new HashMap<>();
        List<LuckyEmployee> employeesList = luckyEmployeeRepository.findAll();
        hm.put(REnum.status, true);
        hm.put(REnum.result, employeesList);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }
}
