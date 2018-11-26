package services.remove.validation;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.domain.ProductFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.remove.RemoveProductRules;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductRulesTest {

    @Mock
    private ProductRepository repository;

    @Autowired
    private ProductFactory productFactory;

    @InjectMocks
    private RemoveProductRules rules = new RemoveProductRules();

    @Test
    public void returnErrorIfProductTitleIsNull() {
        Optional<Error> error = rules.nullTitleRule(null);
        assertTrue(error.isPresent());
    }

    @Test
    public void returnNoErrorIfProductTitleIsNotNull() {
        Optional<Error> error = rules.nullTitleRule(" ");
        assertFalse(error.isPresent());
    }

    @Test
    public void returnErrorWhenProductNotInDataBase() {
        String title = "milk";
        Mockito.when(repository.findByTitle(title)).thenReturn(Optional.empty());
        Optional<Error> error = rules.productPresenceInDataBaseRule(title);
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals(String.format("Product with title '%s' not in a database\n", title.toUpperCase()), error.get().getErrorDescription());
    }

    @Test
    public void returnNoErrorWhenProductInDatabase() {
        productFactory = new ProductFactory();
        Product product = productFactory.createNewProductWithTitle("milk");
        Mockito.when(repository.findByTitle("milk")).thenReturn(Optional.of(product));
        Optional<Error> error = rules.productPresenceInDataBaseRule("milk");
        assertFalse(error.isPresent());
    }
}
