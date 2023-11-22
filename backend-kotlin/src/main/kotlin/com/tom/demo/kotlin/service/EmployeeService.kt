package com.tom.demo.kotlin.service

import com.tom.demo.kotlin.dao.EmployeeDao
import com.tom.demo.kotlin.model.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeService(@Autowired private val employeeDao: EmployeeDao) {

    fun getAllEmployees(): List<Employee> =
        employeeDao.findAll().toList()

    fun getEmployeeById(id: Int): Employee? =
        employeeDao.findById(id).get()

    fun saveEmployees(employee: Employee): Employee {
        return employeeDao.save(employee);
    }

//    fun updateEmployee(id: Int, employee: Employee): Employee {
//    }

    fun deleteEmployee(id: Int) {
        employeeDao.deleteById(id)
    }

}