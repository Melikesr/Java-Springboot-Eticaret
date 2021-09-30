package com.example.Eticaret.product;

import com.example.Eticaret.FileUploadUtil;
import com.example.Eticaret.brand.Brand;
import com.example.Eticaret.brand.BrandRepository;
import com.example.Eticaret.category.Category;
import com.example.Eticaret.category.CategoryRespository;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private BrandRepository brandRepo;

    @GetMapping("/products/new")
    public String showNewProductForm(Model model) {
        List<Brand> listBrand = brandRepo.findAll();
        model.addAttribute("listBrand", listBrand);

        model.addAttribute("product", new Product());

        List <String> stockList= Arrays.asList("var","yok");
        model.addAttribute("stockList",stockList);

        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(Product product,  @RequestParam("primaryImage")MultipartFile mainMultipartFile,
    HttpServletRequest request) throws IOException {

       String[] detailIDs=request.getParameterValues("detailID");
       String[] detailNames=request.getParameterValues("detailName");
       String[] detailValues=request.getParameterValues("detailValue");


       for (int i=0; i<detailNames.length; i++){

           if (detailIDs != null && detailIDs.length > 0) {

               product.setDetail(Integer.valueOf(detailIDs[i]),detailNames[i],detailValues[i]);
           }
           else
           {
               product.addDetail(detailNames[i],detailValues[i]);
           }
       }
       String mainImageName= StringUtils.cleanPath(mainMultipartFile.getOriginalFilename());
       product.setMainImage(mainImageName);


        Product savedProduct=  productRepo.save(product);

        String uploadDir="./product-images/"+ savedProduct.getId();

        FileUploadUtil.saveFile(uploadDir, mainMultipartFile, mainImageName);

        return "redirect:/products";
    }

    @GetMapping("/products")
    public String listProducts(Model model) {

        List<Product> listProducts=productRepo.findAll();

        model.addAttribute("listProducts",listProducts);

        return "products";
    }


    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id, Model model) {
        Product product = productRepo.findById(id).get();
        model.addAttribute("product", product);

        List <String> stockList= Arrays.asList("var","yok");
        model.addAttribute("stockList",stockList);

        List<Brand> listBrand=brandRepo.findAll();
        model.addAttribute("listBrand",listBrand);

        return "product_form";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        productRepo.deleteById(id);


        return "redirect:/products";
    }
}