package Token.service;

import Token.entity.Users;
import Token.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    Add new User
    public Users create(Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

//    Get One User by Id
    public Users getUser(Integer id){
        return userRepository.findById(id).orElse(null);
    }

//    Get One User by Phone
    public Users getUserByPhone(String phone){
        return userRepository.findByPhone(phone);
    }

//    Get Users List
    public List<Users> listUser(){
        return userRepository.findAll();
    }

//    Check with User Phone
    public boolean checkUserPhone(String phona){
        return userRepository.existsByPhone(phona);
    }
}
