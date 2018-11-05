package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReader {
    public static Logger logger = LogManager.getLogger(org.testng.TestRunner.class.getName());

    public static Object[][] getExcelData(String fileName, int sheetNumer) throws IOException {
        Object[][] inputData= null;
        FileInputStream fis = new FileInputStream( new File(fileName));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(sheetNumer);
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();
        inputData = new Object[rows][1];
        for(int i=0;i<rows;i++) {
            Map<Object,Object> datamap = new HashMap<Object, Object>();
            for(int j=0; j<cols;j++) {
                datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
            }
            inputData[1][0] = datamap;
        }
        logger.info(" FIle reading is completed from " + fileName);
        return inputData;
    }



}
