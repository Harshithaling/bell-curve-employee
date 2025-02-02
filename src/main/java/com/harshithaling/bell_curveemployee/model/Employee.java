package com.harshithaling.bell_curveemployee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//It is shortcut that automatically creates useful things like : getting the name of the category , setting the name , checking if two categories are the same  and printing category details.
@Data


//this creates an empty employee without any details at first if needed
@NoArgsConstructor


//this create employee , but we must provide details
@AllArgsConstructor


//automatically connects to a database
@Entity
public class Employee {


    //primary key or unique employee id (like a roll numbers in school)
    @Id
    private Long id;

    //name of the employee
    private String name;

    //rating category assigned to the employee(example : "A" , "B" , "C")
    private String ratingCategory;

}
