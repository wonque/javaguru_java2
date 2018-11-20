package services.add.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import services.Error;
import services.add.AddProductRequest;
import services.add.validation.rules.DuplicateProductTitleRule;
import services.add.validation.rules.EmptyTitleRule;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AddProductValidatorTest {

    @Mock
    DuplicateProductTitleRule duplicateProductTitleRule;

    @Mock
    EmptyTitleRule emptyTitleRule;

    @InjectMocks
    AddProductValidator validator = new AddProductValidatorImpl();

    @Test
    public void collectAndReturnErrorsIfBothRulesFailed() {
        Mockito.when(emptyTitleRule.execute("milk")).thenReturn(Optional.of(new Error("title", "empty")));
        Mockito.when(duplicateProductTitleRule.execute("milk"))
                .thenReturn(Optional.of(new Error("title", "duplicate")));

        List<Error> errors = validator.validate(new AddProductRequest("milk"));

        assertEquals(2, errors.size());
    }

}
