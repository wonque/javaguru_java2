package services.add.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.Error;
import services.add.AddProductRequest;
import services.add.validation.rules.DuplicateProductTitleRule;
import services.add.validation.rules.EmptyTitleRule;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddProductValidatorImpl implements AddProductValidator {


    @Autowired
    private EmptyTitleRule emptyTitleRule;
    @Autowired
    private DuplicateProductTitleRule duplicateProductTitleRule;


    @Override
    public List<Error> validate(AddProductRequest addProductRequest) {
        List<Error> errors = new ArrayList<>();
        emptyTitleRule.execute(addProductRequest.getTitle()).ifPresent(errors::add);
        duplicateProductTitleRule.execute(addProductRequest.getTitle()).ifPresent(errors::add);
        return errors;
    }
}
