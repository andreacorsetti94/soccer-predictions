package com.acorsetti.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimpleController {

    @RequestMapping(path= "/sample",method= RequestMethod.GET)
    public String greet(ModelMap model){
        String greet =" Hello !!! How are You?";
        model.addAttribute("greet", greet);

        return "sample";
    }
}
