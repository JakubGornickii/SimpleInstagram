package com.example.demo.controller;

import com.example.demo.model.Hashtag;
import com.example.demo.model.Post;
import com.example.demo.service.HashtagService;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
postService.savePost(post,hashtagService.toList(hashtags));

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

    @RequestMapping(value = "/tag/{id}",method = RequestMethod.GET)
    public ModelAndView viewByHashtah(@PathVariable("id") String hashtag)
    {
        ModelAndView modelAndView = new ModelAndView("index");
    List<Post> posts = postService.findByTag(hashtag);
        modelAndView.addObject("posts",posts);
        return modelAndView;
    }
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ModelAndView search(@RequestParam("tag") String tag)
    {
        System.out.println(tag);
        ModelAndView modelAndView = new ModelAndView("index");
        List<Post> posts = postService.findByTag(tag);
        if (posts == null)
        {

            modelAndView.addObject( "errorMessage","Błąd nic nie znaleziono");
            return modelAndView;
        }
        modelAndView.addObject("posts",posts);
        return modelAndView;
    }

    @RequestMapping(value = "/display/{postId}",method = RequestMethod.GET)
    public ModelAndView display(@PathVariable("postId") Integer postId)
    {
        ModelAndView modelAndView = new ModelAndView("/display");
        Post post = postService.getOne(postId);
        modelAndView.addObject("post",post);
        return modelAndView;

    }
}

