package com.required;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Import_xpath {

	private int numrows;
	private XSSFCell cell1;
	private static FileInputStream fis;
	private static XSSFWorkbook book;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	public ArrayList<String> range_xpath;
	public ArrayList<String> xpath_name;

	public void xpath_Data() throws Exception {

	        fis = new FileInputStream("config\\dump.xlsx");
			book = new XSSFWorkbook(fis);
			sheet = book.getSheetAt(1);
			numrows=sheet.getPhysicalNumberOfRows();
			//System.out.println(numrows);
			range_xpath= new ArrayList<String>();
			xpath_name= new ArrayList<String>();
			for (int i=0;i<numrows;i++)
			{
				range_xpath.add(getCellValues(i, 0));
				xpath_name.add(getCellValues(i, 1));
			}
		//System.out.println(xpath_name);
		book.close();
		fis.close();
	
	}
	public static String getCellValues(int row1,int cell1)
	{
		row=sheet.getRow(row1);
		cell=row.getCell(cell1);
		if(cell.getCellType()==1)
		{
			return cell.getStringCellValue();
		}
		else
		{	int d =(int) cell.getNumericCellValue();
		//cell.get
			
		return d+"";
		}
	}
	
}
