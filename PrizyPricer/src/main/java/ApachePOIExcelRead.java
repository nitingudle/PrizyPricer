import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.nitin.prizy.entities.Product;
import com.nitin.prizy.rest.utils.Utility;

public class ApachePOIExcelRead
{

    private static final String FILE_NAME = "D:\\Products.xlsx";
    private static List<Double> KB        = new ArrayList<Double>();
    private static List<Double> MB        = new ArrayList<Double>();
    private static List<Double> GB        = new ArrayList<Double>();

    public static void main(String[] args)
    {

        try
        {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext())
            {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                Product p = new Product();
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    if (cell.getRowIndex() == 0)
                        continue;
                    if (cell.getColumnIndex() == 0)
                        p.setStoreName(cell.getStringCellValue());
                    else if (cell.getColumnIndex() == 2)
                        p.setProductName(cell.getStringCellValue());
                    else if (cell.getColumnIndex() == 3)
                        p.setProductDesc(cell.getStringCellValue());
                    else if (cell.getColumnIndex() == 4)
                        p.setProductPrice(new BigDecimal(cell.getNumericCellValue()));
                    else if (cell.getColumnIndex() == 5)
                    {
                        String code = cell.getStringCellValue();
                        code = code.contains("A") ? code.substring(1) : code;
                        p.setProductCode(code);
                    }
                    p.setPkuid(Utility.instance.getUniqueKey(null));
                }
                System.out.println(p);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}