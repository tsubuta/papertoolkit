package papertoolkit.util;

/**
 * A utility for printing messages and seeing which file they originated from. This class will also provide
 * support for creating debug levels (not really implemented yet).
 * 
 * <p>
 * This software is distributed under the <a href="http://hci.stanford.edu/research/copyright.txt"> BSD
 * License</a>.
 * </p>
 * 
 * @author <a href="http://graphics.stanford.edu/~ronyeh">Ron B. Yeh</a> (ronyeh(AT)cs.stanford.edu)
 * 
 * @date Sep 1, 2004 created at FSCA.
 * @date Jul 8, 2005 imported to HCILib.
 * @date Jun 8, 2006 cleaned up stuff. changed format.
 * @date Aug 8, 2006 added to R3.
 */
public class DebugUtils {

	/**
	 * can be used to turn some debugging on, and others off by default, all messages will be printed setting
	 * this to Integer.MAX_VALUE will hide all messages setting this to -1 will show all messages (as long as
	 * your messages have nonnegative priorities)
	 */
	private static int debugPriorityMask = -1;

	/**
	 * prints the line number and originating class. Feel free to turn this on or off as you see fit for your
	 * application.
	 */
	private static boolean debugTraceOn = true;

	/**
	 * used for indexing back into the call stack
	 */
	private static int stackTraceOffset = 3;

	private static final String WHITESPACE = StringUtils.repeat(" ", 54);

	/**
	 * @return the debugPriorityMask
	 */
	public static int getDebugPriorityMask() {
		return debugPriorityMask;
	}

	/**
	 * @return the debugTrace
	 */
	public static boolean isDebugTraceOn() {
		return debugTraceOn;
	}

	/**
	 * @param string
	 */
	public static synchronized void print(Object object) {
		final String s = (object == null) ? "null" : object.toString();
		if (DebugUtils.debugTraceOn) {
			printDebugTrace(0);
		}
		System.out.print(s);
	}

	/**
	 * @param additionalStackOffset
	 */
	private static synchronized String printDebugTrace(int additionalStackOffset) {
		final Thread currThread = Thread.currentThread();
		final int actualOffset = stackTraceOffset + additionalStackOffset;
		final StackTraceElement[] ste = currThread.getStackTrace();
		String className = ste[actualOffset].getClassName();
		className = className.substring(className.lastIndexOf(".") + 1);

		// get the stuff between the parentheses
		String element = ste[actualOffset].toString();
		element = element.substring(element.lastIndexOf("("), element.lastIndexOf(")")+1);
		element = "[" + element + "]";
		String methodName = ste[actualOffset].getMethodName();
		final String trace = className + "." + methodName;

		System.out.print(trace);
		System.out.print("    ");
		int padding = WHITESPACE.length() - trace.length();
		if (padding < 0) {
			padding = 0;
		}
		System.out.print(WHITESPACE.substring(0, padding));

		return element;
	}

	/**
	 * @param object
	 *            the object to print out
	 */
	public synchronized static void println(Object object) {
		printlnWithStackOffset(object, 1);
	}

	/**
	 * @param object
	 * @param debugPriority
	 *            an int that describes how important this message is. If it is greater than
	 *            debugPriorityMask, then it will be printed out. If it is less than debugLevel, then it will
	 *            be hidden.
	 */
	public synchronized static void println(Object object, int debugPriority) {
		if (debugPriority <= debugPriorityMask) {
			// priority is too low, so return
			return;
		}
		stackTraceOffset++;
		println(object);
		stackTraceOffset--;
	}

	/**
	 * @param object
	 * @param additionalStackOffset
	 */
	public synchronized static void printlnWithStackOffset(Object object, int additionalStackOffset) {
		final String s = (object == null) ? "null" : object.toString();
		String linkToLineNumber = "";
		if (DebugUtils.debugTraceOn) {
			linkToLineNumber = printDebugTrace(additionalStackOffset);
		}
		System.out.print(s);
		System.out.println("\t" + linkToLineNumber);
	}

	/**
	 * @param debugPriorityMask
	 *            the debugPriorityMask to set
	 */
	public static void setDebugPriorityMask(int priorityMask) {
		debugPriorityMask = priorityMask;
	}

	/**
	 * @param debugTrace
	 *            the debugTrace to set
	 */
	public static void setDebugTraceVisible(boolean debugTrace) {
		debugTraceOn = debugTrace;
	}

}