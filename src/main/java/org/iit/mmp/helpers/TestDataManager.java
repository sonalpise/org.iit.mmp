package org.iit.mmp.helpers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.iit.utils.Logger;

public class TestDataManager {

	/**
	 **/
	public static HashMap<String, List<String>> readDataFromExcel(File f, String sheetName)
			throws EncryptedDocumentException, IOException {
		HashMap<String, List<String>> data = new HashMap<String, List<String>>();

		if (!f.exists()) {
			Logger.log("E", "File not found");
			return data;
		}

		Workbook workbook = WorkbookFactory.create(f);
		Sheet sheet = workbook.getSheet(sheetName);
		Logger.log("I",
				"Sheet has data between rows " + (sheet.getFirstRowNum() + 1) + " and " + (sheet.getLastRowNum() + 1));

		/** Create a List for each header element */
		Row headerRow = sheet.getRow(sheet.getFirstRowNum());

		for (int h = 0; h < headerRow.getLastCellNum(); h++) {
			data.put(headerRow.getCell(h).toString(), new ArrayList<String>());
		} // loop headers

		/** Populate data in each header List */
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row currentRow = sheet.getRow(i);
			for (int j = 0; j < currentRow.getLastCellNum(); j++) {
				data.get(headerRow.getCell(j).toString()).add(currentRow.getCell(j).toString());
			} // loop columns
		} // loop rows
		Logger.log("I", data);
		return data;
	} // readDataFromExcel

} // TestDataManager
