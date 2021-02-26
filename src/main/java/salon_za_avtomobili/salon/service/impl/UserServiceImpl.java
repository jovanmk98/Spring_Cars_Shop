package salon_za_avtomobili.salon.service.impl;

import org.springframework.stereotype.Service;
import salon_za_avtomobili.salon.model.User;
import salon_za_avtomobili.salon.model.excep.InvalidArgumentException;
import salon_za_avtomobili.salon.repository.UserRep;
import salon_za_avtomobili.salon.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRep userRepository;

    public UserServiceImpl(UserRep userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> listAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User save(String name, String surname, String email) {
        User u = new User(name, surname, email);
        if (
                name == null || name.isEmpty() ||
                        surname == null || surname.isEmpty()) {
            throw new InvalidArgumentException();
        }
        this.userRepository.save(u);
        return u;
    }


    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
