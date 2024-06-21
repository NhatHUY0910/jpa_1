package com.practice_jpa_1.controller;

import com.practice_jpa_1.model.Customer;
import com.practice_jpa_1.model.Province;
import com.practice_jpa_1.service.ICustomerService;
import com.practice_jpa_1.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return provinceService.findAll();
    }

    @GetMapping
    public ModelAndView listCustomer(){
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        Iterable<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createCustomerForm(){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return new ModelAndView("/customer/create");
        }

        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        redirectAttributes.addFlashAttribute("message", "Create new customer successfully");
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateCustomerForm(@PathVariable("id") Long id){
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/customer/update");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/customer/update");
        }
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        modelAndView.addObject("message", "Update customer successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        customerService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Delete customer successfully");
        return "redirect:/customers";
    }
}
