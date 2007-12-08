package papertoolkit.demos.gigaprints2006.posters;

import java.io.File;

import papertoolkit.PaperToolkit;
import papertoolkit.application.Application;
import papertoolkit.events.PenEvent;
import papertoolkit.events.handlers.ClickHandler.ClickAdapter;
import papertoolkit.paper.Region;
import papertoolkit.paper.sheets.PDFSheet;
import papertoolkit.util.DebugUtils;

/**
 * <p>
 * </p>
 * <p>
 * <span class="BSDLicense"> This software is distributed under the <a
 * href="http://hci.stanford.edu/research/copyright.txt">BSD License</a>.</span>
 * </p>
 * 
 * @author <a href="http://graphics.stanford.edu/~ronyeh">Ron B Yeh</a> (ronyeh(AT)cs.stanford.edu)
 */
public class PALette extends Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// if we do not add a pen, the PaperToolkit will add a pen for us...
		final Application a = new PALette();
		final PaperToolkit p = new PaperToolkit();
		p.startApplication(a);
	}

	private PDFSheet poster;

	/**
	 * 
	 */
	public PALette() {
		super("PALette");
	}

	/**
	 * Add Event Handlers Here. Do nothing unless it is overridden by a subclass.
	 */
	protected void initializeEventHandlers() {
		// lower left side of the poster
		final Region webArea = poster.getRegion("WebsiteArea");
		webArea.addEventHandler(new ClickAdapter() {
			public void clicked(PenEvent e) {
				DebugUtils.println("Web");
			}
		});

		// next to the stanford logo...
		final Region emailArea = poster.getRegion("EmailArea");
		emailArea.addEventHandler(new ClickAdapter() {
			public void clicked(PenEvent e) {
				DebugUtils.println("Email");
			}
		});

		// lower right
		final Region downloadArea = poster.getRegion("DownloadPDF");
		downloadArea.addEventHandler(new ClickAdapter() {
			public void clicked(PenEvent e) {
				DebugUtils.println("Download");
			}
		});

	}

	/**
	 * @see papertoolkit.application.Application#initializeAfterConstructor()
	 */
	protected void initializeAfterConstructor() {
		poster = new PDFSheet(new File("data/Posters/PALette.pdf"));
		// for some stupid reason, PowerPoint and PDF kept giving me a rotated page... Boo. We need
		// to support this in the future, instead of manually rotating the XML files.
		// ACTUALLY: Our Acrobat plugin just plain does not support this, because we flip the
		// locations by subtracting from the height of the document.
		// for Rotated pages, we need to code it by hand. Booooooo.
		// If we're gonna do it by hand, might as well just code it
		// ACTUALLY 2: I forgot! You need to export it through Acrobat Twice. Bah Humbug.
		poster.addRegions(new File("data/Posters/PALette.regions.xml"));

		initializeEventHandlers();

		DebugUtils.println(poster.getRegionNames());
		// weird... we need to use the old method once, until we render it... and
		// then we need to add the patternInfo later... this design should be changed.
		addSheet(poster);
		// addSheet(poster, new File("data/Posters/PALette.patternInfo.xml"));
	}
}