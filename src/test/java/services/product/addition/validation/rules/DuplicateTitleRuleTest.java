package services.product.addition.validation.rules;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.domain.ProductBuilder;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.product.adition.validation.rules.DuplicateProductTitleRule;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class DuplicateTitleRuleTest {

    private ProductRepository repository;
    private DuplicateProductTitleRule rule;
    private ProductBuilder productBuilder;


    @Before
    public void init() {
        this.repository = Mockito.mock(ProductRepository.class);
        rule = new DuplicateProductTitleRule(repository);
        productBuilder = new ProductBuilder();
    }


    @Test
    public void returnTrueIfProductExists() {
        Product product = productBuilder.buildInstance("milk");
        Mockito.when(repository.findByTitle("milk")).thenReturn(Optional.of(product));
        Optional<ShoppingListError> error = rule.execute("milk");

        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("Product with title milk already in database!", error.get().getErrorDescription());
    }


    @Test
    public void returnNoErrorsIfProductNotExists() {
        Mockito.when(repository.findByTitle("milk")).thenReturn(Optional.empty());
        Optional<ShoppingListError> error = rule.execute("milk");
        assertFalse(error.isPresent());

    }

    @Test
    public void returnNoErrorsIfProductIsNull() {
        Optional<ShoppingListError> error = rule.execute(null);

        assertFalse(error.isPresent());

        Mockito.verifyZeroInteractions(repository);

    }
}
