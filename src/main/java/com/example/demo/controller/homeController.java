package com.example.demo.controller;

import com.example.demo.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class homeController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage()
    {
        ModelAndView modelAndView =  new ModelAndView("/addpost");
        Post post = new Post();
        modelAndView.addObject("post",post);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPost(@Valid Post post, BindingResult bindingResult, @RequestParam("hash") String hashtags)

    {
        ModelAndView modelAndView =  new ModelAndView("redirect:/add");
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addpost");
        return modelAndView;}
            System.out.println(post.getName());
            System.out.println(post.getDescription());
            System.out.println(post.getUrl());
            System.out.println(hashtags);


            return modelAndView;

    }

}
