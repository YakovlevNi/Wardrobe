package wardrobe.controllers;

import jakarta.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wardrobe.model.Item;
import wardrobe.repositories.ItemRepository;

import java.util.Map;

public class MainController {


    @GetMapping("/")
    public String main(Map<String, Object> model) {
        model.put("greetings", "Main page frontend");
        return "main";

    }

    @GetMapping("/auth")
    public String auth(Map<String, Object> model) {
        return "auth";
    }

    @GetMapping("/forgotpass")
    public String forgotPass(Map<String, Object> model) {
        return "forgotpass";
    }






}
