package com.nitin.prizy.test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nitin.prizy.entities.Product;
import com.nitin.prizy.model.ProductDAO;
import com.nitin.prizy.rest.utils.Utility;

@Component
public class Test
{
    @Autowired
    public Test(ProductDAO productDAO)
    {
        try
        {
            if (productDAO.get() == 0)
            {
                InputStream excelFile = Test.class.getResourceAsStream("/Products.xlsx");
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
                    if (p.getProductCode() != null && p.getProductCode().length() != 0)
                        productDAO.create(p);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
