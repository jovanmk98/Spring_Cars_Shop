package salon_za_avtomobili.salon.repository;

import org.springframework.stereotype.Repository;
import salon_za_avtomobili.salon.bootstrap.DataHolder;
import salon_za_avtomobili.salon.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    public List<User> listAll() {
        return DataHolder.usersList;
    }

    public Optional<User> findById(Long id) {
        return DataHolder.usersList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }


    public void deleteById(Long id) {
        DataHolder.usersList.removeIf(i -> i.getId().equals(id));
    }

    public Optional<User> save(String name, String surname, String email
    ) {
        DataHolder.usersList.removeIf(i -> i.getName().equals(name));
        User user = new User(name, surname, email);
        DataHolder.usersList.add(user);
        return Optional.of(user);
    }

}
