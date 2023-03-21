import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    protected String[] nameProduct;
    protected int[] priceProduct;
    protected List<ProductInCart> cart = new ArrayList<>();

    public Basket(String[] name, int[] price) {
        this.nameProduct = name;
        this.priceProduct = price;
    }

    public Basket(List<ProductInCart> cart) {
        this.cart = cart;
    }


    public void printList() {
        int i = 0;
        while (i < nameProduct.length) {
            System.out.println(i + 1 + ". " + nameProduct[i] + " " + priceProduct[i] + " руб/шт");
            i++;
        }
    }

    public void addToCart(int number, int amount) {
        String name = nameProduct[number];
        int finalyPrice = priceProduct[number] * amount;
        cart.add(new ProductInCart(name, finalyPrice));
    }

    public void printBasket() {
        System.out.println("Ваша корзина:");
        int sum = 0;
        for (ProductInCart product : cart) {
            System.out.println(product.toString());
            sum += product.getPrice();
        }
        System.out.println("Итого: " + sum);
    }

    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile);) {
            out.println(cart.size());
            for (int i=0; i<cart.size(); i++){
                ProductInCart element0 = cart.get(i);
                out.println(element0.toString());
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException {
        List<ProductInCart> cart = new ArrayList<>();
        Basket basket1 = new Basket(cart);

        try (BufferedReader input = new BufferedReader(new FileReader(textFile))){
            int count = Integer.parseInt(input.readLine());
            for (int i=0; i<count; i++)
            {
                String s = input.readLine();
                String[] parts = s.split(" ");
                cart.add(new ProductInCart(parts[0], Integer.parseInt(parts[1])));

            }
            basket1 = new Basket(cart);
            return basket1;
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return basket1;
    }
}


