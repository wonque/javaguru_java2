package lv.java2.shopping_list.security;

import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;

//@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserDetailServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> founded = repository.findByEmail(username);
        if (!founded.isPresent()) {
            throw new EntityNotFoundException("No such user!");
        }
        User user = founded.get();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Collections.emptyList());
    }
}
