package MyFrameWork;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class CommonLib {
	public WebDriver driver;
	public XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public XSSFRow row;
	public static XSSFCell cell;
	public int rowNumb;
	public String celValue;
	static int rowNum = 0;

	
	
	public void fnEnterValueInTextbox(WebElement ele,String text)
	{
		ele.sendKeys(text);
	}	
	
	

	public void fnEnterDate(WebElement ele,Date dateinput)
	{
	    Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    String strDate = formatter.format(date);
		ele.sendKeys(strDate);
	}	
	
	public void fnreadSheet(String path, String sheetname)
	{
		try
		{
			FileInputStream fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetname);
		}
		catch(Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
	
	public static int fnFindRow(String functionname) {
		for (Row row : sheet) {
			if (row.getCell(0).getRichStringCellValue().getString().equalsIgnoreCase(functionname)) {
				rowNum = row.getRowNum();
				break;
			}
		}
		return rowNum;
	}
	
		
	public String fnGetCelValue(int RowNum, int ColNum) 
	{
			cell = sheet.getRow(rowNum).getCell(ColNum);
		
			if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
			{
				Integer val= (int) cell.getNumericCellValue();
				celValue = Integer.toString(val);			
			}
			else if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			{
				celValue = cell.getStringCellValue();
			}
			return celValue;
	}
	
	public  Date getDateCellData(int RowNum, int ColNum) throws Exception {

		try {

			cell = sheet.getRow(rowNum).getCell(ColNum);

			Date CellData = cell.getDateCellValue();

			return CellData;
			

		} catch (Exception e) {
			System.out.println("Incatch");
		}
		return null;

	}

	
	
	public CommonLib(WebDriver driver) {
		this.driver= driver;
	}
	
	
	
}
