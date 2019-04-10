package lv.java2.shopping_list.repository;

import lv.java2.shopping_list.domain.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    @Query("FROM ShoppingList sl WHERE sl.user.id = :userId AND id = :listId")
    Optional<ShoppingList> findByUserIdAndListId(Long userId, Long listId);

//    @Query("FROM ShoppingList sl WHERE sl.user.id = :userId")
    List<ShoppingList> findAllByUserId(Long userId);

    @Query("FROM ShoppingList sl WHERE sl.user.id = :userId AND lower(title) = :title")
    Optional<ShoppingList> findByUserIdAndTitle(Long userId, String title);

}
