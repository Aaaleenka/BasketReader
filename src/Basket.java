import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    protected String[] nameProduct;
    protected int[] priceProduct;
    protected int[] countProduct;

    public Basket(String[] name, int[] price, int[] count){
        this.nameProduct = name;
        this.priceProduct = price;
        this.countProduct = count;
    }

    private Basket()
    {

    }

    public void addToBasket(int number, int count){
        countProduct[number] += count;
    }

    public void printList(){
        int i = 0;
        while (i < nameProduct.length){
            System.out.println(i + 1 + ". " + nameProduct[i] + " " + priceProduct[i] + " руб/шт" );
            i++;
        }
    }

    public void printBasket(){
        System.out.println("Ваша корзина:");
        int sum = 0;
        for (int i=0; i< nameProduct.length; i++){
            System.out.println(nameProduct[i] + " на сумму " + priceProduct[i] * countProduct[i]);
            sum+= priceProduct[i] * countProduct[i];
        }
        System.out.println("Итого: " + sum);
     }


    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile);) {
            out.println(nameProduct.length);
            for (int i=0; i< nameProduct.length; i++) {
                out.println(nameProduct[i] + " " + priceProduct[i] + " " + countProduct[i]);
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException {

        try (BufferedReader input = new BufferedReader(new FileReader(textFile))){
            String s;

            int i = Integer.parseInt(input.readLine());
            System.out.println(i);
            String[] namePr = new String[i];
            int[] price = new int[i];
            int[] count = new int[i];

            for (int j = 0; j<i; j++) {
                //System.out.println(s);
                s = input.readLine();
                String[] parts = s.split(" ");
                namePr[j] = parts[0];
                price[j] = Integer.parseInt(parts[1]);
                count[j] = Integer.parseInt(parts[2]);
            }
            Basket basket1 = new Basket(namePr,price,count);
            return basket1;
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        Basket basket3 = null;
        return basket3;
    }
}


