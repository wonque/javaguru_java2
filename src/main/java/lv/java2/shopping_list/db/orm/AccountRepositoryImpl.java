package lv.java2.shopping_list.db.orm;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.Account;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class AccountRepositoryImpl extends ORMRepository implements AccountRepository {


    @Override
    @Nullable
    public Optional<Account> findAccountByLogin(String login) {
        String query = "FROM Account WHERE lower(login) = :login";
        Account account = (Account) session().createQuery(query)
                .setParameter("login", login).uniqueResult();
        return Optional.ofNullable(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        String query = "FROM Account WHERE id = :id";
        Account account = (Account) session().createQuery(query)
                .setParameter("id", id).uniqueResult();
        return Optional.ofNullable(account);
    }

    @Override
    public Account addToBase(Account account) {
        session().save(account);
        return account;
    }

    @Override
    public boolean deleteAccount(Account account) {
        session().remove(account);
        return true;
    }
}
