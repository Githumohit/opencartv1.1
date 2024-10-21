package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static FileInputStream fi;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileOutputStream fo;
	public static CellStyle style;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}

	public int getRowCount(String xlsheet ) throws IOException
		{
			fi = new FileInputStream(path);
			wb= new XSSFWorkbook (fi);
			ws = wb.getSheet(xlsheet);
			int rowCount = ws.getLastRowNum();
			wb.close();
			fi.close();
			return rowCount;
		
		}
	
	public int getCellCount (String xlsheet, int rownum) throws IOException
		{
			fi= new FileInputStream(path);
			wb = new XSSFWorkbook (fi);
			ws= wb.getSheet(xlsheet);
			row =ws.getRow(rownum);
			int cellCount = row.getLastCellNum();
			wb.close();
			fi.close();
			return cellCount;
			
		}
		
	public String getCellData(String xlsheet, int rownum, int colnum) throws IOException
		{
			fi = new FileInputStream(path);
			wb = new XSSFWorkbook (fi);
			ws = wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			cell = row.getCell(colnum);
			
			String data;
			try
				{
					//data= cell.toString(); // optional simple method.
				
					DataFormatter formatter = new DataFormatter();
					data = formatter.formatCellValue(cell); //Return formatted value of a cell as a string regardless of the cell type.
				}
			catch (Exception e)
					{
						data = "";
				
					}
				
				wb.close();
				fi.close();
				return data;
			}
		
	public void setCellData(String x1sheet, int rownum, int colnum, String data) throws IOException
			{
		// open the sheet and get the data into new cell
				fi = new FileInputStream (path);
				wb = new XSSFWorkbook (fi);
				ws = wb.getSheet(x1sheet);
				row=ws.getRow(rownum);
				
				cell = row.createCell(colnum);
				cell.setCellValue(data);
			
		// open the sheet in write mode and write the data
				fo = new FileOutputStream(path);
				wb.write(fo);
				
				wb.close();
				fi.close();
				fo.close();
				
			}
	
	public void fillGreenColor(String xlsheet, int rownum, int colnum) throws IOException
			{
				fi = new FileInputStream(path);
				wb = new XSSFWorkbook (fi);
				ws = wb.getSheet(xlsheet);
				row=ws.getRow(rownum);
				cell = row.getCell(colnum);
				
				 style= wb.createCellStyle();
				 style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				 style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				 
				 cell.setCellStyle(style);
				 fo = new FileOutputStream(path);
					wb.write(fo);
					
					wb.close();
					fi.close();
					fo.close();
			}
	
	public void fillRedColor(String xlsheet, int rownum, int colnum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook (fi);
		ws = wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell = row.getCell(colnum);
		
		 style= wb.createCellStyle();
		 style.setFillForegroundColor(IndexedColors.RED.getIndex());
		 style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		 
		 cell.setCellStyle(style);
		 fo = new FileOutputStream(path);
			wb.write(fo);
			
			wb.close();
			fi.close();
			fo.close();
	}
		
	}
