package com.harshithaling.bell_curveemployee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harshithaling.bell_curveemployee.model.Employee;



//It works like a librarian who takes care of employees in a database.
@Repository


public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}



//JpaRepository : already knows how to save , find , delete or list all the employees..If we didn’t use JpaRepository, we would have to write long code to save, find, and delete employees. 
