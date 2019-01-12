package lv.java2.shopping_list.web.dtos.mappers;

import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.web.dtos.AccountDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mappings({
            @Mapping(target = "password", ignore = true),
    })
    AccountDTO accountToAccountDTO(Account account);

    @Mappings({
            @Mapping(target = "password", ignore = true)
    })
    Account dtoToDomain(AccountDTO accountDTO);
}
