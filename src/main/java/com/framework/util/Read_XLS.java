package com.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_XLS {
public static void main (String args []) throws IOException {
		
		File src = new File("C:\\Users\\vivec\\Desktop\\Imp Facts\\Book1.xlsx");
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis); 
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);
		
		for(int i=0;i<rowCount;i++) {
			String data =sheet.getRow(i).getCell(0).getStringCellValue();
			//System.out.println(data);
			System.out.println(i + " " + data);
		}
		
		
		/*String data = sheet.getRow(0).getCell(0).getStringCellValue();
		
		System.out.println(data);
		String data1 = sheet.getRow(0).getCell(1).getStringCellValue();
		
		System.out.println(data1);*/
		wb.close();
	}



}
