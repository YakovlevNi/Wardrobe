package wardrobe.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import wardrobe.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
