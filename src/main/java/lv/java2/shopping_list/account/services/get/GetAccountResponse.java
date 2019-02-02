package lv.java2.shopping_list.account.services.get;

import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.web.dto.AccountDTO;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;

public class GetAccountResponse extends ResourceSupport {

    private AccountDTO accountDTO;
    private ShoppingListError error;
    private HttpStatus httpStatus;

    public GetAccountResponse() {
    }

    public GetAccountResponse(AccountDTO accountDTO, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.accountDTO = accountDTO;
    }

    public GetAccountResponse(ShoppingListError error, HttpStatus status) {
        this.httpStatus = status;
        this.error = error;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public ShoppingListError getError() {
        return error;
    }

    public void setError(ShoppingListError error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }

    public void setStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
