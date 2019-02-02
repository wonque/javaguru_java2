package lv.java2.shopping_list.account.repository;

import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query ("FROM Account ac WHERE lower(ac.email) = :email")
    Optional<Account> findByEmail(String email);

}
