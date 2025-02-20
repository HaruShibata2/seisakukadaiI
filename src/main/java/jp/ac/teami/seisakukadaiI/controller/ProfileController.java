package jp.ac.teami.seisakukadaiI.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.repository.UserRepository;
import jp.ac.teami.seisakukadaiI.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
     private UserRepository userrepository;

    // ログインユーザーのプロフィールを表示
    @GetMapping
    public String viewProfile(HttpServletRequest request, Model model) {
        // SecurityContextから現在ログインしているユーザーのusernameを取得
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // ユーザー情報を取得
        Optional<UserModel> user = userService.getByUsername(username);
        
        if (user.isPresent()) {  // Optionalの中身が存在する場合
            model.addAttribute("user", user.get());  // ユーザー情報をModelに追加
            model.addAttribute("requestURI", request.getRequestURI());  // リクエストURIをModelに追加
            return "main/profile";  // mainディレクトリ内のprofile.htmlを表示
        }
        
        return "error";  // ユーザーが見つからない場合のエラーハンドリング
    }
    
//    @GetMapping("/userprofile")
//    public String userProfile(@AuthenticationPrincipal UserModel user, Model model) {
//        // 部署がすでに設定されている場合は、ホームページにリダイレクト
//        if (user != null && (user.getDepartment() != null && !user.getDepartment().isEmpty())) {
//            return "redirect:/home";  // ホームページにリダイレクト
//        }
// 
//        // 部署が未設定の場合、部署登録ページを表示
//        model.addAttribute("user", user);
//        return "login/userprofile";  // 部署登録ページ（ユーザー情報編集ページ）
//    }
 
    
    @GetMapping("/profileupdate")
    public String profileupdate(Model model) {
        try {
            // ログインしているユーザーの情報を取得
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername(); // ユーザー名を取得
 
            // ユーザー情報をサービスから取得
            UserModel user = userService.getByUsername(username).orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません"));
            model.addAttribute("user", user);  // モデルにユーザー情報を追加
 
            // ユーザー情報を更新するページに遷移
            return "login/profileupdate";
        } catch (IllegalArgumentException e) {
            // ユーザーが見つからない場合、エラーメッセージを表示
            model.addAttribute("error", e.getMessage());
            return "redirect:/";  // エラーページに遷移
        }
    }
 
    @PostMapping("/profileupdate")
    public String profileupdate1(@ModelAttribute UserModel user, Model model) {
        try {
            // ログインしているユーザーの情報を取得
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername(); // ユーザー名を取得
 
            // ユーザー情報をサービスから取得
            UserModel existingUser = userService.getByUsername(username).orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません"));
 
            // ユーザー情報の更新
            // ユーザー名は変更しない（ログイン中のユーザー名はそのまま）
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getDepartment() != null && !user.getDepartment().isEmpty()) {
                existingUser.setDepartment(user.getDepartment());
            }
            if (user.getRole() != null) {
                existingUser.setRole(user.getRole());
            }
 
            // パスワードが入力された場合のみ更新
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                existingUser.setPassword(user.getPassword());
            }
 
            // ユーザー情報を保存
            userService.update(existingUser);
 
            // 更新後、プロフィールページにリダイレクト
            return "redirect:/"; // プロフィールページにリダイレクト
        } catch (IllegalArgumentException e) {
            // エラーが発生した場合
            model.addAttribute("error", e.getMessage());
            return "login/profileupdate";  // エラーメッセージを表示し、再度プロフィール編集ページに遷移
        }
    }
        
 
}
