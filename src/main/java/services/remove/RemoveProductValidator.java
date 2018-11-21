package services.remove;

import services.Error;

import java.util.List;

public interface RemoveProductValidator {

    List<Error> validate(RemoveProductRequest request);

}
