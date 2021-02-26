package salon_za_avtomobili.salon.repository;

import org.springframework.stereotype.Repository;
import salon_za_avtomobili.salon.bootstrap.DataHolder;
import salon_za_avtomobili.salon.model.ShoppingCart;
import salon_za_avtomobili.salon.model.ShoppingCartStatus;

import java.util.Optional;

@Repository
public class ShoppingCartRepository {
    public Optional<ShoppingCart> findById(Long id) {
        return DataHolder.shoppingCarts.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartStatus status) {
        return DataHolder.shoppingCarts.stream()
                .filter(i -> i.getKorisnik().getUsername().equals(username) && i.getStatus().equals(status))
                .findFirst();
    }


    public ShoppingCart save(ShoppingCart shoppingCart) {
        DataHolder.shoppingCarts
                .removeIf(i -> i.getKorisnik().getUsername().equals(shoppingCart.getKorisnik().getUsername()));

        DataHolder.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

}
