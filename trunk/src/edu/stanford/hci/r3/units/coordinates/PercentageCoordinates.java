package edu.stanford.hci.r3.units.coordinates;

import edu.stanford.hci.r3.units.Percentage;
import edu.stanford.hci.r3.units.Units;

/**
 * <p>
 * Can be used for specifying where we are on a sheet, region, etc. Instead of specifying absolute coordinates
 * (number of pixels), we specify in a fraction relative to the upper left corner of the document (0,0).
 * </p>
 * <p>
 * <span class="BSDLicense"> This software is distributed under the <a
 * href="http://hci.stanford.edu/research/copyright.txt">BSD License</a>. </span>
 * </p>
 * 
 * @author <a href="http://graphics.stanford.edu/~ronyeh">Ron B Yeh</a> (ronyeh(AT)cs.stanford.edu)
 */
public class PercentageCoordinates extends Coordinates {

	/**
	 * @param x
	 * @param y
	 */
	public PercentageCoordinates(Percentage pctInX, Percentage pctInY) {
		super(pctInX, pctInY);
	}

	/**
	 * @return
	 */
	public Units getActualValueInXDirection() {
		return ((Percentage) x).getActualValue();
	}

	/**
	 * @return
	 */
	public Units getActualValueInYDirection() {
		return ((Percentage) y).getActualValue();
	}

	/**
	 * Not as useful as the actual value, because of the aspect ratio of the sheet/region. If it's a wide
	 * sheet, 50% is a lot bigger in X than it is in Y.
	 * 
	 * @return the percentage value in the horizontal direction.
	 */
	public double getPercentageInXDirection() {
		return x.getValue();
	}

	/**
	 * @return the percentage value in the vertical direction.
	 */
	public double getPercentageInYDirection() {
		return y.getValue();
	}

	/**
	 * Verifies that the passed-in unit is in fact of the right type!
	 * 
	 * @param xCoord
	 * @see edu.stanford.hci.r3.units.coordinates.Coordinates#setX(edu.stanford.hci.r3.units.Units)
	 */
	public void setX(Units xCoord) {
		if (xCoord instanceof Percentage) {
			x = xCoord;
		} else {
			System.err.println("PercentageCoordinates: Incorrect type passed to setX(). ["
					+ xCoord.getClass() + "]");
		}
	}

	/**
	 * Verifies that the passed-in unit is in fact of the right type!
	 * 
	 * @param yCoord
	 * @see edu.stanford.hci.r3.units.coordinates.Coordinates#setY(edu.stanford.hci.r3.units.Units)
	 */
	public void setY(Units yCoord) {
		if (yCoord instanceof Percentage) {
			y = yCoord;
		} else {
			System.err.println("PercentageCoordinates: Incorrect type passed to setY(). ["
					+ yCoord.getClass() + "]");
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "PercentageCoordinates { \n" //
				+ "\t" + x.toString() + ", \n" //
				+ "\t" + y.toString() + "\n}";
	}
}
