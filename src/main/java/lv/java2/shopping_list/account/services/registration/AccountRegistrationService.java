package lv.java2.shopping_list.account.services.registration;

import lv.java2.shopping_list.web.dto.AccountDTO;

public interface AccountRegistrationService {

    AccountRegistrationResponse register(AccountDTO accountDTO);

}
