package jp.ac.teami.seisakukadaiI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.ac.teami.seisakukadaiI.model.PostModel;
import jp.ac.teami.seisakukadaiI.repository.PostRepository;


@Controller
public class MainController {
	
    @Autowired
    private PostRepository postRepository;
	
    @GetMapping("/")
    public String getPosts(Model model) {
        List<PostModel> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "main/home";
    }
	
}

