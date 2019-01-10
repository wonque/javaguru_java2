package services.account.get;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.services.account.get.GetAccountRequest;
import lv.java2.shopping_list.services.account.get.GetAccountResponse;
import lv.java2.shopping_list.services.account.get.GetAccountService;
import lv.java2.shopping_list.services.shoppinglist.get.GetShoppingListResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAccountServiceTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private GetAccountService getAccountService = new GetAccountService();

    private GetAccountRequest request = new GetAccountRequest(1L);

    @Test
    public void accountNotFoundTest(){
        Mockito.when(repository.findById(request.getAccountId())).thenReturn(Optional.empty());
        GetAccountResponse response = getAccountService.get(request);
        assertNull(response.getAccount());
        assertNotNull(response.getError());
        assertFalse(response.isSuccess());
        assertEquals("ID", response.getError().getField());
        assertEquals("Account not found!", response.getError().getErrorDescription());
    }

    @Test
    public void accountFoundTest(){
        Account account = Mockito.mock(Account.class);
        Mockito.when(repository.findById(request.getAccountId())).thenReturn(Optional.of(account));
        GetAccountResponse response = getAccountService.get(request);
        assertNotNull(response.getAccount());
        assertNull(response.getError());
        assertTrue(response.isSuccess());
    }
}
