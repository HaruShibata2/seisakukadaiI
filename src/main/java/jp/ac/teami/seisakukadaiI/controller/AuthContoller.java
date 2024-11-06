package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class AuthContoller {
	
    /**
     * ログイン画面へ遷移します.
     *
     * @return login.html
     */
    @RequestMapping(path = "/login")
 // 設定ファイルでログイン失敗時にはerror=tureを渡すようにしている。
//   ⇒コンソールに「ログインに失敗しました」と表示される。(ログイン成功時には何も表示されない。)
    public String showLogin(@RequestParam(required = false) String error) {
        System.err.println("login error:" + error);
        if (error != null) {
            System.err.println("ログインに失敗しました。");
        }
        return "login/login";
    }
    @GetMapping("/logout")
        public String logout() {
            // ログアウト処理を行う
            // ここでセッションを無効化したり、必要なクリア処理を行います
            return "logout"; // logout.html を表示させる
        }
}

