package services.add.validation.rules;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.services.add.validation.rules.DuplicateProductTitleRule;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import lv.java2.shopping_list.services.Error;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class DuplicateTitleRuleTest {

    private ProductRepository repository;
    private DuplicateProductTitleRule rule;


    @Before
    public void init() {
        this.repository = Mockito.mock(ProductRepository.class);
        rule = new DuplicateProductTitleRule();
    }


    @Test
    public void returnTrueIfProductExists() {
        Product product = new Product("milk");
        Mockito.when(repository.findByTitle("milk")).thenReturn(Optional.of(product));
        Optional<Error> error = rule.execute("milk");

        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("Product with title milk already in database!", error.get().getErrorDescription());
    }


    @Test
    public void returnNoErrorsIfProductNotExists() {
        Mockito.when(repository.findByTitle("milk")).thenReturn(Optional.empty());
        Optional<Error> error = rule.execute("milk");
        assertFalse(error.isPresent());

    }

    @Test
    public void returnNoErrorsIfProductIsNull() {
        Optional<Error> error = rule.execute(null);

        assertFalse(error.isPresent());

        Mockito.verifyZeroInteractions(repository);

    }
}
