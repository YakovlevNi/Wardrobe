package wardrobe.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wardrobe.model.Role;
import wardrobe.model.User;
import wardrobe.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Поиск по именя пользователя
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
    // Добавление пользователя
    public boolean addUser(User user) {

        User userFromSQL = userRepository.findByUsername(user.getUsername());
        if (userFromSQL != null) {
            return false;
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        sendMessage(user);

        return true;
    }

    // Отправка сообщения для подтверждения почты

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getMail())) {
            String message = String.format("Hello, %s! \n" +
                            "Welcome to Wardrobe. Please visit link to activate account http://loclalhost:8080/activate/%s!",
                    user.getUsername(), user.getActivationCode());
            mailSender.send(user.getMail(), "Activation Wardrobe", message);
        }
    }


    //Проверка на активацию пользователя
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }
    // Изменение параметров в пользователе
    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getMail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) || (userEmail != null && userEmail.equals(email));
        if (isEmailChanged) {
            user.setMail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
            if (!StringUtils.isEmpty(password)) {
                user.setPassword(password);
            }
            userRepository.save(user);
            if (isEmailChanged) {
                sendMessage(user);
            }

        }
    }
}
