package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.HighlightList;
import model.IssueList;
import model.ReadFile;
import model.Report;
import model.Userstory;
import model.UserstoryList;

public class ReportControl implements Initializable {

	// Report Overview
	@FXML
	private Label lbReportId;
	@FXML
	private DatePicker dpReportDate;
	@FXML
	private ChoiceBox<String> cbReports;
	@FXML
	private Button btShowReport;

	// Report Highlights
	@FXML
	private TextField tbHiglight;
	@FXML
	private ListView<String> lvHighlights;
	@FXML
	private Button btAddHighlight;

	// Report Issues
	@FXML
	private TextField tbIssue;
	@FXML
	private ListView<String> lvIssues;
	@FXML
	private Button btAddIssue;

	// Report Userstories
	@FXML
	private ListView<Userstory> lvUserstories;
	@FXML
	private ListView<Userstory> lvReadFile;
	@FXML
	private TextField tbUserstoryID;
	@FXML
	private ChoiceBox<String> cbEntity;
	@FXML
	private ChoiceBox<String> cbStatus;
	@FXML
	private TextField tbTotalTc;
	@FXML
	private TextField tbPass;
	@FXML
	private TextField tbFailed;
	@FXML
	private TextField tbBlocked;
	@FXML
	private TextField tbNoRun;
	@FXML
	private TextField tbDefer;
	@FXML
	private TextField tbExePerc;
	@FXML
	private TextField tbPassPerc;
	@FXML
	private TextField tbOutstandDef;
	@FXML
	private TextField tbComments;

	@FXML
	private Button btAddUserStoryInf;
	@FXML
	private Button btGenerateReport;
	@FXML
	private Button btReadFile;

	UserstoryList userstoryList = new UserstoryList();
	IssueList issueList = new IssueList();
	HighlightList highlightList = new HighlightList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cbStatus.setItems(FXCollections.observableArrayList("Not Started", "Scripting - InProgress", "BA Review",
				"Rework - In Progress", "Testing - In Progress", "Executed with Defects", "Exploratory / SME Testing",
				"Accepted"));

		cbEntity.setItems(FXCollections.observableArrayList("COM", "GIP", "LIF", "CLA", "PoS", "INT"));

	}

	@FXML
	private void addHighlight() {

		highlightList.setHighlight(tbHiglight.getText());

		for (int i = 0; i < highlightList.getSize(); i++) {
			lvHighlights.setItems((highlightList.getAlHighlight()));
		}

		tbHiglight.clear();

	}

	@FXML
	private void addIssue() {

		issueList.setIssue(tbIssue.getText());

		for (int i = 0; i < issueList.getSize(); i++) {
			lvIssues.setItems((issueList.getAlIssue()));
		}

		tbIssue.clear();

	}

	@FXML
	private void generateReport() throws IOException {

		String reportName = "DSR-" + cbEntity.getSelectionModel().getSelectedItem() + "-" + dpReportDate.getValue();
		lbReportId.setText(reportName);

		Report report = new Report(dpReportDate.getValue(), lbReportId.getText(), highlightList.getAlHighlight(),
				issueList.getAlIssue(), userstoryList.getAlUserstory());

		System.out.println(report.toString());

		File file = new File("D:\\Privat\\" + reportName);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(report.toHttp());
		writer.close();
	}

	@FXML
	private void addUserstoryInf() {

		calculatePerc();

		Userstory us = new Userstory(tbUserstoryID.getText(), cbEntity.getSelectionModel().getSelectedItem(),
				cbStatus.getSelectionModel().getSelectedItem(), Integer.valueOf(tbTotalTc.getText()),
				Integer.valueOf(tbPass.getText()), Integer.valueOf(tbFailed.getText()),
				Integer.valueOf(tbBlocked.getText()), Integer.valueOf(tbNoRun.getText()),
				Integer.valueOf(tbDefer.getText()), Double.valueOf(tbExePerc.getText()),
				Double.valueOf(tbPassPerc.getText()), tbOutstandDef.getText(), tbComments.getText());

		userstoryList.add(us);

		lvUserstories.setItems(userstoryList.getAlUserstory());
		clearUserstoriesTextBoxes();

	}

	@FXML
	private void readFile() {

		ReadFile rf = new ReadFile();
		// ReportList reportList = new ReportList();
		// reportList = rf.readFile();
		ObservableList<Userstory> reportList = FXCollections.observableArrayList();

		reportList = rf.readFile();

		for (int i = 0; i < reportList.size(); i++) {

			lvReadFile.setItems(reportList);
		}

		checkReports(reportList);

	}

	private void calculatePerc() {

		int tcPass = Integer.valueOf(tbPass.getText());
		double tcTotal = Double.valueOf(tbTotalTc.getText());
		int tcFailed = Integer.valueOf(tbFailed.getText());
		double exePerc = 0.0;
		double passPerc = 0.0;

		exePerc = ((tcPass + tcFailed) / tcTotal) * 100;
		passPerc = (tcPass / tcTotal) * 100;

		tbExePerc.setText(String.valueOf(exePerc));
		tbPassPerc.setText(String.valueOf(passPerc));
	}

	private void clearUserstoriesTextBoxes() {

		tbUserstoryID.clear();
		// cbEntity.getSelectionModel().clearSelection();
		cbStatus.getSelectionModel().clearSelection();
		tbTotalTc.setText("0");
		tbPass.setText("0");
		tbFailed.setText("0");
		tbBlocked.setText("0");
		;
		tbNoRun.setText("0");
		tbDefer.setText("0");
		tbExePerc.setText("0.0");
		tbPassPerc.setText("0.0");
		tbOutstandDef.clear();
		tbComments.clear();

	}

	private void checkReports(ObservableList<Userstory> reportList) {

		ObservableList<String> olReports = FXCollections.observableArrayList();
		
		for (int j = 0; j < reportList.size(); j++) {

			if (olReports.isEmpty()) {
				olReports.add(reportList.get(j).getReportId());
			}
			
			if(olReports.get(olReports.size()-1).equals(reportList.get(j).getReportId())) {
				
			}else olReports.add(reportList.get(j).getReportId());
		}

		cbReports.setItems(olReports);
	}

	
	@FXML
	private void viewDetailsReport() {
		
		lvReadFile.setItems(null);
		String reportId = cbReports.getSelectionModel().getSelectedItem();
		
		ReadFile rf = new ReadFile();
		ObservableList<Userstory> reportList = FXCollections.observableArrayList();
		ObservableList<Userstory> filteredList = FXCollections.observableArrayList();
		
		reportList = rf.readFile();
		
		for (int i = 0; i < reportList.size(); i++) {
			
			if(reportId.equals(reportList.get(i).getReportId()))
			{filteredList.add(reportList.get(i));}
			
		} 	lvReadFile.setItems(filteredList);

		
	}
}
