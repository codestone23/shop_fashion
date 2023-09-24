package com.example.shop_fashion.controller;

import com.example.shop_fashion.dto.CityDTO;
import com.example.shop_fashion.dto.DistrictDTO;
import com.example.shop_fashion.dto.WardDTO;
import com.example.shop_fashion.repository.SizeRepository;
import com.example.shop_fashion.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserControllerApi {
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
    @Autowired
    public CityServices cityServices;
    @Autowired
    public DistrictServices districtServices;
    @Autowired
    public WardServices wardServices;
    @Autowired
    public UserServices userServices;
    @GetMapping("/get-place")
    public ResponseEntity<String> getPlace(
            @RequestParam(value = "cityId",required = false) Long cityId,
            @RequestParam(value = "districtId",required = false) Long districtId,
            @RequestParam(value = "wardId",required = false) Long wardId
    ){
        List<CityDTO> cityDTOS = cityServices.findAll();
        List<DistrictDTO> districtDTOS = new ArrayList<>();
        List<WardDTO> wardDTOS = new ArrayList<>();
        if(cityId != null && cityId !=0){
            districtDTOS = districtServices.findByIdCity(cityId);
        }
        if (districtId != null && districtId != 0){
            wardDTOS = wardServices.findByIdDistrict(districtId);
        }
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<div class=\"checkout__select col-lg-6\">");
        htmlBuilder.append("<select name=\"district\" id=\"district\" class=\"form-control select-parent\" style=\"display:none;\" onchange=\"getPlace1()\" required>");
        htmlBuilder.append("<option value=\"0\">Quận/ Huyện</option>");
        for (DistrictDTO districtDTO: districtDTOS){
            htmlBuilder.append("<option value=\"").append(districtDTO.getId()).append("\" ");
                    if (districtDTO.getId() == districtId){
                        htmlBuilder.append(" selected ");
                    }
                    htmlBuilder.append(">").append(districtDTO.getName()).append("</option>");
        }
        htmlBuilder.append("</select>");
        htmlBuilder.append("<div class=\"nice-select form-control select-parent\" tabindex=\"").append(districtId).append("\">" +
                "<span onchange=\"getPlace1()\" class=\"current\">");
        if (districtId == 0){
            htmlBuilder.append("Quận/ Huyện");
        }else{
            for (DistrictDTO districtDTO: districtDTOS){
                if (districtDTO.getId() == districtId){
                    htmlBuilder.append(districtDTO.getName());
                    break;
                }
            }
        }
        htmlBuilder.append("</span>" +
                "<ul onchange=\"getPlace1()\" class=\"list\">" +
                "<li data-value=\"").append(districtId).append("\" class=\"option focus\">Quận/ Huyện</li>");
        for (DistrictDTO districtDTO: districtDTOS){
            htmlBuilder.append("<li data-value=\"").append(districtDTO.getId()).append("\" class=\"option\"");
            if (districtDTO.getId() == districtId){
                htmlBuilder.append(" selected ");
            }
            htmlBuilder.append(">").append(districtDTO.getName()).append("</li>");
        }
        htmlBuilder.append("</ul></div>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("<div class=\"checkout__select col-lg-6\">");
        htmlBuilder.append("<select name=\"ward\" id=\"ward\" class=\"form-control select-parent\" style=\"display:none;\" required>");
        htmlBuilder.append("<option value=\"0\">Phường/ Xã</option>");
        for (WardDTO wardDTO:wardDTOS){
            htmlBuilder.append("<option value=\"").append(wardDTO.getId()).append("\" ");
            if (wardDTO.getId() == wardId){
                htmlBuilder.append(" selected ");
            }
            htmlBuilder.append(">").append(wardDTO.getName()).append("</option>");
        }
        htmlBuilder.append("</select>");
        htmlBuilder.append("<div class=\"nice-select form-control select-parent\" tabindex=\"").append(wardId).append("\">" +
                "<span class=\"current\">");
        if (wardId == 0){
            htmlBuilder.append("Phường/ Xã");
        }else{
            for (WardDTO wardDTO:wardDTOS){
                if (wardDTO.getId() == wardId){
                    htmlBuilder.append(wardDTO.getName());
                    break;
                }
            }
        }
        htmlBuilder.append("</span>" +
                "<ul class=\"list\">" +
                "<li data-value=\"").append(wardId).append("\" class=\"option focus\">Phường/ Xã</li>");
        for (WardDTO wardDTO:wardDTOS){
            htmlBuilder.append("<li data-value=\"").append(wardDTO.getId()).append("\" class=\"option\"");
            if (wardDTO.getId() == wardId){
                htmlBuilder.append(" selected ");
            }
            htmlBuilder.append(">").append(wardDTO.getName()).append("</li>");
        }
        htmlBuilder.append("</ul></div>");
        htmlBuilder.append("</div>");
        return new ResponseEntity<>(htmlBuilder.toString(), HttpStatus.OK);
    }
}
