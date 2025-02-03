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



    @GetMapping("/error")
        public String  errorIndex(Model model) {
        return "error";
    }
}