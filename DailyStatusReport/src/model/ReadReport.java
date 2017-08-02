package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReadReport {
	
	
	// Files aus denen eingelesen wird...
	private File fileData;
	
	// Pfade zu den Files
	// private String pathUserStories = "D:\\Privat\\DSR_Userstories.txt";

	private String pathReportPrep = "C:\\Users\\Markus\\Downloads\\ReportPrepGIP.txt";

	ObservableList<Userstory> olUserstories = FXCollections.observableArrayList();
	String date = "";
	int calWeek = 0;
	
	public ObservableList<Userstory> readReports(String date, int weekNumber) {
	
		fileData = new File(pathReportPrep);
		this.date = date;
		this.calWeek = weekNumber;
		
		String line = "";
		String[] lineData;
		ObservableList<String> reportData = FXCollections.observableArrayList();
		
	try {
		BufferedReader br = new BufferedReader(new FileReader(fileData));
		while ((line = br.readLine()) != null) {
			if (!line.contains("ID")) {
				lineData = line.split(";");
				if(lineData[2].equals(String.valueOf(weekNumber))) {
					//WEITER MIT MZIMP usw...
					
				}
			}
		}
		br.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	for (int i = 0; i < reportData.size(); i++) {

			System.out.println(reportData.get(i));
	}

	return olUserstories;
	}
	
}