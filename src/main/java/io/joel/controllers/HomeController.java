package io.joel.controllers;

import io.joel.models.Secret;
import io.joel.repositories.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    SecretRepository secretRepo;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Secret> allSecrets = secretRepo.findAll();
        model.addAttribute("secrets", allSecrets);
        model.addAttribute("newSecret", new Secret());
        return "index";
    }
}
