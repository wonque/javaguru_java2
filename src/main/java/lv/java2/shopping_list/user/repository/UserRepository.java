package lv.java2.shopping_list.user.repository;

import lv.java2.shopping_list.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query ("FROM User us WHERE lower(us.email) = :email")
    Optional<User> findByEmail(String email);

}
