package io.aiven.spring.mysql.Grocery.paramss;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {
    @JsonProperty("Id")
    private Integer id; // Corrected the attribute name to lowercase 'id'
    @JsonProperty("ProductName")
    private String productName; // Corrected the attribute name to lowercase 'productName'
    @JsonProperty("Qulty")
    private String qulty; // Corrected the attribute name to lowercase 'qulty'
    @JsonProperty("Price")
    private Integer price; // Corrected the attribute name to lowercase 'price'
    @JsonProperty("Image")
    private String image;

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
}
