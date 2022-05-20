package uz.pdp.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.userservice.entitiy.Modules;
import uz.pdp.userservice.projection.ModuleProjection;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Modules,Long> {
    @Query(nativeQuery = true,
    value = "")
    List<ModuleProjection> getModuleByUserid(Long currentId);
}
