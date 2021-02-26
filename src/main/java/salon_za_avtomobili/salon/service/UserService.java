package salon_za_avtomobili.salon.service;

import salon_za_avtomobili.salon.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> listAll();

    Optional<User> findById(Long id);

    User save(String name, String surname, String email);

    void deleteById(Long id);

}
