package io.aiven.spring.mysql.Grocery.Model;

import jakarta.persistence.*;

@Entity
public class ProductAcc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Corrected the attribute name to lowercase 'id'
    private String productName; // Corrected the attribute name to lowercase 'productName'
    private String qulty; // Corrected the attribute name to lowercase 'qulty'
    private Integer price; // Corrected the attribute name to lowercase 'price'

    private String image;

    private byte[] imageData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQulty() {
        return qulty;
    }

    public void setQulty(String qulty) {
        this.qulty = qulty;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
