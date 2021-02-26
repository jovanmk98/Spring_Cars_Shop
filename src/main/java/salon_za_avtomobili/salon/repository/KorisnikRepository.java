package salon_za_avtomobili.salon.repository;

import org.springframework.stereotype.Repository;
import salon_za_avtomobili.salon.bootstrap.DataHolder;
import salon_za_avtomobili.salon.model.Korisnik;

import java.util.Optional;

@Repository
public class KorisnikRepository {
    public Optional<Korisnik> findByUsername(String username) {
        return DataHolder.korisnici.stream().filter(r -> r.getUsername().equals(username)).findFirst();
    }

    public Optional<Korisnik> findByUsernameAndPassword(String username, String password) {
        return DataHolder.korisnici.stream().filter(r -> r.getUsername().equals(username) && r.getPassword().equals(password)).findFirst();
    }

    public Korisnik saveOrUpdate(Korisnik korisnik) {
        DataHolder.korisnici.removeIf(r -> r.getUsername().equals(korisnik.getUsername()));
        DataHolder.korisnici.add(korisnik);
        return korisnik;
    }

    public void delete(String username) {
        DataHolder.korisnici.removeIf(r -> r.getUsername().equals(username));
    }

}
