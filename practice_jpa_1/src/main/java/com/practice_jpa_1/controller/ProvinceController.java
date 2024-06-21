package com.practice_jpa_1.controller;

import com.practice_jpa_1.model.Province;
import com.practice_jpa_1.service.ICustomerService;
import com.practice_jpa_1.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ModelAndView getAllProvince() {
        ModelAndView modelAndView = new ModelAndView("/province/list");
        Iterable<Province> provinces = provinceService.findAll();
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getCreateProvinceForm() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createProvince(@ModelAttribute("province") Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "Create new province successfully");
        return "redirect:/provinces";
    }
}
