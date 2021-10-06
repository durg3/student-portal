package com.StudentPortal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.StudentPortal.model.Student;
import com.StudentPortal.repo.StudentRepo;
import com.StudentPortal.service.StudentService;
import com.StudentPortal.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value = "")
public class StudentController {
	
//	@Autowired
//	private StudentService studentService;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@ResponseBody
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public List<Student> getStudentDetails() {
		List<Student> studentResponse = (List<Student>) studentRepo.findAll();

		return studentResponse;
	}
	
	@PostMapping("/student")
	public Student createStudent(@RequestBody Student student) {
		return studentRepo.save(student);
		
	}
	
	@GetMapping("/student/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable Integer studentId) {
		Student student =studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with StudentId :" + studentId));
		return ResponseEntity.ok(student);
	}
	
	@PutMapping("/student/{studentId}")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer studentId, @RequestBody Student studentDetails){
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with studentId :" + studentId));
		
		student.setStudentId(studentDetails.getStudentId());
		student.setStudentName(studentDetails.getStudentName());
		student.setStudentAge(studentDetails.getStudentAge());
		student.setStudentContact(studentDetails.getStudentContact());
		Student updatedStudent = studentRepo.save(student);
		return ResponseEntity.ok(updatedStudent);
	}
	
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Integer studentId){
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with studentId :" + studentId));
		
		studentRepo.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	

}
