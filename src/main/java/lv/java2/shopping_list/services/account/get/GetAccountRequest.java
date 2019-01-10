package lv.java2.shopping_list.services.account.get;

public class GetAccountRequest {

    private Long id;

    public GetAccountRequest(Long accountId) {
        this.id = accountId;
    }

    public Long getAccountId() {
        return id;
    }
}
