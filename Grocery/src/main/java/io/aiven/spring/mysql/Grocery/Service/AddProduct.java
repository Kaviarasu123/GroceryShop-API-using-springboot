package io.aiven.spring.mysql.Grocery.Service;

import io.aiven.spring.mysql.Grocery.Model.ProductAcc;
import io.aiven.spring.mysql.Grocery.Model.UserAcc;
import io.aiven.spring.mysql.Grocery.Repository.AddProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Service
public class AddProduct {
    private AddProductRepository addProductRepository;
    @Autowired
    public AddProduct(AddProductRepository addProductRepository){
        this.addProductRepository=addProductRepository;
    }

    public void addproduct(String ProductName, String Qulty, Integer Price, String file){

        ProductAcc PA=new ProductAcc();
        PA.setProductName(ProductName);
        PA.setQulty(Qulty);
        PA.setPrice(Price);
        PA.setImage(file);
        addProductRepository.save(PA);

    }
   public boolean updateproduct(Integer id,String ProductName,String Qulty,Integer price,String image){
        Optional<ProductAcc> oop=addProductRepository.findById(id);
        if(oop.isPresent()){
            ProductAcc PAA=oop.get();
            PAA.setProductName(ProductName);
            PAA.setQulty(Qulty);
            PAA.setPrice(price);
            PAA.setImage(image);
            addProductRepository.save(PAA);
            return true;
        }
        else{
            return  false;
        }
   }
    public boolean deletebyid(Integer Id){
        Optional<ProductAcc> op=addProductRepository.findById(Id);
        if(op.isPresent()){
            addProductRepository.deleteById(Id);
            return true;
        }
        else{
            return false;
        }
    }
    public List<ProductAcc> searchByName(String name) {
        return addProductRepository.findByProductNameContaining(name);
    }

}
