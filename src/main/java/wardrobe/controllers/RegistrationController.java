package wardrobe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import wardrobe.repositories.UserRepository;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    // Страница входа

    @GetMapping("/registration")
    public String auth(Map<String, Object> model) {
        return "registration";
    }

    // Главная страница
    @GetMapping("/")
    public String main (Map<String,Object>model){
        model.put("greetings","Hello, lets do our wardrobe api");
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



}
