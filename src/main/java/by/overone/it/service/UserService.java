package by.overone.it.service;

import by.overone.it.entity.User;
import by.overone.it.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveUser(
            String username,
            String name,
            String secondName,
            String street,
            String house,
            String porchNumber,
            String floor,
            String flat,
            String telephoneNumber,
            String carNumber)
    {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setSecondName(secondName);
        user.setStreet(street);
        user.setHouse(house);
        user.setPorchNumber(porchNumber);
        user.setFloor(floor);
        user.setFlat(flat);
        user.setTelephoneNumber(telephoneNumber);
        user.setCarNumber(carNumber);
        saveUser(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public String getUsername(String username) {
        return userRepository.getUsername(username);
    }
}
