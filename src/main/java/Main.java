import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SAXException, ParserConfigurationException {

        Scanner scanner = new Scanner(System.in);
        Basket basket = new Basket(new String[]{"Хлеб", "Яблоки", "Молоко"}, new int[]{100, 200, 300});
        ClientLog lg = new ClientLog();
        List<ClientLog> listClientLogs = new ArrayList<>();

        System.out.println("Список возможных товаров для покупки:");
        basket.printList();


        //считывание из XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));
        Node root = doc.getDocumentElement();
        System.out.println("Корневой элемент: " + root.getNodeName());

        SettingsFileXML settings = new SettingsFileXML();


        SettingsFileXML load = new SettingsFileXML(); //параметры загрузки
        SettingsFileXML save = new SettingsFileXML(); //параметры сохранения
        SettingsFileXML log = new SettingsFileXML(); //параметры лога

        settings.readXML(root, load, save, log); //считали настройки

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
                        basket.addToCart(productNumber, productCount);
                        listClientLogs.add(new ClientLog(productNumber + 1, productCount));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы не правильно выполнили ввод");
                }
            }
        }
        //basket.printBasket();


        //записываем в "log.csv"
        //ClientLog lg = new ClientLog();
        //File newFile = new File("log.csv");
        //lg.exportAsCSV(newFile, listClientLogs);

        //записываем в "basket.json"
        //File newFile1 = new File("basket.json");
        //basket.saveTxt(newFile1);

        //считываем из "basket.json"
        //System.out.println("Ваша корзина:");
        //basket.loadFromTxtFile(newFile1);

        if (load.enabled) {
            //считываем из файла корзину (но ведь мы этого еще не делали)
            // ну и он false), может в других задачах допишем? Или сейчас писать?
        }

        if (save.enabled) {
            basket.saveTxt(save.fileName);
        }
        if (log.enabled) {
            lg.exportAsCSV(log.fileName, listClientLogs);
        }
    }

}




