package com.matschie.data.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	public String[][] getData(String fileName) {
		String[][] data = null;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook("src/test/resources/data/" + fileName + ".xlsx");
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
			data = new String[rowCount][columnCount];
			for (int i = 1; i <= rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
				}
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}