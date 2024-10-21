package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider1
	@DataProvider(name="LoginData")               // provide a name to DataProvider
	public String [][] getData() throws IOException         // create 2 dimensional method
	{    
		
		String path = ".\\testData\\TestData.xlsx";  // taking xl file from testdata folder
		ExcelUtility xlutil = new ExcelUtility(path);  // create object 
		int totalrows= xlutil.getRowCount("Sheet1");  // call the function required
		int totalcells = xlutil.getCellCount("Sheet1",1);
		
		String[][] logindata = new String[totalrows][totalcells]; // store rows & cell numbers in 2 dimensional array
		
		for(int r=1;r<=totalrows; r++)   // read data from row=1
		{
			for(int c=0;c<totalcells;c++)  // read data from cell=0
				{ 
					logindata[r-1][c] = xlutil.getCellData("Sheet1", r, c); // r-1=(1-1)=0 as we read the data from the file row 1 and store in file(logindata) on 0th row position.				
				}
			}
							
		return logindata;   //returning 2 dimensional array
		}
		//DataProvider2
	
	//DataProvider3
	}
