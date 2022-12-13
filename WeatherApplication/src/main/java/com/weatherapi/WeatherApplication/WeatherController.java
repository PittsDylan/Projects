package com.weatherapi.WeatherApplication;
/*
    Pitts, Dylan
    Last modified: 2/09/2022
    Purpose: Controller
*/


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeatherController {
  
    @RequestMapping("/")
    public String onLoad(Model model) {
        return "index.html";
    }
}
