package io.aiven.spring.mysql.Grocery.Controller;
import io.aiven.spring.mysql.Grocery.Model.*;
import io.aiven.spring.mysql.Grocery.Repository.*;
import io.aiven.spring.mysql.Grocery.Service.AddProduct;
import io.aiven.spring.mysql.Grocery.Service.AddUserService;
import io.aiven.spring.mysql.Grocery.Service.CartService;
import io.aiven.spring.mysql.Grocery.paramss.ProductDTO;
import io.aiven.spring.mysql.Grocery.paramss.adduser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")
public class Controller1 {

    private final AddUserService addUserService;
    private final UserAccRepository userAccRepository;
    private final AddProduct addproduct;
    private final AddProductRepository addProductRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final ImageRepository imageRepository;


    @Autowired
    public Controller1(AddUserService addUserService,UserAccRepository userAccRepository,AddProduct addproduct,AddProductRepository addProductRepository,CartRepository cartRepository,CartService cartService,ImageRepository imageRepository) {
        this.addUserService = addUserService;
        this.userAccRepository=userAccRepository;
        this.addproduct=addproduct;
        this.addProductRepository=addProductRepository;
        this.cartRepository=cartRepository;
        this.cartService=cartService;
        this.imageRepository=imageRepository;
    }

    @PostMapping("/adduser")
    public @ResponseBody ResponseEntity<String> addUser(@RequestBody adduser add) {
        addUserService.adduser(add.getFirstName(),add.getLastName(),add.getEmail(),add.getGender(),add.getDOB(),add.getPassword());
        return ResponseEntity.ok("Entity saved successfully.");
    }

    @GetMapping("/alluser")
    public @ResponseBody Iterable<UserAcc> alluser(){
        return userAccRepository.findAll();
    }

    @PostMapping("/update")
    public @ResponseBody ResponseEntity<String> updateuser(@RequestBody adduser upd) {
        if(addUserService.update(upd.getId(),upd.getFirstName(),upd.getLastName(),upd.getEmail(),upd.getGender(),upd.getDOB(),upd.getPassword())) {
            return ResponseEntity.ok("Entity update successfully.");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/deleteall")
    public @ResponseBody ResponseEntity<String> deleteuser(){
        userAccRepository.deleteAll();
        return ResponseEntity.ok("record deleted successfully");
    }
    @PostMapping("/deletebyid")
        public @ResponseBody ResponseEntity<String> deletebyid(@RequestBody adduser dele){
        if(addUserService.deletebyid(dele.getId())) {
            return ResponseEntity.ok("Record deleted by id");
        }
        else {
            return ResponseEntity.ok("give valued Id");
        }
        }

        @PostMapping("/addproduct")
        public @ResponseBody String addproduct(@RequestBody ProductDTO add){
        addproduct.addproduct(add.getProductName(),add.getQulty(),add.getPrice(),add.getImage());
        return "HELLO";

        }
        @GetMapping("/showallproduct")
    public @ResponseBody Iterable<ProductAcc> showprod(){
        return addProductRepository.findAll();
        }

        @GetMapping("/deleteallproduct")
         public @ResponseBody ResponseEntity<String> deleteallprod(){
             addProductRepository.deleteAll();
                  return ResponseEntity.ok("deleted all record");

        }

        @PostMapping("/updateproduct")
    public @ResponseBody String updateproduct(@RequestBody ProductDTO update){
            if(addproduct.updateproduct(update.getId(),update.getProductName(),update.getQulty(),update.getPrice(),update.getImage())){
                return "product update successfully";
            }
            else{
                return "give value id";
            }

        }
        @PostMapping("/deleteprodid")
    public @ResponseBody String deleprodid(@RequestBody ProductDTO dele){
        if(addproduct.deletebyid(dele.getId())){
            return "deleted by id";
        }
        else{
            return "id is not validated";
        }

    }
    @GetMapping("/getprodbyid/{Id}")
    public @ResponseBody Optional<ProductAcc> gpid(@PathVariable Integer Id){
       return addProductRepository.findById(Id);
    }


    @PostMapping("/addcart")
    public @ResponseBody String addcartt(@RequestParam Integer Id){
        if(cartService.addcarted(Id)){
            return "Product added successfully";
        }
        else{
            return "invalid product id";
        }

    }
    @GetMapping("/viewcart")
    public @ResponseBody Iterable<Cart> showcart(){
        return cartRepository.findAll();
    }
    @GetMapping("/deleteallcart")
    public @ResponseBody String delecart(){
        cartRepository.deleteAll();
        return "all cart delete";
    }
    @PostMapping("/deleteallcartbyid")
    public @ResponseBody String idcartdele(@RequestParam Integer id){
        cartRepository.deleteById(id);
        return "Deleted";
    }
    @PostMapping("/searchprod")
    public @ResponseBody List<ProductAcc> searchProductsByName(@RequestParam String name) {
        return addproduct.searchByName(name);
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            ImageEntity image = new ImageEntity();
            image.setImageData(file.getBytes());
            imageRepository.save(image);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @GetMapping("/showimage/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        ImageEntity image = imageRepository.findById(id).orElse(null);
        if (image != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getImageData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/allimgdelete")
    public ResponseEntity<String> deleimg(){
        imageRepository.deleteAll();
        return ResponseEntity.ok("all record deleted");

    }
    @PostMapping("/imgdeletebyid")
    public ResponseEntity<String> deleimgbyid(@RequestParam Long id){
        imageRepository.deleteById(id);
        return ResponseEntity.ok("image deleted successfully");
    }

    @PostMapping("/showproductandimage")
    public String ppii(@RequestParam Integer id, Model model) {
        ProductAcc productAcc = addProductRepository.findById(id).orElse(null);
        if (productAcc != null) {
            if (id == 53) {
                ImageEntity image = imageRepository.findById(2L).orElse(null);
                if (image != null) {
                    ProductDetailsModel productDetailsModel = new ProductDetailsModel();
                    byte[] imageData = image.getImageData();
                    String base64ImageData = Base64.getEncoder().encodeToString(imageData);
                    productDetailsModel.setImageData(base64ImageData);

                    // Set other attributes from ProductAcc entity
                    productDetailsModel.setProductName(productAcc.getProductName());
                    productDetailsModel.setQulty(productAcc.getQulty());
                    productDetailsModel.setPrice(productAcc.getPrice());

                    // Add the custom model object as a model attribute
                    model.addAttribute("productDetails", productDetailsModel);
                    return "productDetailsPage"; // Return the view name
                }
            }
        }
        // Handle other cases or errors here
        return "errorPage";
    }




}
