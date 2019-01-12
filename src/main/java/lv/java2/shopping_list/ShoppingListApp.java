package lv.java2.shopping_list;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class ShoppingListApp {


    public static void main(String[] args) {
        SpringApplication.run(ShoppingListApp.class, args);
    }

}







