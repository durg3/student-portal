package com.StudentPortal.repo;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.StudentPortal.model.Course;

@Repository
public interface CourseRepo extends CrudRepository<Course, Serializable> {

}