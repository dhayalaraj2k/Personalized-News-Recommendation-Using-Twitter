package com.example.interactivearc.Controllers;

import com.example.interactivearc.Entities.Interest;
import com.example.interactivearc.Entities.User;
import com.example.interactivearc.Entities.news_topics;
import com.example.interactivearc.Entities.tweet_topics;
import com.example.interactivearc.Repositories.newsTopicRepository;
import com.example.interactivearc.Repositories.tweetTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TopicController {
    @Autowired
    tweetTopicRepository tweetTopicRepo;

    @Autowired
    newsTopicRepository newsTopicRepo;

    @RequestMapping(value = "/tweetResult", method = RequestMethod.GET)
    public String tweetResult(@ModelAttribute("interestInstance") Interest interestInstance,
                              final BindingResult tweetBindingResult, Model model){
        model = display(interestInstance, model);
        return "finalResult";
    }

//    @PostMapping("/addinterest")
//    public String questSubmit(@ModelAttribute("interestInstance") Interest interestInstance, Model model,
//                              final BindingResult tweetBindingResult,
//                              final RedirectAttributes redirectAttributes) {
//        model.addAttribute("interestInstance", interestInstance);
//        redirectAttributes.addFlashAttribute("interestInstance", interestInstance);
//        return "redirect:tweetResult";
//    }

    private Model display(Interest interestInstance, Model model){
        if(interestInstance.isFinance()){
            List<tweet_topics> finance = tweetTopicRepo.findTop2ByTopic("Finance");
            model.addAttribute("finance", finance);
        }
        if(interestInstance.isVaccine()){
            List<tweet_topics> vaccine = tweetTopicRepo.findTop2ByTopic("Vaccine");
            model.addAttribute("vaccine", vaccine);
        }
        if(interestInstance.isIndia()){
            List<tweet_topics> india = tweetTopicRepo.findTop2ByTopic("India");
            model.addAttribute("india", india);
        }
        if(interestInstance.isPolitics()){
            List<tweet_topics> politics_tweets = tweetTopicRepo.findTop2ByTopic("Politics");
            List<news_topics> politics_news = newsTopicRepo.findTop2ByTopic("Politics");
            model.addAttribute("politics_tweets", politics_tweets);
            model.addAttribute("politics_news", politics_news);
        }
        if(interestInstance.isCovid19()){
            List<tweet_topics> covid19_tweets = tweetTopicRepo.findTop2ByTopic("Covid 19");
            List<news_topics> covid19_news = newsTopicRepo.findTop2ByTopic("Covid 19");
            model.addAttribute("covid19_tweets", covid19_tweets);
            model.addAttribute("covid19_news", covid19_news);
        }
        if(interestInstance.isElection()){
            List<tweet_topics> election = tweetTopicRepo.findTop2ByTopic("Election");
            model.addAttribute("election", election);
        }
        if(interestInstance.isEntertainment()){
            List<news_topics> entertainment = newsTopicRepo.findTop2ByTopic("Entertainment");
            model.addAttribute("entertainment", entertainment);
        }
        if(interestInstance.isTechnology()){
            List<news_topics> technology = newsTopicRepo.findTop2ByTopic("Technology");
            model.addAttribute("technology", technology);
        }
        if(interestInstance.isBusiness()){
            List<news_topics> business = newsTopicRepo.findTop2ByTopic("Buisness");
            model.addAttribute("business", business);
        }
        if(interestInstance.isSports()){
            List<news_topics> sports = newsTopicRepo.findTop2ByTopic("Sports");
            model.addAttribute("sports", sports);
        }
        return model;
    }
}
