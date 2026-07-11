package org.bluebridge.poi.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author lingwh
 * @desc POI操作Excel测试
 * @date 2026/7/9 00:00
 */
public class POIExcelTest {
    private static final String FILE_PATH = "D:\\Excel\\test.xlsx";

    public static void main(String[] args) throws Exception {
        // write(FILE_PATH);
        read(FILE_PATH);
    }

    /**
     * 给excel表格写入数据
     *
     * @param path
     * @throws Exception
     */
    public static void write(String path) throws Exception {
        // 针对.xls格式的excel文件
        // Workbook workbook = new HSSFWorkbook();
        // 针对.xlsx格式的excel文件
        // 1. 获取excel文件
        Workbook workbook = new XSSFWorkbook();
        // 2. 创建一个表
        Sheet sheet = workbook.createSheet("sheet-1");
        int data[][] = {{1, 2, 3}, {4, 5, 6}};
        for (int i = 0; i < data.length; i++) {
            // 3. 创建一行
            Row row = sheet.createRow(i);
            // 4. 创建一个单元格
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j);
                // 4. 设置单元格的内容
                cell.setCellValue(data[i][j]);
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /**
     * 读取excel表格数据
     *
     * @throws Exception
     */
    public static void read(String path) throws Exception {
        FileInputStream stream = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(stream);
        Sheet sheet = workbook.getSheet("sheet-1");
        int rowsCount = sheet.getLastRowNum() + 1;
        for (int i = 0; i < rowsCount; i++) {
            Row row = sheet.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                int numericCellValue = (int) cell.getNumericCellValue();
                System.out.print(numericCellValue + " ");
            }
            System.out.println();
        }
    }
}
