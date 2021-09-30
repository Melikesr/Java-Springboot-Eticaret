package com.example.Eticaret.Shopping;

import com.example.Eticaret.product.Product;
import com.example.Eticaret.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ShoppingController {


    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private  ShoppingRepository shoppingRepo;

    @GetMapping("/products_get")
    public String showEditProductForm(Model model) {
        List<Product> listProducts=productRepo.findAll();
        model.addAttribute("listProducts",listProducts);
        return "products_get";
    }

    @GetMapping("/shopping/new")
    public String showCreateNewShoppingForm(Model model){

        model.addAttribute("shopping",new Shopping());
        List<String> stocknumberlist= Arrays.asList("1","2","3","4","5","6","7","8","9");
        model.addAttribute("stocknumberlist",stocknumberlist);


        return "shopping";
    }

    @PostMapping("/shopping/save")
    public String saveShopping(@ModelAttribute("shopping") Shopping shopping){
        shoppingRepo.save(shopping);
        System.out.println(shopping);
        return "redirect:/shopping_success";
    }

    @GetMapping("/shopping/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        shoppingRepo.deleteById(id);
        return "redirect:/shopping_get";
    }

}
