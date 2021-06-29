package utils.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DataDriven {

    private String filePath;
    private String sheetName;
    private String className;

    public DataDriven(String filePath, String sheetName, Class className) {
        this.filePath = filePath;
        this.sheetName = sheetName;
        this.className = className.getName();
    }

    private XSSFSheet getSheet() {
        try {
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook excel = new XSSFWorkbook(file);
            return excel.getSheet(sheetName);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DataStorage getData(String tag) {
        ArrayList<String> data = new ArrayList<>();

        XSSFSheet sheet = getSheet();
        for(Row row : sheet) {
            for(Cell cell : row) {
                if(cell.getColumnIndex() == 0) {
                    if(!cell.toString().equalsIgnoreCase(className)) break;
                } else if(cell.getColumnIndex() == 1) {
                    if(!cell.toString().equalsIgnoreCase(tag)) break;
                } else {
                    data.add(cell + "");
                }
            }
            if(data.size() > 0) return new DataStorage(data);
        }

        return null;
    }

}
