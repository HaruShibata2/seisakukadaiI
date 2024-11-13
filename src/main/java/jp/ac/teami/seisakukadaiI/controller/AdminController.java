package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.ac.teami.seisakukadaiI.repository.PostRepository;
import jp.ac.teami.seisakukadaiI.repository.UserRepository;

@Controller
public class AdminController {
	
//    @Autowired
//    private PostRepository postRepository;
//    
//    @Autowired
//    private UserRepository userRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

	
	@GetMapping("/admin")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
		public String  adminIndex(Model model) {
		return "main/admin/admin";
	}
    @GetMapping("/bousai")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @GetMapping("/error")
        public String  errorIndex(Model model) {
        return "error";
    }
//    @GetMapping("/posts")
//    public String toukouIndex(Model model) {
//        // 現在ログインしているユーザー情報を取得
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserModel userModel = (UserModel) authentication.getPrincipal();
//        String userId = userModel.getUserId();  // UserModelからuserIdを取得
//
//        // 投稿リストの取得（例としてPostRepositoryを使う）
//        List<PostModel> posts = postRepository.findAll();
//
//        // 投稿日時のフォーマットを行う
//        for (PostModel post : posts) {
//            post.setCreatedAt(post.getCreatedAt()); // setFormattedCreatedAtでフォーマットを設定
//        }
//
//        // Modelにpostsをセットしてビューに渡す
//        model.addAttribute("posts", posts);
//
//        return "main/admin/posts";  // ビュー名
//    }
}