package uz.pdp.courseservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.courseservice.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
     CourseService courseService;

    @GetMapping
    public HttpEntity<?> getAllCourse(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String search){
        return ResponseEntity.ok(courseService.getAllCourse(page,size,search));
    }

    @GetMapping("/course-with-module/{courseId}")
    public HttpEntity<?> getCourseById(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }
}
