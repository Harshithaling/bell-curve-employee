package com.harshithaling.bell_curveemployee.model;

import lombok.AllArgsConstructor;
import lombok.Data;


//It is shortcut that automatically creates useful things like : getting the name of the category , setting the name , checking if two categories are the same  and printing category details.
@Data

//lombok annotation,whenever we create a new category, we must give it all the details it needs.
@AllArgsConstructor


public class Category {

    //it stores the name of the category (example : "A" , "B")
    private String name;

    //this is the number stores a percentage (example : 10.0 for 10%)
    private double standardPercentage;

}
