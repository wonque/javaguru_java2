package services;

import org.springframework.stereotype.Component;

@Component
public class UserInputValidation {

    public boolean isEnteredProductTitleEmpty(String title) {
        return title == null || title.isEmpty() || "\"\"".equals(title)
                || "\"".equals(title);
    }

    public boolean isUserEnteredPriceBiggerThanZero(double price) {
        return price > 0;
    }

    public boolean isMainMenuUserInputValid(int input) {
        return (input >= 0 && input <= 3);
    }
}
