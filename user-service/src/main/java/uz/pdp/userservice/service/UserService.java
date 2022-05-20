package uz.pdp.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.dto.UserDto;
import uz.pdp.userservice.entitiy.User;
import uz.pdp.userservice.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDto getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return null;
        }

        User user = optionalUser.get();
        UserDto userDto = new UserDto(user.getFullName(),
                user.getEmail(), user.getPhoneNumber(),
                user.getPassword());
        return userDto;
    }
}
