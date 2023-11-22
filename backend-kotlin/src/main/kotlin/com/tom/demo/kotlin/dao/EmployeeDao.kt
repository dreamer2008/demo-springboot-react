package com.tom.demo.kotlin.dao

import com.tom.demo.kotlin.model.Employee
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface EmployeeDao : CrudRepository<Employee, Int> {

    @Query(value = "SELECT * FROM employee WHERE fullName = :fullName", nativeQuery = true)
    fun findByFullName(fullName: String): MutableList<Employee>

}
