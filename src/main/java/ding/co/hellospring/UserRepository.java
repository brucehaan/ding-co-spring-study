package ding.co.hellospring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);

    @Query("select u from User u where u.email = ?1 and u.name = ?2")
    List<User> findUserCustom(String email, String name);


}
