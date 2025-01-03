package utilities;

import java.io.IOException;
//Dataprovider1

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		String path=".\\testData\\OpenCart_Login.xlsx";
		Excelutility xlutil=new Excelutility(path);
		int totalrows=xlutil.getRowCount("sheet1");
		int totalcell=xlutil.getCellCount("sheet1", 1);
		String logindata[][]= new String[totalrows][totalcell];
		for(int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcell;j++) {
				 logindata[i-1][j]=xlutil.getCellData("sheet1", i, j);
				
			}
		}
		return logindata;
		
	}

}
