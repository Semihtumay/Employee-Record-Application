package com.semihtumay.employeerecordapplication.controllers;

import com.semihtumay.employeerecordapplication.services.LuckyEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lucky")
public class LuckyEmployeeController {
    final LuckyEmployeeService luckyEmployeeService;

    public LuckyEmployeeController(LuckyEmployeeService luckyEmployeeService) {
        this.luckyEmployeeService = luckyEmployeeService;
    }

    @GetMapping("/winner")
    public ResponseEntity winner(){
        return luckyEmployeeService.getWinner();
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        return luckyEmployeeService.list();
    }
}
