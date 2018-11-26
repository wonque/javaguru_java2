package lv.java2.shopping_list.services;

import org.springframework.stereotype.Component;

@Component
public class UserInputValidation {

    public boolean isEnteredTitleEmpty(String title) {
        return (title == null || title.isEmpty() || title.matches("\\s+"));
    }

    public boolean isUserEnteredPriceBiggerThanZero(double price) {
        return price > 0;
    }

    public boolean isMainMenuUserInputValid(int input) {
        return (input >= 0 && input <= 3);
    }
}
