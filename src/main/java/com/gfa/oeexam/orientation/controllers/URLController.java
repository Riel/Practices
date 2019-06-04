package com.gfa.oeexam.orientation.controllers;

import com.gfa.oeexam.orientation.models.UrlAlias;
import com.gfa.oeexam.orientation.services.URLService;
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
    public URLController(URLService service){
        this.service = service;
    }

    @GetMapping(value="/")
    public String renderRoot(Model model){
        model.addAttribute("hasMessage", service.displayMessage());
        model.addAttribute("message", service.getMessage());
        model.addAttribute("urlAlias", service.getItemToDisplayed());
        model.addAttribute("stored", service.isAlreadyStored());
        return "index";
    }

    @PostMapping(value="/save-link")
    public String renderSaveLink(@ModelAttribute UrlAlias urlAlias){
        service.shallDisplayMessage(true);
        boolean isStored = service.isAlreadyStored(urlAlias.getAlias());

        if (isStored){
            // Error scenario
            service.setItemToDisplayed(urlAlias);
            service.setMessageFor(null);

        } else {
            // Sucess scenario
            service.save(urlAlias);
            service.setItemToDisplayed(new UrlAlias());
            service.setMessageFor(urlAlias);
        }

        return "redirect:/";
    }

    //https://stackoverflow.com/questions/17955777/redirect-to-an-external-url-from-controller-action-in-spring-mvc
}