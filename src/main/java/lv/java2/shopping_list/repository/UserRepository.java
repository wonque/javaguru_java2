package lv.java2.shopping_list.repository;

import lv.java2.shopping_list.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User us WHERE lower(us.email) = :email")
    @Transactional
    Optional<User> findByEmail(String email);

}
