package services.add.validation;

import services.Error;
import services.add.AddProductRequest;

import java.util.List;

public interface AddProductValidator {

    List<Error> validate(AddProductRequest addProductRequest);
}
