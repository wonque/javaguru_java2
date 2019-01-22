package lv.java2.shopping_list.account.web;

import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.account.web.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AccountMapper {


    AccountDTO accountToAccountDTO(Account account);

    @Mappings({
            @Mapping(target = "password", ignore = true)
    })
    Account dtoToDomain(AccountDTO accountDTO);
}
