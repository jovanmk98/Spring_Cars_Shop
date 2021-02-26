package salon_za_avtomobili.salon.service.impl;

import org.springframework.stereotype.Service;
import salon_za_avtomobili.salon.model.Avtomobil;
import salon_za_avtomobili.salon.model.Korisnik;
import salon_za_avtomobili.salon.model.ShoppingCart;
import salon_za_avtomobili.salon.model.ShoppingCartStatus;
import salon_za_avtomobili.salon.repository.KorisnikRep;
import salon_za_avtomobili.salon.repository.ShoppingCartRep;
import salon_za_avtomobili.salon.service.AvtomobilService;
import salon_za_avtomobili.salon.service.ShoppingCartService;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRep shoppingCartRepository;
    private final AvtomobilService avtomobilService;
    private final KorisnikRep korisnikRepository;

    public ShoppingCartServiceImpl(ShoppingCartRep shoppingCartRepository, AvtomobilService avtomobilService, KorisnikRep korisnikRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.avtomobilService = avtomobilService;
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public List<Avtomobil> listAllProducts(Long cartId) {
        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return this.shoppingCartRepository
                .findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    Korisnik korisnik = this.korisnikRepository.findByUsername(username)
                            .orElseThrow();
                    ShoppingCart shoppingCart = new ShoppingCart(korisnik);
                    return this.shoppingCartRepository.save(shoppingCart);
                });

    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long carId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Avtomobil car = this.avtomobilService.findById(carId)
                .orElseThrow();
        List<Avtomobil> products=shoppingCart.getProducts();
        if(!products.contains(car))
            products.add(car);
        return this.shoppingCartRepository.save(shoppingCart);

    }

    @Override
    public Optional<Avtomobil> findById(Long id, String username) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        return shoppingCart.getProducts().stream().filter(i->i.getId().equals(id)).findFirst();
    }
}
