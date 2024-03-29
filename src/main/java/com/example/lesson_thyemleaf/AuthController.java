package com.example.lesson_thyemleaf;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

/*    @GetMapping("/go-to-logOutPage")
    public String logOut(Model model) {
        model.addAttribute("isError", true);
        return "loginPage";
    }*/
    @GetMapping("/go-to-logOutPage")
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/go-to-loginPage";
    }

}
