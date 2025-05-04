package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.service.*;

public class StudentController {

	private StudentService studentService;
	
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    
}
