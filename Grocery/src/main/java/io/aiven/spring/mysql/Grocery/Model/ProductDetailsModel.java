package io.aiven.spring.mysql.Grocery.Model;

public class ProductDetailsModel    {
    private String imageData;
    private String productName;
    private String qulty;
    private Integer price;

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
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
}
