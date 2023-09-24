package com.example.shop_fashion.controller;

import com.example.shop_fashion.dto.ProductDTO;
import com.example.shop_fashion.repository.SizeRepository;
import com.example.shop_fashion.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
//@RequestMapping("/admin")
public class Manager {
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
    @GetMapping("/manager")
    public String manager(Model model, @RequestParam("page")Optional<Integer> page
    ,@RequestParam("size") Optional<Integer> size){
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<ProductDTO> productDTOS = productServices.getAll(pageable);
        int totalPage = productDTOS.getTotalPages();
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        model.addAttribute("sizepage",pageSize);
        model.addAttribute("products",productDTOS);

        return "manager";
    }

}
