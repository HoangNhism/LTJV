package com.example.Buoi2.controller;

import com.example.Buoi2.model.Order;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.example.Buoi2.model.CartItem;
import com.example.Buoi2.service.CartService;
import com.example.Buoi2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @GetMapping("/checkout")
    public String checkout(Model model) {
        return "/cart/checkout";
    }
    @PostMapping("/submit")
    public String submitOrder(@Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return "/cart/submit";
        }
        List<CartItem> cartItems = cartService.getCartItems();
        if (cartItems.isEmpty()) {
            return "redirect:/cart"; // Redirect if cart is empty
        }
        orderService.createOrder(order, cartItems);
        return "redirect:/order/confirmation";
    }
    @GetMapping("/confirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("message", "Your order has been successfully placed.");
        return "cart/order-confirmation";
    }
}