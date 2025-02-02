package com.harshithaling.bell_curveemployee.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.harshithaling.bell_curveemployee.model.Category;
import com.harshithaling.bell_curveemployee.model.Employee;
import com.harshithaling.bell_curveemployee.repositories.EmployeeRepo;
import com.harshithaling.bell_curveemployee.services.BellCurveService;

@RestController


@CrossOrigin(origins =  "http://localhost:3000")

@RequestMapping("/api/bell-curve")


public class BellCurveController {


    @Autowired
    private BellCurveService service;


    @Autowired
    private EmployeeRepo employeeRepo;


    @GetMapping("/analyze")
    public ResponseEntity<Map<String, Object>> analyze()
    {
        List<Category> categories = List.of(
            new Category("A", 10.0),
            new Category("B", 20.0),
            new Category("C", 40.0),
            new Category("D", 20.0),
            new Category("E", 10.0)
        );      

        List<Employee> employees =employeeRepo.findAll();

        Map<String, Double> actual = service.calculateActualPercentage(employees, categories);

        Map<String, Double> deviation = service.calculateDeviation(actual, categories);

        List<Employee> suggestions = service.suggestAdjustment(deviation, employees);

        Map<String, Object> response = new HashMap<>();
        response.put("actualPercentage", actual);
        response.put("deviation", deviation);
        response.put("adjustments", suggestions);


        return ResponseEntity.ok(response);
    }



    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
    {
        Employee savedEmployee = employeeRepo.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }



    @PostMapping("/add-all")
    public ResponseEntity<List<Employee>> addEmployees(@RequestBody List<Employee> employees)
    {
        List<Employee> savedEmployees = employeeRepo.saveAll(employees);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployees);

    }


    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        List<Employee> employees = employeeRepo.findAll();

        return ResponseEntity.ok(employees);
    }

}
