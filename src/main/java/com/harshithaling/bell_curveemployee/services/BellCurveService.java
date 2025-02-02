package com.harshithaling.bell_curveemployee.services;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.harshithaling.bell_curveemployee.model.Employee;
import com.harshithaling.bell_curveemployee.model.Category;

@Service
public class BellCurveService {

    public Map<String, Double> calculateActualPercentage(List<Employee> employees, List<Category> categories) {
        Map<String, Long> countByCategory = employees.stream()
                .collect(Collectors.groupingBy(Employee::getRatingCategory, Collectors.counting()));

        int totalEmployees = employees.size();
        Map<String, Double> actualPercentage = new HashMap<>();

        for (Category category : categories) {
            long count = countByCategory.getOrDefault(category.getName(), 0L);
            double percentage = (double) count / totalEmployees * 100;
            actualPercentage.put(category.getName(), percentage);
        }

        return actualPercentage;
    }

    public Map<String, Double> calculateDeviation(Map<String, Double> actual, List<Category> categories) {
        Map<String, Double> deviation = new HashMap<>();

        for (Category category : categories) {
            double standard = category.getStandardPercentage();
            double actualPercentage = actual.getOrDefault(category.getName(), 0.0);
            deviation.put(category.getName(), actualPercentage - standard);
        }

        return deviation;
    }

    public List<Employee> suggestAdjustment(Map<String, Double> deviation, List<Employee> employees) {
        List<Employee> adjustments = new ArrayList<>();

        for (Map.Entry<String, Double> entry : deviation.entrySet()) {
            if (entry.getValue() > 0) {
                String category = entry.getKey();
                adjustments.addAll(employees.stream()
                        .filter(emp -> emp.getRatingCategory().equals(category))
                        .limit((long) Math.ceil(entry.getValue()))
                        .collect(Collectors.toList()));
            }
        }

        return adjustments;
    }
}
