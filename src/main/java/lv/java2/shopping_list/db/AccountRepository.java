package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.Account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findAccountByLogin(String login);

    Optional<Account> findById(Long id);

    Account addToBase(Account account);

    boolean deleteAccount(Account account);
}
