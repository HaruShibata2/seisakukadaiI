package jp.ac.teami.seisakukadaiI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.ac.teami.seisakukadaiI.model.StockpileModel;
import jp.ac.teami.seisakukadaiI.repository.PostRepository;
import jp.ac.teami.seisakukadaiI.repository.UserRepository;
import jp.ac.teami.seisakukadaiI.service.StockpileService;



@Controller
public class AdminController {
	
    private final StockpileService service;

    public AdminController(StockpileService service) {
        this.service = service;
    }
    
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
    public String showAdminStockpilePage(Model model) {
        // 管理者専用の備蓄リスト
        List<StockpileModel> stockpiles = service.getAllStockpiles();
        model.addAttribute("stockpiles", stockpiles);
        return "main/admin/biciku"; // 管理者向けの備蓄管理ページ
    }



    @GetMapping("/error")
        public String  errorIndex(Model model) {
        return "error";
    }
}