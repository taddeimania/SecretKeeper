package io.joel.controllers;

import io.joel.models.Secret;
import io.joel.models.User;
import io.joel.repositories.SecretRepository;
import io.joel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    SecretRepository secretRepo;

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        Iterable<Secret> allSecrets = secretRepo.findAllByUser(user);

        model.addAttribute("secrets", allSecrets);
        model.addAttribute("newSecret", new Secret());
        return "index";
    }
}
