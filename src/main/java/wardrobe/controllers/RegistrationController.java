package wardrobe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import wardrobe.model.UserRepository;

@RestController
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;



}
