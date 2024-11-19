package jp.ac.teami.seisakukadaiI.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.ac.teami.seisakukadaiI.model.PostModel;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.repository.PostRepository;
import jp.ac.teami.seisakukadaiI.service.PostService;

@Controller
//@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    // 投稿一覧を表示するGETメソッド（降順で表示）
    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();
        String userId = userModel.getUserId();

        List<PostModel> posts = postRepository.findAllByOrderByCreatedAtDesc();
        model.addAttribute("userId", userId);
        model.addAttribute("posts", posts);

        return "main/admin/posts";
    }

    // 投稿を作成するPOSTメソッド
    @PostMapping("/posts")
    public String createPost(@ModelAttribute PostModel postModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();

        postModel.setUser(userModel);
        postModel.setCreatedAt(LocalDateTime.now());
        postRepository.save(postModel);

        return "redirect:/posts";
    }

 // Method to handle the display of the edit form for a specific post
    @GetMapping("/editpost/{postId}")
    public String getEditPost(@PathVariable Integer postId, Model model) {
        Optional<PostModel> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
            return "editpost";  // Return your HTML file name without .html extension
        } else {
            return "redirect:/posts";  // Redirect if post is not found
        }
    }

    @PostMapping("/editpost")
    public String updatePost(@RequestParam Integer postId, @RequestParam String title, @RequestParam String description) {
        Optional<PostModel> postOptional = postRepository.findById(postId);
        
        if (postOptional.isPresent()) {
            PostModel post = postOptional.get();
            post.setTitle(title);
            post.setDescription(description);
            postRepository.save(post); // データベースを更新
        }
        return "redirect:/posts"; // 更新後、投稿一覧ページにリダイレクト
    }

    // Method to handle the deletion of a post
    @PostMapping("/posts/delete")
    public String deletePost(@RequestParam Integer postId) {
        postRepository.deleteById(postId);
        return "redirect:/posts";
    }
}