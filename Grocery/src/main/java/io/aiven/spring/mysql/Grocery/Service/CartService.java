package io.aiven.spring.mysql.Grocery.Service;
import io.aiven.spring.mysql.Grocery.Model.Cart;
import io.aiven.spring.mysql.Grocery.Model.ProductAcc;
import io.aiven.spring.mysql.Grocery.Repository.AddProductRepository;
import io.aiven.spring.mysql.Grocery.Repository.CartRepository;
import io.aiven.spring.mysql.Grocery.Repository.UserAccRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final AddProductRepository addProductRepository;

    @Autowired
    public CartService(CartRepository cartRepository,AddProductRepository addProductRepository){
        this.cartRepository=cartRepository;
        this.addProductRepository=addProductRepository;
    }

    public boolean addcarted(Integer Id){
        Optional<ProductAcc> op=addProductRepository.findById(Id);
        if(op.isPresent()){
            ProductAcc ProAcc= op.get();
            Cart ca=new Cart();
            ca.setProductName(ProAcc.getProductName());
            ca.setQulty(ProAcc.getQulty());
            ca.setPrice(ProAcc.getPrice());
            ca.setImage(ProAcc.getImage());
            cartRepository.save(ca);
            return true;
        }
        else {
            return false;
        }
    }
}
