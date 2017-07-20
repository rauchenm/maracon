package model;

import java.time.LocalDate;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import javafx.collections.ObservableList;

public class Report {

	private LocalDate reportDate;
	private String reportID;
	private ObservableList<String> higlights;
	private ObservableList<String> issues;
	private ObservableList<Userstory> stories;
	
	public Report(LocalDate reportDate, String reportID, ObservableList<String> higlights, ObservableList<String> issues,
			ObservableList<Userstory> stories) {
		super();
		this.reportDate = reportDate;
		this.reportID = reportID;
		this.higlights = higlights;
		this.issues = issues;
		this.stories = stories;
	}
	public LocalDate getReportDate() {
		return reportDate;
	}
	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportID() {
		return reportID;
	}
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}
	public ObservableList<String> getHiglights() {
		return higlights;
	}
	public void setHiglights(ObservableList<String> higlights) {
		this.higlights = higlights;
	}
	public ObservableList<String> getIssues() {
		return issues;
	}
	public void setIssues(ObservableList<String> issues) {
		this.issues = issues;
	}
	public ObservableList<Userstory> getStories() {
		return stories;
	}
	public void setStories(ObservableList<Userstory> stories) {
		this.stories = stories;
	}
	public String toString(){
	
		String string = 
				"Report ID: " + reportID + "\n"
				+ "Date: " + getReportDate() + "\n"
				+ "Highlights: " + getHiglights() + "\n"
				+ "Issues: " + getIssues() + "\n"
				+ "Userstories: ";
		
		for (int i = 0; i < stories.size(); i++) {
			string = string + stories.get(i).getUserStoryID() + ", " 
					+ stories.get(i).getEntity() + ", " 
					+ stories.get(i).getStatus() + ", " 
					+ stories.get(i).getTcTotal() + ", " 
					+ stories.get(i).getTcPass() + ", " 
					+ stories.get(i).getTcFail() + ", " 
					+ stories.get(i).getTcBlocked() + ", " 
					+ stories.get(i).getTcNoRun() + ", " 
					+ stories.get(i).getTcDefer() + ", " 
					+ stories.get(i).getTcExePerc() + ", " 
					+ stories.get(i).getTcPassPerc() + ", " 
					+ stories.get(i).getDefects() + ", " 
					+ stories.get(i).getComments();

			;
		}
		
		return string;
		
	}
	
	public String toHttp() {
		
		String httpString = 
				
				"<!DOCTYPE html> <html> <head> "
			+ "<style>	table { "
			+ " font-family: arial, sans-serif;"
			+ " border-collapse: collapse;"
			+ " width: 100%; }"

			+ "td, th {"
			+ "  border: 1px solid #dddddd;"
			+ "  text-align: left;"
			+ "  padding: 8px;}"

			+ "tr:nth-child(even) {"
		    + "background-color: #dddddd;}"
		    + "</style> </head> <body>"
		    
			//Table Report ID
			+ "<table>"
			+ "  <tr>"
			+ "    <th>ReportId</th>"
			+ "  </tr>"
			+ "  <tr>"
			+ "    <td>" + this.getReportID() + "</td>"
			+ "  </tr>"
			+ "</table><br>"

			//Table Highlights
			+ "<table>"
			+ "  <tr>"
			+ "    <th>ID</th>"
			+ "    <th>Highlight</th>"
			+ "  </tr>";
			for (int i = 0; i < higlights.size() ; i++) {
				httpString = httpString + "  <tr>"
						+ "   <td>" + i + "</td>"
						+ "   <td>" + getHiglights().get(i) + "</td>"
						+ "  </tr>";
			}
			httpString = httpString + "</table><br>"
			
			//Table Issues
			+ "<table>"
			+ "  <tr>"
			+ "    <th>ID</th>"
			+ "    <th>Issue</th>"
			+ "  </tr>"
			+ "  <tr>" ;
			for (int i = 0; i < issues.size(); i++) {
					//+ "   <td>" + getIssueId + "</td>"
			httpString = httpString + "  <tr><td>" + i + "</td>"
					+ "    <td>" + getIssues().get(i)+ "</td></tr>";		
			}
			
			httpString = httpString + "</table><br>"    
		
			//Table Userstories
			+ "<table>"
			+ "  <tr>"
			+ "    <th>Userstory</th>"
			+ "    <th>Entity</th>"
			+ "    <th>Status</th>"
			+ "    <th>TotalTC</th>"
			+ "    <th>Pass</th>"
			+ "    <th>Failed</th>"
			+ "    <th>Blocked</th>"
			+ "    <th>NoRun</th>"
			+ "    <th>Defer</th>"
			+ "    <th>PercExec</th>"
			+ "    <th>PassExec</th>"
			+ "    <th>OutstandingDefect</th>"
			+ "    <th>Comments</th>"
			+ "  </tr>";
			
			for (int i = 0; i < stories.size(); i++) {
			 httpString = httpString	+ "  <tr>"
						+ "   <td>" + stories.get(i).getUserStoryID()  + "</td>"
						+ "   <td>" + stories.get(i).getEntity()  + "</td>"
						+ "   <td>" + stories.get(i).getStatus()  + "</td>"
						+ "   <td>" + stories.get(i).getTcTotal()  + "</td>"
						+ "   <td>" + stories.get(i).getTcPass() + "</td>"
						+ "   <td>" + stories.get(i).getTcFail() + "</td>"
						+ "   <td>" + stories.get(i).getTcBlocked() + "</td>"
						+ "   <td>" + stories.get(i).getTcNoRun() + "</td>"
						+ "   <td>" + stories.get(i).getTcDefer() + "</td>"
						+ "   <td>" + stories.get(i).getTcExePerc() + "</td>"
						+ "   <td>" + stories.get(i).getTcPassPerc() + "</td>"
						+ "   <td>" + stories.get(i).getDefects() + "</td>"
						+ "   <td>" + stories.get(i).getComments() + "</td>"
						+ "  </tr>";
			}
			httpString = httpString	+ "</table>"
			+ "</body>"
			+ "</html>";
		
		return httpString;
			
	}
}
	
	
