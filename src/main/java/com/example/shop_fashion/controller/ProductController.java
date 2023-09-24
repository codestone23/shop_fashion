package com.example.shop_fashion.controller;


import com.example.shop_fashion.dto.*;
import com.example.shop_fashion.repository.SizeRepository;
import com.example.shop_fashion.services.impl.*;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@MultipartConfig
//@RequestMapping("/admin")
public class ProductController {
    @Value("C:\\intelli IDEA\\shop_fashion\\src\\main\\resources\\static\\img\\product\\")
    private String path_file;
    @Autowired
    public SizeRepository sizeRepository;
    @Autowired
    public DetailsImgServices detailsImgServices;
    @Autowired
    public ProductServices productServices;
    @Autowired
    public BrandServices brandServices;
    @Autowired
    public CategoryServices categoryServices;
    @Autowired
    public ColorServices colorServices;
    @Autowired
    public GenderServices genderServices;
    @Autowired
    public SizeServices sizeServices;
    @Autowired
    public StatusServices statusServices;
    @GetMapping("/saveproduct")
    public String saveProduct(Model model, Pageable pageable,@RequestParam("name_search") String name_search){
        Page<BrandDTO> brandDTOS = brandServices.getAll(pageable);
        Page<CategoryDTO> categoryDTOS = categoryServices.getAll(pageable);
        Page<ColorDTO> colorDTOS = colorServices.getAll(pageable);
        Page<GenderDTO> genderDTOS = genderServices.getAll(pageable);
        Page<SizeDTO> sizeDTOS = sizeServices.getAll(pageable);
        Page<StatusDTO> statusDTOS = statusServices.getAll(pageable);
        model.addAttribute("brands",brandDTOS);
        model.addAttribute("categorys",categoryDTOS);
        model.addAttribute("colors",colorDTOS);
        model.addAttribute("genders",genderDTOS);
        model.addAttribute("sizes",sizeDTOS);
        model.addAttribute("statuses",statusDTOS);
        model.addAttribute("productDto",new ProductDTO());
        model.addAttribute("id_sizes_select",new HashSet<Long>());
        model.addAttribute("id_colors_select",new HashSet<Long>());
        model.addAttribute("name_search",name_search);
        return "add-product";
    }
    @PostMapping("save")
    public String save(Model model
            ,@ModelAttribute ProductDTO productDTO
                       ,@RequestParam(value = "id_sizes_select",required = false) List<Long> id_sizes
                       ,@RequestParam(value = "id_colors_select",required = false) Set<Long> id_colors
                       ,@RequestParam("name_search") String name_search
            ,@RequestParam("page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size
    ) {
        try {
            MultipartFile file = productDTO.getFile();
            Set<MultipartFile> fileSet = productDTO.getFiles();
            if (!file.isEmpty()) {
                String name_img = file.getOriginalFilename();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path_file+ File.separator+name_img));
                stream.write(file.getBytes());
                stream.close();
                productDTO.setImg(name_img);
            }

            productDTO.setBrand(brandServices.findById(productDTO.getBrand().getId()));
            productDTO.setCategory(categoryServices.findById(productDTO.getCategory().getId()));
            productDTO.setGender(genderServices.findById(productDTO.getGender().getId()));
            productDTO.setStatus(statusServices.findById(productDTO.getStatus().getId()));
            Set<SizeDTO> sizeDTOS = new HashSet<>();
            Set<ColorDTO> colorDTOS = new HashSet<>();
            for(Long id_size:id_sizes){
                sizeDTOS.add(sizeServices.findById(id_size));
            }
            for (Long id_color:id_colors){
                colorDTOS.add(colorServices.findById(id_color));
            }
            productDTO.setSizes(sizeDTOS);
            productDTO.setColors(colorDTOS);
            productServices.save(productDTO);
            ProductDTO product = productServices.findProductByName(productDTO.getName());
            if (!(fileSet.size() == 1) || !fileSet.iterator().next().isEmpty()) {
                for (MultipartFile file1: fileSet){
                    String name_img1 = file1.getOriginalFilename();
                    BufferedOutputStream stream1 = new BufferedOutputStream(new FileOutputStream(path_file+ File.separator+name_img1));
                    stream1.write(file1.getBytes());
                    DetailsImgDTO detailsImgDTO = new DetailsImgDTO(name_img1,product);
                    detailsImgServices.save(detailsImgDTO);
                    stream1.close();
                }
            }

            ProductDTO productDTO1 = productServices.findProductByNameAll(product.getName());
            for (SizeDTO sizeDTO:sizeDTOS){
                Set<ProductDTO> product1 = sizeDTO.getProductDTOSet();
                product1.add(productDTO1);
                sizeDTO.setProductDTOSet(product1);
                sizeServices.update(sizeDTO);
            }
            for (ColorDTO colorDTO:colorDTOS){
                Set<ProductDTO> product1 = colorDTO.getProductDTOSet();
                product1.add(productDTO1);
                colorDTO.setProductDTOSet(product1);
                colorServices.update(colorDTO);
            }
            int pageIndex = page.orElse(1);
            int pageSize = size.orElse(5);
            Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
            Page<ProductDTO> productDTOS = null;
            if(name_search.equals("")){
                productDTOS = productServices.getAll(pageable);
            }else{
                productDTOS = productServices.searchProductByName(name_search,pageable);
            }
            int totalPage = productDTOS.getTotalPages();
            model.addAttribute("totalpages",totalPage);
            model.addAttribute("currentpage",pageIndex);
            model.addAttribute("sizepage",pageSize);
            model.addAttribute("products",productDTOS);
            model.addAttribute("name_search",name_search);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "search-product";
    }
    @GetMapping("/updateproduct")
    public String updateProduct(Model model,@RequestParam("id") Long id,Pageable pageable,@RequestParam("page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size,@RequestParam("name_search") String name_search){
        ProductDTO productDTO = productServices.findById(id);
        Set<Long> id_colors = productDTO.getColors().stream().map(ColorDTO::getId).collect(Collectors.toSet());
        Set<Long> id_sizes = productDTO.getSizes().stream().map(SizeDTO::getId).collect(Collectors.toSet());
        Page<BrandDTO> brandDTOS = brandServices.getAll(pageable);
        Page<CategoryDTO> categoryDTOS = categoryServices.getAll(pageable);
        Page<ColorDTO> colorDTOS = colorServices.getAll(pageable);
        Page<GenderDTO> genderDTOS = genderServices.getAll(pageable);
        Page<SizeDTO> sizeDTOS = sizeServices.getAll(pageable);
        Page<StatusDTO> statusDTOS = statusServices.getAll(pageable);
        model.addAttribute("brands",brandDTOS);
        model.addAttribute("categorys",categoryDTOS);
        model.addAttribute("colors",colorDTOS);
        model.addAttribute("genders",genderDTOS);
        model.addAttribute("sizes",sizeDTOS);
        model.addAttribute("statuses",statusDTOS);
        model.addAttribute("productDto",productDTO);
        model.addAttribute("id_colors",id_colors);
        model.addAttribute("id_sizes",id_sizes);
        model.addAttribute("id_sizes_select",new HashSet<Long>());
        model.addAttribute("id_colors_select",new HashSet<Long>());
        model.addAttribute("name_search",name_search);
        return "update-product";
    }
    @PostMapping("/updateproduct")
    public String updateProduct(Model model
            ,@ModelAttribute ProductDTO productDTO
            ,@RequestParam(value = "id_sizes_select",required = false) List<Long> id_sizes
            ,@RequestParam(value = "id_sizes_origin",required = false) List<Long> id_sizes_origin
            ,@RequestParam(value = "id_colors_origin",required = false) Set<Long> id_colors_origin
            ,@RequestParam(value = "id_colors_select",required = false) Set<Long> id_colors
            ,@RequestParam(value = "id_imgs",required = false) Set<Long> id_imgs
            ,@RequestParam("name_search") String name_search
            ,@RequestParam("page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size
    ) {
        try {
            MultipartFile file = productDTO.getFile();
            Set<MultipartFile> fileSet = productDTO.getFiles();
            if (!file.isEmpty()) {
                String name_img = file.getOriginalFilename();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path_file+ File.separator+name_img));
                stream.write(file.getBytes());
                stream.close();
                productDTO.setImg(name_img);
            }else{
                productDTO.setImg(productDTO.getImg());
            }
            productDTO.setBrand(brandServices.findById(productDTO.getBrand().getId()));
            productDTO.setCategory(categoryServices.findById(productDTO.getCategory().getId()));
            productDTO.setGender(genderServices.findById(productDTO.getGender().getId()));
            productDTO.setStatus(statusServices.findById(productDTO.getStatus().getId()));
            Set<SizeDTO> sizeDTOS = new HashSet<>();
            Set<ColorDTO> colorDTOS = new HashSet<>();
            for(Long id_size:id_sizes){
                sizeDTOS.add(sizeServices.findById(id_size));
            }
            for (Long id_color:id_colors){
                colorDTOS.add(colorServices.findById(id_color));
            }
            productDTO.setSizes(sizeDTOS);
            productDTO.setColors(colorDTOS);
            productServices.save(productDTO);
            if (!(fileSet.size() == 1) || !fileSet.iterator().next().isEmpty()) {
                for (MultipartFile file1: fileSet){
                    String name_img1 = file1.getOriginalFilename();
                    BufferedOutputStream stream1 = new BufferedOutputStream(new FileOutputStream(path_file+ File.separator+name_img1));
                    stream1.write(file1.getBytes());
                    DetailsImgDTO detailsImgDTO = new DetailsImgDTO(name_img1,productDTO);
                    detailsImgServices.save(detailsImgDTO);
                    stream1.close();
                }
                for (Long id:id_imgs){
                    detailsImgServices.delete(id);
                }
            }
//            else{
//                productDTO.setImgs(productDTO.getImgs());
//            }
            productDTO.setFile(null);
            productDTO.setFiles(null);
            if (id_sizes_origin != null){
                for (Long id_size:id_sizes_origin){
                    if(id_sizes == null || !id_sizes.contains(id_size)){
                        SizeDTO sizeDTO = sizeServices.findById(id_size);
                        Set<ProductDTO> product1 = sizeDTO.getProductDTOSet();
                        product1.removeIf(product -> product.getId().equals(productDTO.getId()));
                        sizeDTO.setProductDTOSet(product1);
                        sizeServices.update(sizeDTO);
                    }
                }
            }
            if (id_sizes != null){
                for (Long id_size:id_sizes){
                    if(id_sizes_origin == null || !id_sizes_origin.contains(id_size)){
                        SizeDTO sizeDTO = sizeServices.findById(id_size);
                        Set<ProductDTO> product1 = sizeDTO.getProductDTOSet();
                        product1.add(productDTO);
                        sizeDTO.setProductDTOSet(product1);
                        sizeServices.update(sizeDTO);
                    }
                }
            }
            if(id_colors_origin != null){
                for (Long id_color:id_colors_origin){
                    if (id_colors == null || !id_colors.contains(id_color)){
                        ColorDTO colorDTO = colorServices.findById(id_color);
                        Set<ProductDTO> product1 = colorDTO.getProductDTOSet();
                        product1.removeIf(product -> product.getId().equals(productDTO.getId()));
                        colorDTO.setProductDTOSet(product1);
                        colorServices.update(colorDTO);
                    }
                }
            }
            if(id_colors != null){
                for (Long id_color:id_colors){
                    if (id_colors_origin == null || !id_colors_origin.contains(id_color)){
                        ColorDTO colorDTO = colorServices.findById(id_color);
                        Set<ProductDTO> product1 = colorDTO.getProductDTOSet();
                        product1.add(productDTO);
                        colorDTO.setProductDTOSet(product1);
                        colorServices.update(colorDTO);
                    }
                }
            }
            int pageIndex = page.orElse(1);
            int pageSize = size.orElse(5);
            Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
            Page<ProductDTO> productDTOS = null;
            if(name_search.equals("")){
                productDTOS = productServices.getAll(pageable);
            }else{
                productDTOS = productServices.searchProductByName(name_search,pageable);
            }
            int totalPage = productDTOS.getTotalPages();
            model.addAttribute("totalpages",totalPage);
            model.addAttribute("name_search",name_search);
            model.addAttribute("currentpage",pageIndex);
            model.addAttribute("sizepage",pageSize);
            model.addAttribute("products",productDTOS);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
//        return "redirect:manager";
     return "search-product";
    }
    @GetMapping("/searchproduct")
    public String searchProduct(Model model, @RequestParam("page")Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size,@RequestParam("name_search") String name_search){
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<ProductDTO> productDTOS = null;
        if(name_search.equals("")){
            productDTOS = productServices.getAll(pageable);
        }else{
            productDTOS = productServices.searchProductByName(name_search,pageable);
        }
        int totalPage = productDTOS.getTotalPages();
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        model.addAttribute("sizepage",pageSize);
        model.addAttribute("products",productDTOS);
        model.addAttribute("name_search",name_search);
        return "search-product";
    }
    @GetMapping("deleteproduct")
    public String deleteproduct(Model model, @RequestParam("page")Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size,@RequestParam("id") Long id,@RequestParam("name_search") String name_search){
        productServices.delete(id);
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<ProductDTO> productDTOS = null;
        if(name_search.equals("")){
            productDTOS = productServices.getAll(pageable);
        }else{
            productDTOS = productServices.searchProductByName(name_search,pageable);
        }
        int totalPage = productDTOS.getTotalPages();
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        model.addAttribute("sizepage",pageSize);
        model.addAttribute("products",productDTOS);
        model.addAttribute("name_search",name_search);
        return "search-product";
    }

}
