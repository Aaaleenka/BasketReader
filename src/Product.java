public class Product {
    public String name;
    public int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return name + " " + price + " руб/шт";
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }
}
