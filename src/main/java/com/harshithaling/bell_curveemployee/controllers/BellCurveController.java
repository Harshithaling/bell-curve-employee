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


//tells Spring that this class will handle HTTP requests and send responses (this is for creating a web API).
@RestController

//allow cross origin request from a specific frontend application
@CrossOrigin(origins = "http://127.0.0.1:5500")


//used to define a base URL for all the methods in this controller.
@RequestMapping("/api/bell-curve")


public class BellCurveController {

    //inject BellcurveService to use its method for business logic
    @Autowired
    private BellCurveService service;

    //inject EmployeeRepo to interact with database for employee related operations
    @Autowired
    private EmployeeRepo employeeRepo;


    @GetMapping("/analyze")
    public ResponseEntity<Map<String, Object>> analyze()
    {
        //define performane categories and their percentage
        List<Category> categories = List.of(
            new Category("A", 10.0),
            new Category("B", 20.0),
            new Category("C", 40.0),
            new Category("D", 20.0),
            new Category("E", 10.0)
        );      


        //fetch all employees from the database
        List<Employee> employees =employeeRepo.findAll();

        //calculate the actual performane percentage for ec=ach category
        Map<String, Double> actual = service.calculateActualPercentage(employees, categories);

        //calculate the deviation(difference from standard percentage)
        Map<String, Double> deviation = service.calculateDeviation(actual, categories);

        //give suggestions for performance adjustment based on deviation
        List<Employee> suggestions = service.suggestAdjustment(deviation, employees);

        Map<String, Object> response = new HashMap<>();
        response.put("actualPercentage", actual);
        response.put("deviation", deviation);
        response.put("adjustments", suggestions);

        //return the response as JSON with HTTP status 200 (ok)
        return ResponseEntity.ok(response);
    }


    //add a single employee to the database
    @PostMapping("/add")

    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
    {
        //save the employee object to the database
        Employee savedEmployee = employeeRepo.save(employee);

        //return the saved employee object with HTTP status 201
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }


    //add multiple employees to the database at once
    @PostMapping("/add-all")
    public ResponseEntity<List<Employee>> addEmployees(@RequestBody List<Employee> employees)
    {
        //save all employee object to the database
        List<Employee> savedEmployees = employeeRepo.saveAll(employees);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployees);

    }

    //fetch all employees from the database
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        //retrieve all employees from the database
        List<Employee> employees = employeeRepo.findAll();

        //return the list of employees with HTTP statuc 200 (ok)
        return ResponseEntity.ok(employees);
    }

}
