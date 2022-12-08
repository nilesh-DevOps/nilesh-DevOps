package com.required;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Import_data_revised {

	private int numrows;
	private XSSFCell cell1;
	
	private static FileInputStream fis;
	private static XSSFWorkbook book;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static XSSFCell cell;

	@Test
	public Object[][] testData(int sheetnum) throws Exception {

		fis = new FileInputStream("config\\dump.xlsx");
		book = new XSSFWorkbook(fis);
		sheet = book.getSheetAt(sheetnum);
		numrows=sheet.getPhysicalNumberOfRows();
		Object[][] data = null;
		System.out.println(numrows);
		int numtescases=1;
		List e= new ArrayList();
		List e1= new ArrayList();
		List<String> data2= new ArrayList<String>();
		//System.out.println();
		int teste=0;
		int test_count=0;
		
		for (int i=0;i<numrows;i++) {
			//List<String> data1= new ArrayList<>();
			
			//adding into columns
			for (int j=0;j<5;j++) {
				//xpath department
				
				if (getCellValues(i,j )!=null) {
					if(getCellValues(i,0 ).equals("0"))
					{
						//data2.addAll(data1);
						//System.out.println(data2);
						test_count++;
						break;
					}
					else {
						//data1.add(getCellValues(i,j ));
						//System.out.println(getCellValues(i,j ));
					}
				}else {
					//data1.add(getCellValues(i,j ));
					//System.out.println(getCellValues(i,j ));
				}
				
			}
			
		}
		System.out.println("TestCount "+test_count);
		
		data = new Object[test_count][1];

		for (int i=0;i<numrows;i++) {
			List<String> data1= new ArrayList<>();
			
			//adding into columns
			for (int j=0;j<5;j++) {
				//xpath department
				
				if (getCellValues(i,j )!=null) {
					if(getCellValues(i,0 ).equals("0"))
					{
						//data2.addAll(data1);
						//System.out.println(data2);
						break;
					}
					else {
						data1.add(getCellValues(i,j ));
						//System.out.println(getCellValues(i,j ));
					}
				}else {
					data1.add(getCellValues(i,j ));
					//System.out.println(getCellValues(i,j ));
				}
				
			}
			if (!data1.isEmpty()) {
				//System.out.println(data1);
				e.add(data1);
				//System.out.println(e);
			}
			else {
				e1.add(new ArrayList<String>(e));
					data[teste][0]=new ArrayList<String>(e);
					teste++;
				e.clear();
			}
		}
		//System.out.println(data[2][0]);
		book.close();
		fis.close();
		System.out.println(data[0][0]);
		return data;
	
	}
	public static String getCellValues(int row1,int cell1)
	{
		row=sheet.getRow(row1);
		if (row!=null) {
		cell=row.getCell(cell1);
		if (cell!=null) {
		//	System.out.println("not null");
		
		if(cell.getCellType()==1)
		{
			return cell.getStringCellValue();
		}
		else
		{	int d =(int) cell.getNumericCellValue();
		//cell.get
			
		return ""+d;
		}
		}
		return null;
		}
		return "null";
	}
	
}
