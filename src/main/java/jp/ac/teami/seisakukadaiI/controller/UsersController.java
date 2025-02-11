package jp.ac.teami.seisakukadaiI.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.micrometer.common.lang.NonNull;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.service.UserService;
@Controller
@RequestMapping("/users")
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserService userService;
    /**
     * ユーザー一覧表示（HTMLビュー）
     */
    @GetMapping
    public ModelAndView listUsers(
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "order", defaultValue = "desc") String order,
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "department", required = false) String department,
            Model model) {

        logger.info("全ユーザー情報を取得します - Sort by: {}, Order: {}, Query: {}, Email: {}, Department: {}",
                sortBy, order, query, email, department);

        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        List<UserModel> users = userService.searchUsers(query, email, department, sort);

        if (users.isEmpty()) {
            logger.warn("該当するデータが見つかりません - Query: {}, Email: {}, Department: {}", query, email, department);
            model.addAttribute("error", "該当するデータがありません");
        }

        model.addAttribute("users", users);
        return new ModelAndView("main/admin/user_list");
    }

    /**
     * 新規ユーザー追加フォーム表示
     */
    @GetMapping("/add")
    public ModelAndView add(UserModel usermodel, ModelAndView model) {
        model.addObject("user", usermodel); 
        model.setViewName("main/admin/user_add");
        return model;
    }

    // POST メソッド：登録処理
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute @NonNull UserModel usermodel, RedirectAttributes redirectAttributes) {
        try {
            // ロールを設定
            if (usermodel.getRole() != null) {
            	UserModel.UserRole userRole = UserModel.UserRole.valueOf(usermodel.getRole().name().toUpperCase());  // フォームから送信されたロール
                usermodel.setRole(userRole);  // ユーザーにロールを設定
            }

            this.userService.save(usermodel);
            redirectAttributes.addFlashAttribute("exception", "");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
        }
        return "redirect:/"; // ホームまたは適切な場所にリダイレクト
    }

    /**
     * ユーザー詳細表示
     */
    @GetMapping("/{id}/detail")
    public String userDetail(@PathVariable("id") Long id, Model model) {
        try {
            UserModel user = userService.getUserById(id);
            model.addAttribute("user", user);
        } catch (IllegalArgumentException e) {
            logger.error("ユーザーが見つかりません - ID: {}", id);
            model.addAttribute("error", "ユーザーが見つかりません");
            return "error";
        }
        return "main/admin/user_detail";
    }

    /**
     * ユーザー詳細編集画面表示
     */
    @GetMapping("/{id}/edit")
    public ModelAndView editUser(@PathVariable Long id, Model model) {
        try {
            UserModel user = userService.getUserById(id);
            model.addAttribute("user", user);
            return new ModelAndView("main/admin/user_edit");
        } catch (IllegalArgumentException e) {
            logger.error("指定されたユーザーは存在しません - ID: {}", id);
            model.addAttribute("error", "ユーザーが見つかりません");
            return new ModelAndView("main/admin/user_list");
        }
    }

    /**
     * ユーザー情報の更新
     */
    @PostMapping("/{id}/edit")
    public ModelAndView updateUser(@PathVariable Long id, @ModelAttribute UserModel user, Model model) {
        try {
            user.setId(id);
            userService.update(user);  // ユーザー情報を更新
            return new ModelAndView("redirect:/users");
        } catch (IllegalArgumentException e) {
            logger.error("ユーザー情報更新エラー: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("main/admin/user_edit");
        }
    }

    /**
     * ユーザー削除（JSON API）
     */
    @DeleteMapping("/api/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("ユーザーを削除します - ID: {}", id);
        userService.delete(id);  // ユーザーを削除
        return ResponseEntity.noContent().build();
    }
 // ユーザー検索エンドポイント
    @GetMapping("/search")
   // @GetMapping("/users")
    public String getUserList(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "department", required = false) String department,
            Model model) {

        // 検索条件が空でない場合のみ検索を実行
        List<UserModel> users = userService.search(username, email, department);

        model.addAttribute("users", users);
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("department", department);

     
        return "main/admin/user_list"; // Thymeleafのテンプレートに渡す
    }
}