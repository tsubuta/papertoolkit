package edu.stanford.hci.r3.demos.documents;

import java.io.File;

import edu.stanford.hci.r3.Application;
import edu.stanford.hci.r3.PaperToolkit;
import edu.stanford.hci.r3.events.filters.InkCollector;
import edu.stanford.hci.r3.paper.Region;
import edu.stanford.hci.r3.paper.Sheet;
import edu.stanford.hci.r3.paper.sheets.PDFSheet;
import edu.stanford.hci.r3.util.DebugUtils;

public class Spreadsheet extends Application {

	private Sheet sheet;
	private Region region;

	public Spreadsheet() {
		super("Spreadsheet");
		addSheet(createSheet());
	}

	private Sheet createSheet() {
		sheet = new PDFSheet(new File("data/Documents/DataSheet.pdf"));
		sheet.addRegion(createRegion());
		return sheet;
	}

	private Region createRegion() {
		region = new Region("Data", 0, 0, 8.5, 11);
		final InkCollector inkCollector = new InkCollector() {
			public void contentArrived() {
				DebugUtils.println(getNumStrokesCollected());
			}
		};
		region.addContentFilter(inkCollector);
		return region;
	}

	public static void main(String[] args) {
		Spreadsheet spreadsheet = new Spreadsheet();
		new PaperToolkit(true).loadApplication(spreadsheet);
	}

}