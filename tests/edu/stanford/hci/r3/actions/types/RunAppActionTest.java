package edu.stanford.hci.r3.actions.types;

/**
 * 
 * <p>
 * <span class="BSDLicense"> This software is distributed under the <a
 * href="http://hci.stanford.edu/research/copyright.txt">BSD License</a>. </span>
 * </p>
 * 
 * @author <a href="http://graphics.stanford.edu/~ronyeh">Ron B Yeh</a> (ronyeh(AT)cs.stanford.edu)
 */
public class RunAppActionTest {
	public static void main(String[] args) {
		// loads Windows wordpad
		new RunAppAction("write").invoke();
	}
}
