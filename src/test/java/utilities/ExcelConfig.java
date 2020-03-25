package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelConfig {

    FileInputStream fis;
    XSSFWorkbook wb;
    XSSFSheet sh;

    public ExcelConfig(String sourcePath) {
        try {
            File pathSrc = new File(sourcePath);
            fis = new FileInputStream(pathSrc);
            wb = new XSSFWorkbook(fis);
            sh = wb.getSheetAt(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Object[][] readExcel() {
        XSSFRow Row = sh.getRow(0);
        int RowNum = sh.getPhysicalNumberOfRows();// cuento mi número de filas
        int ColNum = Row.getLastCellNum(); // obtengo la última columna
        Object[][] data = new Object[RowNum - 1][ColNum];
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        evaluator.evaluateAll();
        try {
            for (int i = 0; i < RowNum - 1; i++) // loop filas
            {
                XSSFRow row = sh.getRow(i + 1);

                for (int j = 0; j < ColNum; j++) // loop columnas
                {
                    if (row == null)
                        data[i][j] = "";
                    else {
                        Cell cell = row.getCell(j);
                        if (cell == null)
                            data[i][j] = "";
                        else {
                            CellType cellType = cell.getCellType();
                            switch (cellType) {
                                case NUMERIC:
                                    data[i][j] = String.valueOf((int) cell.getNumericCellValue());
                                    break;
                                case STRING:
                                    data[i][j] = cell.getStringCellValue();
                                    break;
                                case BLANK:
                                    break;
                                case ERROR:
                                    break;
                                case FORMULA:
                                    Date javaDate = DateUtil.getJavaDate(cell.getNumericCellValue());
                                    data[i][j] = new SimpleDateFormat("MM/dd/yyyy").format(javaDate);
                                    break;
                                default:
                                    data[i][j] = cell.getStringCellValue();
                                    break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

        return data;
    }

}