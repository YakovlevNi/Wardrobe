package wardrobe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wardrobe.model.Item;

public interface ItemRepository extends JpaRepository <Item,Integer> {
}
