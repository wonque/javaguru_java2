package lv.java2.shopping_list.repository;

import lv.java2.shopping_list.domain.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {

}
