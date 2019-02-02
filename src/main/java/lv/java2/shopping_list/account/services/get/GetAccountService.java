package lv.java2.shopping_list.account.services.get;

import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.account.repository.AccountRepository;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.web.dto.AccountDTO;
import lv.java2.shopping_list.web.dto.mappers.AccountDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class GetAccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountDTOMapper accountDTOMapper;

    @Transactional
    public GetAccountResponse getById(Long id) {
        Optional<Account> founded = accountRepository.findById(id);
        if (founded.isPresent()) {
            AccountDTO accountDTO = accountDTOMapper.toDTO(founded.get());
            return new GetAccountResponse(accountDTO, HttpStatus.OK);
        }
        return buildAccountNotFoundResponse();
    }

    private GetAccountResponse buildAccountNotFoundResponse() {
        ShoppingListError error = new ShoppingListError("ID", "Account not found!");
        error.setErrorDatetimeToNow();
        return new GetAccountResponse(error, HttpStatus.NOT_FOUND);
    }

}
