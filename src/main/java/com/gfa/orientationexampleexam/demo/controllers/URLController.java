package com.gfa.orientationexampleexam.demo.controllers;

import com.gfa.orientationexampleexam.demo.models.URLItem;
import com.gfa.orientationexampleexam.demo.services.States;
import com.gfa.orientationexampleexam.demo.services.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class URLController {

    private URLService service;

    @Autowired
    public URLController (URLService service){
        this.service = service;
    }

    @GetMapping(value = "/")
    public String renderMain(Model model){
        model.addAttribute("hasMessage", service.hasMessage());
        model.addAttribute("message", service.getMessage());
        model.addAttribute("urlItem", service.getItem());
        return "index";
    }

    @PostMapping(value = "/save-link")
    public String renderMain(Model model, @ModelAttribute URLItem item){
        boolean alreadyStored = service.isAlreadyStored(item);

        if (alreadyStored){
            service.setMessage(States.USED, item);
            service.setItem(item);
        } else {
            service.storeItem(item);
            service.setMessage(States.FREE, item);
            model.addAttribute("hasMessage", true);
            model.addAttribute("message", service.getMessage());
            model.addAttribute("urlItem", new URLItem());
        }

        return "redirect:/";
    }
}
