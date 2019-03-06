package lv.java2.shopping_list;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ServiceResponse<T> {

    private T data;

    public ServiceResponse(T data) {
        this.data = data;
    }

    @JsonProperty("data")
    public T getData() {
        return data;
    }

}
