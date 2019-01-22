package lv.java2.shopping_list.account.services.get;

import lv.java2.shopping_list.account.repository.AccountRepository;
import lv.java2.shopping_list.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public GetAccountResponse get(GetAccountRequest request) {
        return accountRepository.findById(request.getAccountId()).map(GetAccountResponse::new)
                .orElseGet(this::buildAccountNotFoundResponse);
    }

    private GetAccountResponse buildAccountNotFoundResponse() {
        ShoppingListError error = new ShoppingListError("ID", "Account not found!");
        error.setStatus(HttpStatus.NOT_FOUND);
        return new GetAccountResponse(error);
    }
}
