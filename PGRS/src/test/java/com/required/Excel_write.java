package com.required;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;

public class Excel_write {

	
	private Sheet sheet1;
	private int rowCount;
	private XSSFWorkbook workbook1;
	private Row row;
	private Cell cell;

	public void write_header() {
		workbook1 = new XSSFWorkbook();
		sheet1 = workbook1.createSheet("Java Books");
		rowCount = 0;
		row = sheet1.createRow(rowCount);
		cell = row.createCell(0);
		cell.setCellValue((String) "Testscenario_Number");
		cell = row.createCell(1);
		cell.setCellValue((String) "Testscenario_Name");
		cell = row.createCell(2);
		cell.setCellValue((String) "Steps");
		cell = row.createCell(3);
		cell.setCellValue((String) "Point of click/Select/write");
		cell = row.createCell(4);
		cell.setCellValue((String) "Input Data");
		cell = row.createCell(5);
		cell.setCellValue((String) "Expected Output");
		cell = row.createCell(6);
		cell.setCellValue((String) "Scenario Passed/Failed");
		cell = row.createCell(7);
		cell.setCellValue((String) "Error");
	}
	
	public void write_parameter(ITestResult i) {
		
		row = sheet1.createRow(++rowCount);
		Object test_scenario =(List) i.getParameters()[0];
		List header = (List) ((List) test_scenario).get(0);
		cell = row.createCell(0);
		cell.setCellValue((String) header.get(1));
		cell = row.createCell(1);
		cell.setCellValue((String) header.get(3));
		
		
		//writing steps
		cell = row.createCell(2);
		String writing= "";
		for (int k=2;k<((List) test_scenario).size()-1;k++) {
			//System.out.println(((List) para).get(k));
			List test = (List) ((List) test_scenario).get(k);
			//System.out.println(test.get(0));
			writing =writing+"\n"+test.get(0);
		}
		cell.setCellValue((String) writing);
		
		
		
		cell = row.createCell(3);
		writing= "";
		for (int k=2;k<((List) test_scenario).size()-1;k++) {
			//System.out.println(((List) para).get(k));
			List test = (List) ((List) test_scenario).get(k);
			//System.out.println(test.get(0));
			//writing =writing+" , "+test.get(1);
			writing =writing+"\n"+test.get(1);
		}
		
		
		cell.setCellValue((String) writing);
		
		
		
		
		
		
		
		
		//writing input_data
		cell = row.createCell(4);
		writing= "";
		for (int k=2;k<((List) test_scenario).size()-1;k++) {
			//System.out.println(((List) para).get(k));
			List test = (List) ((List) test_scenario).get(k);
			//System.out.println(test.get(0));
			//writing =writing+" , "+test.get(1);
			writing =writing+"\n"+test.get(2);
		}
		
		
		cell.setCellValue((String) writing);

		//Writing Expected o/p
		cell = row.createCell(5);
		writing= "";
		for (int k=2;k<((List) test_scenario).size()-1;k++) {
			//System.out.println(((List) para).get(k));
			List test = (List) ((List) test_scenario).get(k);
			//System.out.println(test.get(0));
			writing =writing+"\n"+test.get(3);
		}
		cell.setCellValue((String) writing);
		
		
		cell = row.createCell(6);
	
		//cell.setCellValue((String) ""+i.isSuccess());
		if(i.isSuccess()) {
			cell.setCellValue((String) "PASSED");
		}
		else {
			CellStyle style = workbook1.createCellStyle();  
			 XSSFFont font= workbook1.createFont();
			    font.setFontHeightInPoints((short)10);
			    font.setFontName("Arial");
			    font.setColor(IndexedColors.RED.getIndex());
			    font.setBold(true);
			    font.setItalic(false);
			style.setFont(font);
			
			cell.setCellStyle(style);
			cell.setCellValue((String) "FAILED");
				
		}
	}
	
	void write_error(ITestResult i) {
		cell = row.createCell(7);
		CellStyle style = workbook1.createCellStyle();  
		 XSSFFont font= workbook1.createFont();
		    font.setFontHeightInPoints((short)10);
		    font.setFontName("Arial");
		    font.setColor(IndexedColors.RED.getIndex());
		    font.setBold(true);
		    font.setItalic(false);
		style.setFont(font);
		
		cell.setCellStyle(style);
		String error1= i.getThrowable().getMessage();
		cell.setCellValue((String) error1 );
		
	}
	
	public void write_time(String date) {
		cell = row.createCell(8);
		cell.setCellValue((String) date );
	}
	public void total_time(String date) {
		row = sheet1.createRow(++rowCount);
		cell = row.createCell(8);
		cell.setCellValue((String) date );
	}
	
	public void write_file() {
		try (FileOutputStream outputStream = new FileOutputStream("JavaBooks1.xlsx")) {
            workbook1.write(outputStream);
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
