package services.remove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.Error;

import java.util.ArrayList;
import java.util.List;

@Component
public class RemoveProductValidatorImpl implements RemoveProductValidator {

    @Autowired
    private RemoveProductRules rules;

    @Override
    public List<Error> validate(RemoveProductRequest request) {
        List<Error> errors = new ArrayList<>();
        rules.emptyTitleRule(request.getTitle()).ifPresent(errors::add);
        rules.isProductInDataBaseRule(request.getTitle()).ifPresent(errors::add);
        return errors;
    }
}
