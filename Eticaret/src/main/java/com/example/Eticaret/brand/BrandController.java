package com.example.Eticaret.brand;
import com.example.Eticaret.FileUploadUtil;
import com.example.Eticaret.category.Category;
import com.example.Eticaret.category.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class BrandController {

    @Autowired
    private BrandRepository brandRepo;

    @Autowired
    private CategoryRespository categoryRepo;

    @GetMapping("/brands/new")
    public String showCreateNewBrandForm(Model model) {
        List<Category> listCategories = categoryRepo.findAll();
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("brand", new Brand());

        return "brand_form";

    }

    @PostMapping("/brands/save")
    public String saveBrand(@ModelAttribute(name = "brand") Brand brand ,
        @RequestParam("fileImage")MultipartFile multipartFile) throws IOException {

        String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
        brand.setLogo(fileName);
      Brand savedBrand=  brandRepo.save(brand);
      String uploadDir="./brand-logos/"+savedBrand.getId();
        FileUploadUtil.saveFile(uploadDir, multipartFile, fileName);

        Path uploadPath= Paths.get(uploadDir);

        if (!Files.exists(uploadPath)){

            Files.createDirectories(uploadPath);
        }

       try (  InputStream inputStream = multipartFile.getInputStream()){

           Path filePath = uploadPath.resolve(fileName);
           Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
       }catch (IOException e){
           throw  new IOException("Could not uploaded file: "+fileName);
       }
        return "redirect:/brands";
    }

    @GetMapping("/brands")
    public String listBrands(Model model) {
        List<Brand> listBrands = brandRepo.findAll();
        model.addAttribute("listBrands", listBrands);

        return "brands";
    }

    @GetMapping("/brands/edit/{id}")
    public String showEditBrandForm(@PathVariable("id") Integer id, Model model) {
        List<Category> listCategories = categoryRepo.findAll();
        Brand brand = brandRepo.findById(id).get();

         model.addAttribute("listCategories", listCategories);
        model.addAttribute("brand", brand);

        return "brand_form";

    }
    @GetMapping("/brands/delete/{id}")
    public String deleteBrand(@PathVariable("id") Integer id, Model model) {
        brandRepo.deleteById(id);


        return "redirect:/brands";
    }
}
