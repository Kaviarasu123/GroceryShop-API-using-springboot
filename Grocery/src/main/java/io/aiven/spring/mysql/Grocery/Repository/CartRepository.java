package io.aiven.spring.mysql.Grocery.Repository;

import io.aiven.spring.mysql.Grocery.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {

}
