import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Basket basket = new Basket(new String[]{"Хлеб", "Яблоки", "Молоко"}, new int[]{100, 200, 300}, new int[]{0,0,0});

        System.out.println("Список возможных товаров для покупки:");
        basket.printList();


        while (true) {
            System.out.println("Введите номер продукта и его количество");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Вы не правильно выполнили ввод");
            } else {
                try {
                    if ((Integer.parseInt(parts[1]) < 0) || (Integer.parseInt(parts[0]) > 3)) {
                        System.out.println("Вы не правильно выполнили ввод");
                    } else {
                        int productNumber = Integer.parseInt(parts[0]) - 1; // продукт который хотят
                        int productCount = Integer.parseInt(parts[1]); //количество

                        basket.addToBasket(productNumber,productCount);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы не правильно выполнили ввод");
                }
            }
        }
        basket.printBasket();
        File newFile = new File("basket.txt");
        basket.saveTxt(newFile);
        System.out.println("Ваша корзина:");
        basket.loadFromTxtFile(newFile).printBasket();

    }
}