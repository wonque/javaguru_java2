package lv.java2.shopping_list.services.account.get;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAccountService {

    @Autowired
    private AccountRepository accountRepository;

    public GetAccountResponse get(GetAccountRequest request) {
        return accountRepository.findById(request.getAccountId()).map(GetAccountResponse::new)
                .orElseGet(this::buildAccountNotFoundResponse);
    }

    private GetAccountResponse buildAccountNotFoundResponse() {
        ShoppingListError error = new ShoppingListError("ID", "Account not found!");
        return new GetAccountResponse(error);
    }
}
