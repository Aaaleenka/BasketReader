public class ProductInCart {
    protected String name;
    protected int finalyPrice;

    public ProductInCart(String name, int finalyPrice) {
        this.name = name;
        this.finalyPrice = finalyPrice;
    }

    @Override
    public String toString() {
        return name + " на сумму: " + finalyPrice + " руб.";
    }

    public int getPrice() {
        return finalyPrice;
    }

}
