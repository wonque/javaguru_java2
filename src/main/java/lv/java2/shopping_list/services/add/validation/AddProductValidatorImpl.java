package lv.java2.shopping_list.services.add.validation;

import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.add.AddProductRequest;
import lv.java2.shopping_list.services.add.validation.rules.FirstCharacterRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.java2.shopping_list.services.add.validation.rules.DuplicateProductTitleRule;
import lv.java2.shopping_list.services.add.validation.rules.EmptyTitleRule;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddProductValidatorImpl implements AddProductValidator {


    @Autowired
    private EmptyTitleRule emptyTitleRule;
    @Autowired
    private DuplicateProductTitleRule duplicateProductTitleRule;
    @Autowired
    private FirstCharacterRule firstCharacterRule;


    @Override
    public List<Error> validate(AddProductRequest addProductRequest) {
        List<Error> errors = new ArrayList<>();
        emptyTitleRule.execute(addProductRequest.getTitle()).ifPresent(errors::add);
        firstCharacterRule.execute(addProductRequest.getTitle()).ifPresent(errors::add);
        duplicateProductTitleRule.execute(addProductRequest.getTitle()).ifPresent(errors::add);
        return errors;
    }
}
