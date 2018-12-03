package services.remove.validation;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.domain.builders.ProductBuilder;
import lv.java2.shopping_list.services.ShoppingListError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import lv.java2.shopping_list.services.remove.product.RemoveProductRules;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductRulesTest {

    private ProductBuilder productBuilder = new ProductBuilder();
    @Mock
    private ProductRepository repository;


    @InjectMocks
    private RemoveProductRules rules = new RemoveProductRules();

    @Test
    public void returnErrorIfProductTitleIsNull() {
        Optional<ShoppingListError> error = rules.nullTitleRule(null);
        assertTrue(error.isPresent());
    }

    @Test
    public void returnNoErrorIfProductTitleIsNotNull() {
        Optional<ShoppingListError> error = rules.nullTitleRule(" ");
        assertFalse(error.isPresent());
    }

    @Test
    public void returnErrorWhenProductNotInDataBase() {
        String title = "milk";
        Mockito.when(repository.findByTitle(title)).thenReturn(Optional.empty());
        Optional<ShoppingListError> error = rules.productPresenceInDataBaseRule(title);
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals(String.format("Product with title '%s' not in a database\n", title.toUpperCase()), error.get().getErrorDescription());
    }

    @Test
    public void returnNoErrorWhenProductInDatabase() {
        Product product = productBuilder.buildInstance("milk");
        Mockito.when(repository.findByTitle("milk")).thenReturn(Optional.of(product));
        Optional<ShoppingListError> error = rules.productPresenceInDataBaseRule("milk");
        assertFalse(error.isPresent());
    }
}
