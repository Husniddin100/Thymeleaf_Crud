package com.example.lesson_thyemleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/go-to-loginPage")
    public String goToLoginPage(Model model) {
        model.addAttribute("isError", false);
        return "loginPage";
    }
    @GetMapping("/go-to-failed-loginPage")
    public String goToFailedLoginPage(Model model) {
        model.addAttribute("isError", true);
        return "loginPage";
    }


}
