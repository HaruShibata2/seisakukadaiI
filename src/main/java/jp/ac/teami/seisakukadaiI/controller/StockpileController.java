package jp.ac.teami.seisakukadaiI.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ac.teami.seisakukadaiI.model.StockpileModel;
import jp.ac.teami.seisakukadaiI.service.StockpileService;

@Controller
@RequestMapping("/stockpile")
public class StockpileController {
    private final StockpileService service;

    public StockpileController(StockpileService service) {
        this.service = service;
    }

    /**
     * 一般ユーザー・リーダーが閲覧できる備蓄一覧ページ
     */
    @GetMapping
    public String showStockpileList(Model model) {
        // 一般ユーザー・リーダー用の備蓄リスト
        List<StockpileModel> stockpiles = service.getAllStockpiles();
        model.addAttribute("stockpiles", stockpiles);
        return "main/index"; // 一般ユーザー・リーダー閲覧用のビュー
    }

    /**
     * 備蓄データの追加（管理者のみ）
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/biciku/add")
    public String addStockpile(@ModelAttribute StockpileModel stockpile, RedirectAttributes redirectAttributes) {
        service.saveStockpile(stockpile);
        // フラッシュメッセージをリダイレクト先に渡す
        redirectAttributes.addFlashAttribute("message", "新しい備蓄アイテムが追加されました。");
        return "redirect:/biciku"; // リダイレクト先を http://localhost:8080/biciku に変更
    }

    /**
     * 備蓄データの削除（管理者のみ）
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/biciku/delete/{id}")
    public String deleteStockpile(@PathVariable Long id) {
        service.deleteStockpile(id);
        return "redirect:/biciku"; // 削除後も http://localhost:8080/biciku にリダイレクト
    }
}
