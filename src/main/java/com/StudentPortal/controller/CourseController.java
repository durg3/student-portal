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

import com.StudentPortal.model.Course;
import com.StudentPortal.repo.CourseRepo;
import com.StudentPortal.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value = "")
public class CourseController {

	@Autowired
	CourseRepo courseRepo;
	
	@ResponseBody
	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public List<Course> getCourseDetails() {
		List<Course> courseresponse = (List<Course>) courseRepo.findAll();

		return courseresponse;
	}
	
	@PostMapping("/course")
	public Course createCourse(@RequestBody Course course) {
		return courseRepo.save(course);
		
	}
	
	@GetMapping("/course/{courseId}")
	public ResponseEntity<Course> getCourseById(@PathVariable Integer courseId) {
		Course course =courseRepo.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("course not exist with courseId :" + courseId));
		return ResponseEntity.ok(course);
	}
	
	@PutMapping("/course/{courseId}")
	public ResponseEntity<Course> updateCourse(@PathVariable Integer courseId, @RequestBody Course courseDetails){
		Course course = courseRepo.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not exist with courseId :" + courseId));
		
		course.setCourseId(courseDetails.getCourseId());
		course.setCourseName(courseDetails.getCourseName());
		course.setCourseFee(courseDetails.getCourseFee());
		
		
		Course updatedCourse = courseRepo.save(course);
		return ResponseEntity.ok(updatedCourse);
	}
	
	@DeleteMapping("/course/{courseId}")
	public ResponseEntity<Map<String, Boolean>> deleteCourse(@PathVariable Integer courseId){
		Course course = courseRepo.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not exist with courseId :" + courseId));
		
		courseRepo.delete(course);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
