package lv.java2.shopping_list.db.orm;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.account.Account;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class AccountRepositoryImpl extends ORMRepository implements AccountRepository {


//    @Override
//    public boolean checkIfLoginExists(String login) {
//        Query query = session().createQuery("FROM Account WHERE lower(login) = :login");
//        query.setParameter("login", login);
//        return query.uniqueResult() != null;
//    }

    @Override
    public Account addToBase(Account account) {
        session().save(account);
        return account;
    }

    @Override
    public Optional<Account> findByLogin(String login) {
        String stringQuery = "FROM Account ac WHERE lower(ac.login) = :login";
        Account account = (Account) session().createQuery(stringQuery)
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
    public boolean deleteAccount(Account account) {
        session().remove(account);
        return true;
    }

//    @Override
//    public List<ShoppingList> findAllActiveLists(Account account) {
//        String query = "FROM ShoppingList sl WHERE sl.account = :account AND sl.status = :status";
//        return session().createQuery(query, ShoppingList.class)
//                .setParameter("account", account).setParameter("status", ShoppingListStatus.ACTIVE).list();
//    }
//
//    @Override
//    public List<ShoppingList> findAllArchivedLists(Account account) {
//        String query = "FROM ShoppingList sl WHERE sl.account = :account AND sl.status = :status";
//        return session().createQuery(query, ShoppingList.class)
//                .setParameter("account", account).setParameter("status", ShoppingListStatus.ARCHIVED).list();
//    }

}
