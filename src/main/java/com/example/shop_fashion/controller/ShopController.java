package com.example.shop_fashion.controller;

import com.example.shop_fashion.dto.*;
import com.example.shop_fashion.entities.*;
import com.example.shop_fashion.repository.SizeRepository;
import com.example.shop_fashion.services.impl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ShopController {
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
    @Autowired
    public ClientServices clientServices;
    @Autowired
    public CartServices cartServices;
    @Autowired
    public BillServices billServices;
    @GetMapping("/shop")
    public String shop(Model model, @RequestParam("page")Optional<Integer> page, @RequestParam("size") Optional<Integer> size){

        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(6);
        Pageable pageable1 = PageRequest.of(pageIndex-1,pageSize);
        Page<ProductDTO> productDTOS = productServices.getAll(pageable1);
        List<SizeDTO> sizeDTOS = sizeServices.findAll();
        List<ColorDTO> colorDTOS = colorServices.findAll();
        List<CategoryDTO> categoryDTOS = categoryServices.findAll();
        List<BrandDTO> brandDTOS = brandServices.findAll();
        List<GenderDTO> genderDTOS = genderServices.findAll();
        List<StatusDTO> statusDTOS = statusServices.findAll();
        int totalPage = productDTOS.getTotalPages();
        model.addAttribute("products",productDTOS);
        model.addAttribute("sizes",sizeDTOS);
        model.addAttribute("colors",colorDTOS);
        model.addAttribute("categorys",categoryDTOS);
        model.addAttribute("brands",brandDTOS);
        model.addAttribute("status",statusDTOS);
        model.addAttribute("genders",genderDTOS);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        model.addAttribute("sizepage",pageSize);
        return "shop";
    }
    @GetMapping("/shop_details")
    public String shopdetails(Model model,@RequestParam("id") Long id_product){
        ProductDTO productDTO = productServices.findById(id_product);
        model.addAttribute("productDto",productDTO);
        return "shop-details";
    }
    @GetMapping("/shopping_cart")
    public String shoppingcart(HttpSession session, Model model){
        List<CartDTO> cartDTOS = (List<CartDTO>) session.getAttribute("count");
        int total_price = 0;
        for (CartDTO cartDTO : cartDTOS){
            total_price += cartDTO.getProductDTO().getPrice() * cartDTO.getQuantity();
        }
        model.addAttribute("carts",cartDTOS);
        model.addAttribute("total_price",total_price);
        return "shopping-cart";
    }
    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model
            ,@RequestParam(value = "id_city",required = false) Long id_city
            ,@RequestParam(value = "id_district",required = false) Long id_district
            ,@RequestParam(value = "id_ward",required = false) Long id_ward
    ){
        try {
            List<CartDTO> cartDTOS = (List<CartDTO>) session.getAttribute("count");
            int total_price = 0;
            for (CartDTO cartDTO : cartDTOS){
                total_price += cartDTO.getProductDTO().getPrice() * cartDTO.getQuantity();
            }
            List<CityDTO> cityDTOS = cityServices.findAll();
            List<DistrictDTO> districtDTOS = new ArrayList<>();
            List<WardDTO> wardDTOS = new ArrayList<>();
            if(id_city != null && id_city !=0){
                districtDTOS = districtServices.findByIdCity(id_city);
            }
            if (id_district != null && id_district != 0){
                wardDTOS = wardServices.findByIdDistrict(id_district);
            }
            model.addAttribute("citys",cityDTOS);
            model.addAttribute("districts",districtDTOS);
            model.addAttribute("wards",wardDTOS);
            model.addAttribute("id_city",id_city);
            model.addAttribute("id_district",id_district);
            model.addAttribute("id_ward",id_ward);
            model.addAttribute("carts",cartDTOS);
            model.addAttribute("total_price",total_price);
            model.addAttribute("new_client",new Client());
        }catch (Exception e){
            e.getMessage();
        }
        return "checkout";
    }
    @PostMapping("/checkout")
    public String checkout(Model model, @ModelAttribute Client client
            ,@RequestParam("ward") Long id_ward,HttpSession session){
        Ward ward = wardServices.findById(id_ward);
        client.setWard(ward);
        clientServices.saveEntity(client);
        List<CartDTO> cartDTOS = (List<CartDTO>) session.getAttribute("count");
        session.removeAttribute("count");
        Set<Cart> carts = new HashSet<>();
        for (CartDTO cartDTO: cartDTOS){
            Cart cart = new Cart().toEntity(cartDTO);
            Cart cart1 = cartServices.saveOrReturn(cart);
            carts.add(cart1);
            ProductDTO productDTO = cartDTO.getProductDTO();
            productDTO.setQuantity(productDTO.getQuantity() - cartDTO.getQuantity());
            productServices.update(productDTO);
        }
        UserDetails userDetails = (UserDetails) session.getAttribute("customer");
        Bill bill = new Bill();
        bill.setCarts(carts);
        if (userDetails != null){
            bill.setUser(userServices.findByUsername(userDetails.getUsername()));
        }else{
            bill.setUser(null);
        }
        bill.setDate(new Date());
        bill.setClient(client);
        String code = carts.size()+client.getName();
        bill.setCode(code);
        Bill bill1 = billServices.saveOrReturn(bill);
        for (Cart cart:carts){
            cart.setBill(bill1);
            cartServices.saveOrReturn(cart);
        }
        return "redirect:index";
    }
}
