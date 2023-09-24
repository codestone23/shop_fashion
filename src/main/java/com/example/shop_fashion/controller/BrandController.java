package com.example.shop_fashion.controller;

import com.example.shop_fashion.dto.BrandDTO;
import com.example.shop_fashion.services.impl.BrandServices;
import org.aspectj.apache.bcel.classfile.InnerClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BrandController {
    @Autowired
    private BrandServices brandServices;
    @GetMapping("/get-brand")
    public String getBrand(Model model, @RequestParam("page")Optional<Integer> page
    , @RequestParam("size")Optional<Integer> size){
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<BrandDTO> brandDTOS = brandServices.getAll(pageable);
        int totalPage = brandDTOS.getTotalPages();
        model.addAttribute("brands",brandDTOS);
        model.addAttribute("sizepage",pageSize);
        model.addAttribute("currentpage",pageIndex);
        model.addAttribute("totalpages",totalPage);
        return "manager-brand";
    }
}
