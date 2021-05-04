package by.tms.strore.service;


import by.tms.strore.entity.User;

import by.tms.strore.exception.UserAlreadyExistsException;
import by.tms.strore.exception.UserNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.tms.strore.repository.UserRepository;

import java.util.List;

@Data
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void creatUser(User user) {
        if(!userRepository.existsUserByLogin(user.getLogin())){
            userRepository.save(user);
        }else {
            throw new UserAlreadyExistsException(user.getUserName());
        }
    }

    public User getByLogin(String login) {
        User user = userRepository.findUserByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
        return user;
    }

    public User getByName(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new UserNotFoundException(userName));
        return user;
    }

    public void updateUser(User user, String userName) {
        if(userRepository.existsByUserName(userName)){
            userRepository.save(user);
        }else {
            throw new UserNotFoundException(userName);
        }
    }

    public User getById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

    public void deleteUser(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
