package com.week2.introMvc.Mvc.controllers;

import com.week2.introMvc.Mvc.dto.EmployeeDto;
import com.week2.introMvc.Mvc.entities.EmployeeEntity;
import com.week2.introMvc.Mvc.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController //  This annotation is used at the class level and
// allows the class to handle the requests made by the client.

@RequestMapping(path = "/employees") //The @RequestMapping annotation can be applied to class-level and/or method-level in a controller. The class-level annotation maps a specific request path or pattern onto a controller. You can then apply additional
// method-level annotations to make mappings more specific to handler methods.
//map request to controller methods

public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeRepository.findById(id).orElse(null);
    }
//    @GetMapping(path="/getSecretMessage")
//    public String getMySuperSecretMessage(){
//    return "Secret message: Hello world";
//}
    @GetMapping()
    //by default  Request Param - true
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = true, name = "inputAge") Integer age, @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }

//    @GetMapping(path="/{employeeId}")
//    // I can remove @GetMapping(path="/employees/{employeeId}") -> employees from here as I added @RequestMapping it will
//    public EmployeeDto getEmployeeId(@PathVariable Long employeeId){
//        return new EmployeeDto(employeeId, "manya" ,"manya@gmail.com", 27, LocalDate.of(2024,06,01),true);
//    }

    @GetMapping(path="/{employeeId}/{name}")
    public EmployeeDto getEmployeeIdAndName(@PathVariable Long employeeId ,@PathVariable String name){
        return new EmployeeDto(employeeId, name ,"manya@gmail.com", 27, LocalDate.of(2024,06,01),true);
    }


//    @PostMapping
//    public EmployeeDto createNewEmployees(@RequestBody EmployeeDto employeeDto) // RequestBody maps data from json to java object
//    {
//        // @RequestBody is used to bind the HTTP request body to a Java object when the client sends data in the body of the request.
//        //Typically used in post , put, patch methos where client sends data  that needs to be processed by server.
//        //Converts JSON or XML data from the request body into a java object using message converter(Jackson for JSON).
//        employeeDto.setId(100L);
//        return employeeDto;
//
//    }
    @PostMapping
    public EmployeeEntity createNewEmployees(@RequestBody EmployeeEntity inputEmployee) // RequestBody maps data from json to java object
    {
       return employeeRepository.save(inputEmployee);

    }

    @PutMapping
    public String updateEmployeeById()
    {
        return "I am Put Request";
    }
}
