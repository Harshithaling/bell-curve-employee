package com.harshithaling.bell_curveemployee.services;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.harshithaling.bell_curveemployee.model.Employee;
import com.harshithaling.bell_curveemployee.model.Category;


//Service class in Spring contains important business logic (like calculations, processing, and decision-making), but it's not directly visible to users.
@Service
public class BellCurveService {

    //Calculate the actual percentage distribution of employees across categories

    public Map<String, Double> calculateActualPercentage(List<Employee> employees, List<Category> categories) {

        //group the employees based on their rating catergory and count the occurance.
        Map<String, Long> countByCategory = employees.stream()
                .collect(Collectors.groupingBy(Employee::getRatingCategory, Collectors.counting()));


        //This simply counts how many employees are in total.
        int totalEmployees = employees.size();

        //This creates an empty map where we will store the actual percentage of employees in each category.
        Map<String, Double> actualPercentage = new HashMap<>();


        //calculate the percentage of employees in each category
        for (Category category : categories) {
            long count = countByCategory.getOrDefault(category.getName(), 0L);//get count or default to 0
            double percentage = (double) count / totalEmployees * 100; //calculate percentage
            actualPercentage.put(category.getName(), percentage);  //stores in map
        }

        return actualPercentage;
    }



    //calculate the deviation of actual percentages from the standard percentage.
    public Map<String, Double> calculateDeviation(Map<String, Double> actual, List<Category> categories) {

        //Map to store deviation values for each category
        Map<String, Double> deviation = new HashMap<>();

        //calculate deviation for each category
        for (Category category : categories) {
            double standard = category.getStandardPercentage();
            double actualPercentage = actual.getOrDefault(category.getName(), 0.0);
            deviation.put(category.getName(), actualPercentage - standard);
        }

        return deviation;
    }


    //Adjustments" are like telling a group of employees to move from one category to another, if there are too many employees in the one category group.
    public List<Employee> suggestAdjustment(Map<String, Double> deviation, List<Employee> employees) {
       
       //list will hold the employees who need to be adjusted
        List<Employee> adjustments = new ArrayList<>();

        //The loop checks if a category has too many employees compared to the standard and suggest adjustment
        for (Map.Entry<String, Double> entry : deviation.entrySet()) {
            if (entry.getValue() > 0) {
                String category = entry.getKey();
                adjustments.addAll(employees.stream()
                        .filter(emp -> emp.getRatingCategory().equals(category))//We only keep the employees who belong to the category (like "A").
                        .limit((long) Math.ceil(entry.getValue()))//We limit how many employees we want to move, based on the deviation (if deviation is 5%, we move a little).
                        .collect(Collectors.toList())); //Rounds up the number to make sure we don't move partial employees.
            }
        }

        return adjustments;
    }
}
