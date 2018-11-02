package services;

public class UserMenuService {

    public boolean isUserInputValid(int ans) {
        return (ans >= 0 && ans <= 3);
    }
}
