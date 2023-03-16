import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.json.simple.JSONObject;

import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;


public class ClientLog {
    protected int productNum;
    protected int amount;

    public ClientLog() {
    }

    public ClientLog(int productNum, int amount) {
        this.productNum = productNum;
        this.amount = amount;
    }

    public int getProductNum() {
        return productNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public static void exportAsCSV(File txtFile, List<ClientLog> listClientLogs) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (FileWriter writer = new FileWriter(txtFile, true)) {
            StatefulBeanToCsv<ClientLog> sbc = new StatefulBeanToCsvBuilder<ClientLog>(writer)
                    .withSeparator(',')
                    .withMappingStrategy(getStrategy())
                    .build();
            sbc.write(listClientLogs);
        }
    }

    @Override
    public String toString() {
        return productNum + " " + amount;
    }

    private static ColumnPositionMappingStrategy<ClientLog> getStrategy() {
        ColumnPositionMappingStrategy<ClientLog> strategy =
                new ColumnPositionMappingStrategy<>();
        strategy.setType(ClientLog.class);
        strategy.setColumnMapping("productNum", "amount");
        return strategy;
    }
}
