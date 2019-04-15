package lv.java2.shopping_list.repository;

import lv.java2.shopping_list.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query ("FROM Item it WHERE it.title = :title")
    Optional<Item> findByTitle(String title);
}
