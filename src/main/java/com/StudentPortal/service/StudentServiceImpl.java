package com.StudentPortal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.StudentPortal.model.Course;
import com.StudentPortal.model.Student;
import com.StudentPortal.repo.StudentRepo;

public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepo studentRepo;
	
	public Student findByStudentId(int studentId) {
		Optional<Student> studentResponse = studentRepo.findById(studentId);
		Student student = studentResponse.get();
		return student;
	}

	public List<Student> saveStudent(List<Student> studentList) {

//		
//		Student student1 = studentList.get(0);
//		Student student2 = studentList.get(1);
//
//		
//		Course course1 = new Course();
//		Course course2 = new Course();
//		course1.setCourseName("Course name 1");
//		course2.setCourseName("Course name 2");
//
//	
//		student1.getCourseSet().add(course1);
//		student1.getCourseSet().add(course2);
//		student2.getCourseSet().add(course1);
//		student2.getCourseSet().add(course2);
//
//	
//		course1.getStudentSet().add(student1);
//		course1.getStudentSet().add(student2);
//		course2.getStudentSet().add(student1);
//		course2.getStudentSet().add(student2);
//
//		
		List<Student> studentResponse = (List<Student>) studentRepo.saveAll(studentList);

		return studentResponse;

	}
}
