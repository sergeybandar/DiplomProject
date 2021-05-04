package by.tms.strore.repository;


import by.tms.strore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

        Optional<User> findByUserName(String userName);
        Optional<User> findUserByLogin(String login);
        boolean existsByUserName(String userName);
        boolean existsUserByLogin(String login);
        void deleteByUserName(String userName);
}
