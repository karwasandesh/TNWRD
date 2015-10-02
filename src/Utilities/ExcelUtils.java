package Utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	public static void setExcelFile(String Path,String SheetName) throws Exception {
			try {
   			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			} catch (Exception e){
				throw (e);
			}
	}
	
	
	
	public static String getCellData(int RowNum, int ColNum) throws Exception{
		 
			try{

  			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
  			
  			int value = Cell.getCellType();
  			
  			
  			if(value == 0)
  				return String.valueOf(Cell.getRawValue());
  			
  			String CellData = Cell.getStringCellValue();
  			return CellData;

  			}catch (Exception e){

				return"";

  			}

    }
	
	
	public static int getRowCount(){
		return ExcelWSheet.getLastRowNum();
	}

	@SuppressWarnings("static-access")
	public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

			try{
			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
				} else {
					Cell.setCellValue(Result);
				}
  // Constant variables Test Data path and Test Data file name
  				FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
  				ExcelWBook.write(fileOut);
  				fileOut.flush();
				fileOut.close();
				}catch(Exception e){
					throw (e);
			}

		}
	
}
