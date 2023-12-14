package com.tom.demo.kotlin.controller

import com.tom.demo.kotlin.model.Employee
import com.tom.demo.kotlin.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/employees")
class EmployeeController(@Autowired private val employeeService: EmployeeService) {

    //gets all employees
    @GetMapping
    fun getAllEmployees(): ResponseEntity<List<Employee>> =
        ResponseEntity.status(HttpStatus.OK)
            .body(employeeService.getAllEmployees())


    //gets the requested employee
    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: Int): Employee =
        employeeService.getEmployeeById(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "This employee does not exist"
        )


    //creates a new employee
    @PostMapping
    fun saveEmployees(@RequestBody employee: Employee): ResponseEntity<Employee> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(employeeService.saveEmployees(employee))

    }


    // deletes an existing employee
    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: Int): ResponseEntity<Any> {
        try {
            employeeService.deleteEmployee(id)
        } catch (e: Exception) {
            // can handle it by adding to logger
        }
        return ResponseEntity.noContent().build()

    }

}