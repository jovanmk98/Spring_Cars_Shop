package salon_za_avtomobili.salon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salon_za_avtomobili.salon.model.Korisnik;

import java.util.Optional;
@Repository
public interface KorisnikRep extends JpaRepository<Korisnik, String> {
    Optional<Korisnik> findByUsernameAndPassword(String username, String password);
    Optional<Korisnik> findByUsername(String username);

}
