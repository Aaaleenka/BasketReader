import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
            out.println(cart);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void loadFromTxtFile(File textFile) throws IOException {

        try (FileReader input = new FileReader(textFile);) {
            int c;
            while ((c = input.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }
}


