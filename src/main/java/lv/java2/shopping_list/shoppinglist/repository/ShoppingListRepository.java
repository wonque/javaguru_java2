package lv.java2.shopping_list.shoppinglist.repository;

import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

//    ShoppingList addToDataBase(ShoppingList shoppingList);

    @Query ("FROM ShoppingList sl WHERE sl.account.id = :accountId AND id = :listId")
    Optional<ShoppingList> getByAccountIdAndListId(Long accountId, Long listId);

    @Query("FROM ShoppingList sl WHERE account = :account AND lower(title) = :title")
    Optional<ShoppingList> findByAccountAndTitle(Account account, String title);

    @Query("FROM ShoppingList sl WHERE sl.account.id = :accountId")
    List<ShoppingList> findListsByAccountId (Long accountId);

}
