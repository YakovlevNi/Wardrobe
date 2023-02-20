package wardrobe.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wardrobe.model.User;
import wardrobe.repositories.UserRepository;
import wardrobe.service.UserService;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // Страница входа

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    // Главная страница
    @GetMapping("/")
    public String main(Map<String, Object> model) {
        model.put("greetings", "Hello, lets do our wardrobe api");
        return "main";
    }

    @GetMapping("/forgotpass")
    public String forgotPass(Map<String, Object> model) {
        return "forgotpass";
    }


    // @PostMapping ("/auth")
    //  public String add(@RequestParam String login, @RequestParam String password, Map<String,Object>model){
    //     User user = new User(login,password);
    //     userRepository.save(user);
    //     Iterable<User>users = userRepository.findAll();
    //    model.put("user",users);
    //     return "auth";
    // }

    @PostMapping("/registration")
    public String addUser(
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }


        return "redirect:/";
    }

}
