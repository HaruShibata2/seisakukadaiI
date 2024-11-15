package jp.ac.teami.seisakukadaiI.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.ac.teami.seisakukadaiI.model.PostModel;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.repository.PostRepository;

@Controller
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    // 投稿一覧を表示するGETメソッド（降順で表示）
    @GetMapping
    public String getAllPosts(Model model) {
        // 現在ログインしているユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();
        String userId = userModel.getUserId();  // UserModelからuserIdを取得

        // 投稿リストを降順で取得（createdAtフィールドでソート）
        List<PostModel> posts = postRepository.findAllByOrderByCreatedAtDesc();

        // ModelにuserIdとpostsをセットしてビューに渡す
        model.addAttribute("userId", userId); // userIdを追加
        model.addAttribute("posts", posts);

        return "main/admin/posts";  // ビュー名
    }

    // 投稿を作成するPOSTメソッド
    @PostMapping
    public String createPost(@ModelAttribute PostModel postModel, Model model) {
        // 現在ログインしているユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();

        // ユーザーIDを投稿に設定
        postModel.setUser(userModel);  // ユーザーと関連付け

        // 投稿日時をセット（投稿時に自動的に設定）
        postModel.setCreatedAt(LocalDateTime.now());

        // 投稿を保存
        postRepository.save(postModel);

        // 新しい投稿を含むリストを取得（降順で表示）
        List<PostModel> posts = postRepository.findAllByOrderByCreatedAtDesc();

        // Modelにpostsをセットしてビューに渡す
        model.addAttribute("posts", posts);

        // 投稿後に投稿一覧を表示するページにリダイレクト
        return "main/admin/posts";  // 投稿後のビュー名
    }
}
