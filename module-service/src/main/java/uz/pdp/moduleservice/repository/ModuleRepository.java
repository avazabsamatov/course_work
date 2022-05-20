package uz.pdp.moduleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.moduleservice.entitiy.Modules;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Modules,Long> {
    @Query(nativeQuery = true,
    value = "select *\n" +
            "from modules m where m.course_id=:courseId and m.is_active=true ")
    List<Modules> findByCourseId(Long courseId);
}
