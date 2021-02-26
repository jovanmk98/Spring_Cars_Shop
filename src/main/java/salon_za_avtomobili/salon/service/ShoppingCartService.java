package salon_za_avtomobili.salon.service;

import salon_za_avtomobili.salon.model.Avtomobil;
import salon_za_avtomobili.salon.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    List<Avtomobil> listAllProducts(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductToShoppingCart(String username, Long productId);

    Optional<Avtomobil> findById(Long id, String username);


}
