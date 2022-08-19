package com.semihtumay.employeerecordapplication.repositories;

import com.semihtumay.employeerecordapplication.entities.LuckyEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LuckyEmployeeRepository extends JpaRepository<LuckyEmployee, Long> {
}