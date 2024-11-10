package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.ac.teami.seisakukadaiI.model.PostModel;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.repository.PostRepository;
import jp.ac.teami.seisakukadaiI.repository.UserRepository;

@Controller
public class AdminController {
	
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
	
	@GetMapping("/admin")
		public String  adminIndex(Model model) {
		return "main/admin/admin";
	}
    @GetMapping("/bousai")
        public String  bousaiIndex(Model model) {
        return "main/admin/bousai";
    }
    @GetMapping("/biciku")
        public String  bicikuIndex(Model model) {
        return "main/admin/biciku";
    }
    @GetMapping("/member")
        public String  memberIndex(Model model) {
        return "main/admin/member";
    }
    @GetMapping("/posts")
    public String toukouIndex(Model model) {
        // 現在ログインしているユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();
        String userId = userModel.getUserId();  // UserModelからuserIdを取得

        // ModelにuserIdをセットしてビューに渡す
        model.addAttribute("userId", userId);

        return "main/admin/posts";
    }

    
    @PostMapping("/posts")
    public String savePost(@RequestParam String title, @RequestParam String description, Model model) {
        // 現在ログインしているユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();
        String userId = userModel.getUserId();  // UserModelからuserIdを取得
        System.out.print("abc");        // UserRepositoryを使ってユーザー情報を取得
        UserModel user = userRepository.findByUserId(userId);  // user_idがuserIdの場合
        System.out.print("c");
        // 投稿データをPostModelにセット
        PostModel post = new PostModel();
        post.setTitle(title);
        post.setDescription(description);
        System.out.print("b");
        // ユーザー情報からUserModelを設定
        post.setUser(user);  // UserModelのインスタンスを設定
        System.out.print("a");
        // 投稿をデータベースに保存
        postRepository.save(post);
        System.out.print("abcbcbc");

        // 投稿一覧ページへリダイレクト
        return "redirect:/posts";  // 投稿一覧ページにリダイレクト
    }


    
}