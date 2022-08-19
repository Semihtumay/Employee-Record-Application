package com.semihtumay.employeerecordapplication.repositories;

import com.semihtumay.employeerecordapplication.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    Optional<Employees> findByIdNumber(String idNumber);

    List<Employees> findByStartDateAfterAndSalaryGreaterThan(Date startDate, BigDecimal salary);



}