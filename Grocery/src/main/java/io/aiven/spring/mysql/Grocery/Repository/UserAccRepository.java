package io.aiven.spring.mysql.Grocery.Repository;

import io.aiven.spring.mysql.Grocery.Model.UserAcc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccRepository extends JpaRepository<UserAcc,Integer> {

    void deleteById(Integer id);
    Optional<UserAcc> findById(Integer id);
}
