package lv.java2.shopping_list.services.account.get;

public class GetAccountRequest {

    private Long accountId;

    public GetAccountRequest(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }
}
