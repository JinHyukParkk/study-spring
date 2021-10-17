package com.example.foodlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class PageController {


    @GetMapping("/main")
    public ModelAndView main() {
        // templates 아래의 상대 경로 넣어줌
        return new ModelAndView("main");
    }
}
