package services.add.validation.rules;

import db.ProductRepository;
import domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import services.Error;
import services.add.validation.rules.DuplicateProductTitleRule;

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
        rule = new DuplicateProductTitleRule(repository);
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
