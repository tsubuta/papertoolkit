package papertoolkit.demos.debug;

import papertoolkit.PaperToolkit;
import papertoolkit.application.Application;
import papertoolkit.events.PenEvent;
import papertoolkit.events.handlers.ClickHandler.ClickAdapter;
import papertoolkit.paper.Region;
import papertoolkit.paper.Sheet;
import papertoolkit.paper.regions.ButtonRegion;
import papertoolkit.pen.Pen;
import papertoolkit.util.DebugUtils;

/**
 * <p>
 * 
 * </p>
 * <p>
 * <span class="BSDLicense"> This software is distributed under the <a
 * href="http://hci.stanford.edu/research/copyright.txt">BSD License</a>. </span>
 * </p>
 * 
 * @author <a href="http://graphics.stanford.edu/~ronyeh">Ron B Yeh</a> (ronyeh(AT)cs.stanford.edu)
 * 
 */
public class DebugPaperApplication {
	public static void main(String[] args) {

		Sheet sheet = new Sheet(8.5, 11);

		Region r = new Region("Ink", 1, 1, 6, 4);
		r.addEventHandler(new ClickAdapter() {
			public void clicked(PenEvent e) {
				DebugUtils.println(e.getPenName() + " Clicked");
			}
		});

		ButtonRegion buttonRegion = new ButtonRegion("Send", 6, 9, 2, 1.3) {
			protected void onClick(PenEvent e) {
				DebugUtils.println("I got clicked");
			}
		};

		sheet.addRegion(r);
		sheet.addRegion(buttonRegion);

		Application app = new Application("A Simple Debug Application");
		app.addPenInput(new Pen("Local Pen"));
		app.addPenInput(new Pen("Laptop Pen", "171.66.51.122"));
		app.addSheet(sheet); // no pattern info xml file loaded...

		PaperToolkit p = new PaperToolkit();
		p.startApplication(app);
	}
}