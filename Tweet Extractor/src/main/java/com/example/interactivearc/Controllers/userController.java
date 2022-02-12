package com.example.interactivearc.Controllers;

import com.example.interactivearc.Entities.User;
import com.example.interactivearc.Entities.tweet;
import com.example.interactivearc.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class userController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/signup")
    public String questForm(Model model) {
        model.addAttribute("userInstance", new User());
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUp(@ModelAttribute User userInstance, Model model){
        model.addAttribute("userInstance", userInstance);
        userRepo.save(userInstance);
        return "success";
    }

    @GetMapping(path="/allusers")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }
}
