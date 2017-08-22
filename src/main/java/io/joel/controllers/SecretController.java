package io.joel.controllers;

import io.joel.models.Secret;
import io.joel.models.User;
import io.joel.repositories.SecretRepository;
import io.joel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class SecretController {

    @Autowired
    SecretRepository secretRepo;

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/secret/createSecret")
    public String createSecretForm(Model model) {
        model.addAttribute("newSecret", new Secret());
        return "createSecret";
    }

    @RequestMapping(value = "/secret/createSecret", method = RequestMethod.POST)
    public String createSecretForm(@RequestParam("body") String body,
                                   Principal principal) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        Secret newSecret = new Secret();
        newSecret.setBody(body);
        newSecret.setUser(user);
        secretRepo.save(newSecret);
        return "redirect:/";
    }

    @RequestMapping(value = "/secret/mySecrets")
    public String mySecrets(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName());
        Iterable<Secret> secrets = secretRepo.findAllByUser(user);
        model.addAttribute("secrets", secrets);
        model.addAttribute("user", user);
        return "mySecrets";
    }
}
