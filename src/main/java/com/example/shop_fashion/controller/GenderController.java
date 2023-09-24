package com.example.shop_fashion.controller;

import com.example.shop_fashion.dto.GenderDTO;
import com.example.shop_fashion.dto.SizeDTO;
import com.example.shop_fashion.services.impl.GenderServices;
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
public class GenderController {
    @Autowired
    private GenderServices genderServices;
    @GetMapping("get-gender")
    public String getSize(Model model, @RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size) {
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<GenderDTO> genderDTOS = genderServices.getAll(pageable);
        int totalPage = genderDTOS.getTotalPages();
        model.addAttribute("genders",genderDTOS);
        model.addAttribute("currentpage", pageIndex);
        model.addAttribute("sizepage", pageSize);
        model.addAttribute("totalpages", totalPage);
        return "manager-gender";
    }
}
