package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Account addToBase(Account account);

    Optional<Account> findByLogin(String login);

    Optional<Account> findById(Long id);

    boolean deleteAccount(Account account);

//    List<ShoppingList> findAllActiveLists (Account account);
//
//    List<ShoppingList> findAllArchivedLists(Account account);
}
