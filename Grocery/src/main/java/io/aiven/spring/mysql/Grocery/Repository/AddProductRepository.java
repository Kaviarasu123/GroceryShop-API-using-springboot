package io.aiven.spring.mysql.Grocery.Repository;

import io.aiven.spring.mysql.Grocery.Model.ProductAcc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddProductRepository extends JpaRepository<ProductAcc,Integer> {
    List<ProductAcc> findByProductNameContaining(String productName);
}
