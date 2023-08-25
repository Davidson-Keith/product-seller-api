package au.com.keithdavidson.productsellerapi.repository;

import au.com.keithdavidson.productsellerapi.model.Role;
import au.com.keithdavidson.productsellerapi.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    @Modifying()
    @Query("update User set role = :role where userName = :userName")
    void updateUserRole(@Param("userName") String userName, @Param("role") Role role);
}
