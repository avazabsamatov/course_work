package uz.pdp.courseservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.courseservice.entitiy.Course;
import uz.pdp.courseservice.projection.CourseProjection;

public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query(
            nativeQuery = true,
            value = "select c.id," +
                    "       c.name," +
                    "       c.description" +
                    "       from courses c where lower(c.name) like lower(concat('%', :search, '%'))"
    )
    Page<CourseProjection> getAllCourses(Pageable pageable, String search);
}
