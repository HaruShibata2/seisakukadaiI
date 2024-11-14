package jp.ac.teami.seisakukadaiI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.ac.teami.seisakukadaiI.model.PostModel;
import jp.ac.teami.seisakukadaiI.model.SafetyCheck;
import jp.ac.teami.seisakukadaiI.repository.PostRepository;
import jp.ac.teami.seisakukadaiI.repository.SafetyCheckRepository;


@Controller
public class MainController {
	
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private SafetyCheckRepository safetycheckRepository;

	
    @GetMapping("/")
    public String getPosts(Model model) {
        List<PostModel> posts = postRepository.findAllByOrderByCreatedAtDesc();
        model.addAttribute("posts", posts);
//        List<SafetyCheck> check = safetycheckRepository.findAll();
//        model.addAttribute("check", check);
        return "main/home";
    }
    @GetMapping("/status")
    public String getCheck(Model model) {
//        List<PostModel> posts = postRepository.findAllByOrderByCreatedAtDesc();
//        model.addAttribute("posts", posts);
        List<SafetyCheck> check = safetycheckRepository.findAll();
        model.addAttribute("check", check);
        return "status";
    }
	
}

