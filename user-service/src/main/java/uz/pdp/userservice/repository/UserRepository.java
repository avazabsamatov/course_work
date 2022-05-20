package uz.pdp.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.userservice.entitiy.User;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(nativeQuery = true,value = "select * from users u where u.username=:ali")
    User findByUserName(String ali);
}
