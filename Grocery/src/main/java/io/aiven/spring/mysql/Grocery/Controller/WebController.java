package io.aiven.spring.mysql.Grocery.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {



        @GetMapping("/demo/addimage")
    public String addimage(){
            return "Addimage";
        }
        @GetMapping("demo/viewproddetails")
    public String showprod(){
            return "viewprod";
        }
        @GetMapping("/demo/addprod")
        public String addproductt(){
            return "Addpro";
        }


}
