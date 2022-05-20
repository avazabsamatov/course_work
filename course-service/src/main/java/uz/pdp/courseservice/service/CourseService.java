package uz.pdp.courseservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.courseservice.dto.CourseDto;
import uz.pdp.courseservice.entitiy.Course;
import uz.pdp.courseservice.projection.CourseProjection;
import uz.pdp.courseservice.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    RestTemplate restTemplate;

    public Page<CourseProjection> getAllCourse(Integer page, Integer size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseProjection> courses = courseRepository.getAllCourses(pageable, search);
        return courses;
    }

    @HystrixCommand(fallbackMethod = "courseFallBackMethod")
    public CourseDto getCourseById(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(courseOptional.isPresent()){
            Course course = courseOptional.get();
            List courseForObject  = restTemplate.getForObject(
                    "http://localhost:9092/api/module/course/{courseId}",
                    List.class,
                    course.getId()
            );
            CourseDto courseDto = new CourseDto();
            courseDto.setId(course.getId());
            courseDto.setName(course.getName());
            courseDto.setDescription(course.getDescription());
            courseDto.setModules(courseForObject);
            return courseDto;
        }
       return null;
    }
}
