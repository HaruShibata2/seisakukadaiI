package jp.ac.teami.seisakukadaiI.controller;

import jp.ac.teami.seisakukadaiI.model.InquiryModel;
import jp.ac.teami.seisakukadaiI.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class InquiryController {

    @Autowired
    private InquiryRepository inquiryRepository;

    @GetMapping("/inquiry")
    public String showInquiryForm(Model model) {
        model.addAttribute("inquiryModel", new InquiryModel());
        return "setting/inquiry_form";
    }

    @PostMapping("/inquiry")
    public String submitInquiry(@ModelAttribute InquiryModel inquiryModel) {
        inquiryRepository.save(inquiryModel);
        return "redirect:/thank_you";
    }

    @GetMapping("/thank_you")
    public String showThankYouPage() {
        return "setting/thank_you";
    }
}
