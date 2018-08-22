package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.HashtagService;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class homeController {

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView("/addpost");
        Post post = new Post();
        modelAndView.addObject("post", post);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPost(@Valid Post post, BindingResult bindingResult, @RequestParam("hash") String hashtags)

    {
        ModelAndView modelAndView = new ModelAndView("redirect:/add");
        if (bindingResult.hasErrors()) {
            String check = hashtagService.check(hashtags);
            if (!check.equals("true")) {
                modelAndView.addObject("checkHashtag", check);
            }
            modelAndView.setViewName("addpost");
            return modelAndView;
        }
        String check = hashtagService.check(hashtags);
        if (!check.equals("true")) {
            modelAndView.addObject("checkHashtag", check);
            modelAndView.setViewName("addpost");
            return modelAndView;
        }
postService.savePost(post,hashtagService.toList(hashtags),hashtags);

        return modelAndView;

    }

    @RequestMapping(value = {"/", "/index"},method = RequestMethod.GET)
    public ModelAndView homePage()
    {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Post> posts = postService.getAllPost();
        modelAndView.addObject("posts",posts);
        return modelAndView;
    }

}
