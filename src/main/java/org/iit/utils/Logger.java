package org.iit.utils;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Logger {
	/** sev = Severity (E | W | I) **/

	public static void log(String sev, String message) {
		System.out.println(">> " + sev + " :: " + message);
	} // log String

	public static void log(String sev, HashMap<String, List<String>> map) {
		System.out.println(">> " + sev + " :: ");
		for (String key : map.keySet())
			System.out.println(key + ": " + map.get(key));
	} // log HashMap<Key, List>

	public static void logKV(String sev, HashMap<String, String> map) {
		System.out.println(">> " + sev + " :: ");
		for (String key : map.keySet())
			System.out.println(key + ": " + map.get(key));
	} // logKV HashMap<Key, String>

	public static void log(String sev, Sheet sheet) {
		System.out.println(">> " + sev + " :: ");
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Row currentRow = sheet.getRow(i);
			for (int j = 0; j < currentRow.getLastCellNum(); j++) {
				System.out.print(currentRow.getCell(j));
			} // loop columns
			System.out.println();
		} // loop rows
	} // log Sheet

} // Logger
