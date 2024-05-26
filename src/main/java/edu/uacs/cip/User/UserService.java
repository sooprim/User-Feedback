package edu.uacs.cip.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    int NAME_LENGTH = 20;

    private void checkNames(User user) throws Exception {
        if(user.getName().length() > NAME_LENGTH) {
            throw new Exception("Name too long!");
        } else if(user.getSurname().length() > NAME_LENGTH) {
            throw new Exception("Surname too long!");
        }
    }

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) throws Exception {
        checkNames(user);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) throws Exception {
        checkNames(userDetails);
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setSurname(userDetails.getSurname());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
