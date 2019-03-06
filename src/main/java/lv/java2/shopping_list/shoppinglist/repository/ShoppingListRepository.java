package lv.java2.shopping_list.shoppinglist.repository;

import lv.java2.shopping_list.user.domain.User;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

//    ShoppingList addToDataBase(ShoppingList shoppingList);

    @Query ("FROM ShoppingList sl WHERE sl.user.id = :userId AND id = :listId")
    Optional<ShoppingList> getByUserIdAndListId(Long userId, Long listId);

    @Query("FROM ShoppingList sl WHERE user = :user AND lower(title) = :title")
    Optional<ShoppingList> getByUserAndTitle(User user, String title);

    @Query("FROM ShoppingList sl WHERE sl.user.id = :userId")
    List<ShoppingList> getAllByUserId(Long userId);

}
