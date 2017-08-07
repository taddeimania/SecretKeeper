package io.joel.controllers;

import io.joel.models.Secret;
import io.joel.repositories.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecretController {

    @Autowired
    SecretRepository secretRepo;

    @RequestMapping(value = "/createSecret", method = RequestMethod.POST)
    public String createSecretForm(@RequestParam("body") String body) {
        Secret newSecret = new Secret();
        newSecret.setBody(body);
        secretRepo.save(newSecret);
        return "redirect:/";
    }
}
