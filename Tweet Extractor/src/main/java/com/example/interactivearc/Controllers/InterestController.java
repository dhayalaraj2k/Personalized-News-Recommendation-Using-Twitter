package com.example.interactivearc.Controllers;

import com.example.interactivearc.Entities.Interest;
import com.example.interactivearc.Entities.User;
import com.example.interactivearc.Entities.tweet;
import com.example.interactivearc.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.interactivearc.Repositories.InterestRepository;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class InterestController {

    @Autowired
    InterestRepository interestRepo;

    @Autowired
    UserRepository userRepo;

    @GetMapping("/addinterest")
    public String questForm(Model model) {
        model.addAttribute("interestInstance", new Interest());
        return "addinterest";
    }

    @PostMapping("/addinterest")
    public String questSubmit(@ModelAttribute("interestInstance") Interest interestInstance, Model model,
                              final BindingResult tweetBindingResult,
                              final RedirectAttributes redirectAttributes) {
        model.addAttribute("interestInstance", interestInstance);
        redirectAttributes.addFlashAttribute("interestInstance", interestInstance);
        Iterable<User> users=userRepo.findAll();
        long max=0;
        for(User user: users){
            if(user.getId()>max)
                max=user.getId();
        }
        interestInstance.setId(max);
//        question.setId(10);
        interestRepo.save(interestInstance);
//        System.out.println(interestInstance.isCovid19());
        return "redirect:tweetResult";
    }

    @GetMapping(path="/allinterests")
    public @ResponseBody
    Iterable<Interest> getAllUsers() {
        return interestRepo.findAll();
    }

}