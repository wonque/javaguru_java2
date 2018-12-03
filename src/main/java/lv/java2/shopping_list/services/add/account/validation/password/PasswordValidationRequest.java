package lv.java2.shopping_list.services.add.account.validation.password;

public class PasswordValidationRequest {

    private String enteredPassword;
    private String confirmedPassword;

    public PasswordValidationRequest(String enteredPassword, String confirmedPassword) {
        this.enteredPassword = enteredPassword;
        this.confirmedPassword = confirmedPassword;
    }

    protected String getEnteredPassword() {
        return enteredPassword;
    }

    public void setEnteredPassword(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }

    protected String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
