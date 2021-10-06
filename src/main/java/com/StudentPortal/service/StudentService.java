package com.StudentPortal.service;
import java.util.List;
import org.springframework.stereotype.Component;
import com.StudentPortal.model.Student;


@Component
public interface StudentService {
	
	public List<Student> saveStudent(List<Student> studentList);

}
