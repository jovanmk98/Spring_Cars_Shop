package salon_za_avtomobili.salon.model.excep;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException() {
        super("Vnesete argumenti");
    }
}
