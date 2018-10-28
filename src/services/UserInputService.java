package services;

public class UserInputService {

    public boolean isUserAnswerValid(int ans) {
        return (ans >= 0 && ans <= 3);
    }

}
