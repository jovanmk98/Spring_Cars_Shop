package salon_za_avtomobili.salon.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private Long id;


    private LocalDateTime dateCreated;


    private Korisnik korisnik;


    private List<Avtomobil> products;


    private ShoppingCartStatus status;

    public ShoppingCart() {
        this.id = (long) (Math.random() * 1000);
    }

    public ShoppingCart(Korisnik korisnik) {
        this.id = (long) (Math.random() * 1000);
        this.dateCreated = LocalDateTime.now();
        this.korisnik = korisnik;
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public List<Avtomobil> getProducts() {
        return products;
    }

    public void setProducts(List<Avtomobil> products) {
        this.products = products;
    }

    public ShoppingCartStatus getStatus() {
        return status;
    }

    public void setStatus(ShoppingCartStatus status) {
        this.status = status;
    }
}
