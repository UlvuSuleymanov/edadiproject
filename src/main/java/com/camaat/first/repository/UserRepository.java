package com.camaat.first.repository;

import com.camaat.first.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("update User u set u.name = ?1, u.email = ?2 ,u.username=?3 where u.username = ?4")
    void updateUser(String name, String email, String NewUsername,String username);

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Long findIdByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);



    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
