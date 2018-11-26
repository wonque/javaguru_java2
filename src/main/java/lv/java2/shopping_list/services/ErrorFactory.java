package lv.java2.shopping_list.services;

import org.springframework.stereotype.Component;

@Component
public class ErrorFactory {

    public Error createNewError(String title, String descriptions) {
        return new Error(title, descriptions);
    }
}
