import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket implements Serializable {

    String[] nameProduct;
    int[] priceProduct;

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

    List<ProductInCart> cart = new ArrayList<>();

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

    public void saveBin(File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(cart);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void loadFromBinFile(File file) throws Exception {

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            List<ProductInCart> cart = null;
            cart = (List<ProductInCart>) ois.readObject();
            System.out.print(cart.toString());

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }


    }
}


