package uz.pdp.lessonservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.lessonservice.entitiy.Lesson;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
    @Query(nativeQuery = true,value =
    "select * from lessons l where l.module_id=:id   ")
    List<Lesson> getLessonsByModuleId(Long id);
}
