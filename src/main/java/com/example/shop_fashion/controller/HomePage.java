package com.example.shop_fashion.controller;

import com.example.shop_fashion.dto.CartDTO;
import com.example.shop_fashion.dto.ProductDTO;
import com.example.shop_fashion.entities.User;
import com.example.shop_fashion.repository.SizeRepository;
import com.example.shop_fashion.services.impl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePage {
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
    public UserServices userServices;
    @GetMapping("/index")
    public String index(HttpSession session, Model model){
        List<CartDTO> cartDTOS = (List<CartDTO>) session.getAttribute("count");
        if (cartDTOS == null) {
            cartDTOS = new ArrayList<>();
            session.setAttribute("count", cartDTOS);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = null;
        List<String> roles = new ArrayList<>();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities){
                roles.add(authority.getAuthority());
            }
        }
        List<ProductDTO> productBS = productServices.findBS();
        List<ProductDTO> productHS = productServices.findHS();
        List<ProductDTO> productNA = productServices.findNA();
        model.addAttribute("productBS",productBS);
        model.addAttribute("productHS",productHS);
        model.addAttribute("productNA",productNA);
        session.setAttribute("customer",userDetails);
        session.setAttribute("roles",roles);
        return "index";
    }
    @GetMapping("/addtocart")
    public String addtocart(HttpSession session, Model model, @RequestParam("id_product") Long id
    ,@RequestParam("size") String size
    ,@RequestParam("color") String color
    ,@RequestParam("quantity") int quantity){
        List<CartDTO> cartDTOS = (List<CartDTO>) session.getAttribute("count");
        Long max_card_id = 0L;
        for (CartDTO cartDTO: cartDTOS){
            max_card_id = Math.max(cartDTO.getId(),max_card_id);
        }
        ProductDTO productDTO = productServices.findById(id);
        CartDTO cartDTO = new CartDTO(max_card_id+1,productDTO,quantity,color,size);
        boolean check = true;
        for (CartDTO cartDTO1: cartDTOS){
            if(cartDTO1.getProductDTO().getName().equals(cartDTO.getProductDTO().getName())
                    && cartDTO1.getCode_color().equals(cartDTO.getCode_color())
                    && cartDTO1.getSize().equals(cartDTO.getSize()))
            {
                cartDTO1.setQuantity(cartDTO1.getQuantity() + cartDTO.getQuantity());
                check = false;
                break;
            }
        }
        if (check){
            cartDTOS.add(cartDTO);
        }
        session.setAttribute("count",cartDTOS);
        model.addAttribute("productDto",productDTO);
        return "shop-details";
    }
    @GetMapping("/getsession")
    public String getsession(HttpSession session,Model model){
        List<CartDTO> cartDTOS = (List<CartDTO>) session.getAttribute("count");
        int total_price = 0;
        for (CartDTO cartDTO : cartDTOS){
            total_price += cartDTO.getProductDTO().getPrice() * cartDTO.getQuantity();
        }
        model.addAttribute("carts",cartDTOS);
        model.addAttribute("total_price",total_price);
        return "shopping-cart";
    }
    @GetMapping("changequantitycart")
    public String changeQuantityCart(HttpSession session,Model model,@RequestParam("id_cart") Long id_cart,@RequestParam("quantity") int quantity){
        List<CartDTO> cartDTOS = (List<CartDTO>) session.getAttribute("count");
        for (CartDTO cartDTO: cartDTOS){
            if (cartDTO.getId() == id_cart){
                cartDTO.setQuantity(quantity);
                break;
            }
        }
        int total_price = 0;
        for (CartDTO cartDTO : cartDTOS){
            total_price += cartDTO.getProductDTO().getPrice() * cartDTO.getQuantity();
        }
        session.setAttribute("count",cartDTOS);
        model.addAttribute("carts",cartDTOS);
        model.addAttribute("total_price",total_price);
        return "shopping-cart";
    }
    @GetMapping("deletecart")
    public String deleteCart(HttpSession session,Model model,@RequestParam("id_cart") Long id_cart){
        List<CartDTO> cartDTOS = (List<CartDTO>) session.getAttribute("count");
        for (CartDTO cartDTO:cartDTOS){
            if (cartDTO.getId() == id_cart){
                cartDTOS.remove(cartDTO);
                break;
            }
        }
        int total_price = 0;
        for (CartDTO cartDTO : cartDTOS){
            total_price += cartDTO.getProductDTO().getPrice() * cartDTO.getQuantity();
        }

        session.setAttribute("count",cartDTOS);
        model.addAttribute("carts",cartDTOS);
        model.addAttribute("total_price",total_price);
        return "shopping-cart";
    }
    @GetMapping("remove_cart")
    public String removeCart(HttpSession session,Model model){
        List<CartDTO> cartDTOS = (List<CartDTO>) session.getAttribute("count");
        cartDTOS.clear();
        int total_price = 0;
        session.setAttribute("count",cartDTOS);
        model.addAttribute("carts",cartDTOS);
        model.addAttribute("total_price",total_price);
        return "shopping-cart";
    }
    @GetMapping("/user-details")
    public String userDetails(Model model,HttpSession session){
        UserDetails userDetails = (UserDetails) session.getAttribute("customer");
        User user = userServices.findByUsername(userDetails.getUsername());
        model.addAttribute("user",user);
        return "user-details";
    }

}
