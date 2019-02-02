package lv.java2.shopping_list.account.services.registration;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.web.dto.AccountDTO;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;

import java.util.List;

public class AccountRegistrationResponse extends ResourceSupport {

    private AccountDTO accountDTO;
    private List<ShoppingListError> errors;
    private HttpStatus httpStatus;

    public AccountRegistrationResponse() {
    }

    public AccountRegistrationResponse (AccountDTO accountDTO, HttpStatus httpStatus){
        this.accountDTO = accountDTO;
        this.httpStatus = httpStatus;
    }

    public AccountRegistrationResponse(List<ShoppingListError> errors, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

    public List<ShoppingListError> getErrors() {
        return errors;
    }

    public void setErrors(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

//    @JsonProperty("account_info")
    @JsonIgnore
    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }
}
