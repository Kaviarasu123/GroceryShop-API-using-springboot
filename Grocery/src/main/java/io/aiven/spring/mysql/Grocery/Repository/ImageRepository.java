package io.aiven.spring.mysql.Grocery.Repository;

import io.aiven.spring.mysql.Grocery.Model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Long> {

}
