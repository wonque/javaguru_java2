package lv.java2.shopping_list.services;

import org.springframework.stereotype.Component;

@Component
public class UserInputValidation {

    public boolean isEnteredTitleEmpty(String title) {
        return (title == null || title.isEmpty() || title.matches("\\s+"));
    }

    public boolean isBiggerZero(double price) {
        return price > 0;
    }

    public boolean isEqualOrBiggerZeroLessOrEqual3(int input) {
        return (input >= 0 && input <= 3);
    }

    public boolean isEqualZeroOrEqualOne(int input) {
        return (input == 0 || input == 1);
    }

    public boolean isEqualOrBiggerZeroLessOrEqual4(int input) {
        return (input >= 0 && input <= 4);
    }
}
