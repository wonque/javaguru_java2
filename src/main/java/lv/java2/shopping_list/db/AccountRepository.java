package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.ShoppingList;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    boolean checkIfLoginExists (String login);

    Optional<Account> findByLoginAndPass(String login, String password);

    Optional<Account> findById(Long id);

    Account addToBase(Account account);

    boolean deleteAccount(Account account);

    List<ShoppingList> findAllLists (Account account);
}
