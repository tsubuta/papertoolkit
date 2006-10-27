package edu.stanford.hci.r3.devices.channels;

import java.io.File;

import edu.stanford.hci.r3.actions.types.PlaySoundAction;
import edu.stanford.hci.r3.actions.types.TextToSpeechAction;
import edu.stanford.hci.r3.devices.Device;

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
public class AudioChannel {

	private Device parentDevice;

	public AudioChannel(Device device) {
		parentDevice = device;
	}

	/**
	 * @param sound
	 */
	public void playSoundFile(File sound) {
		PlaySoundAction action = new PlaySoundAction(sound);
		parentDevice.invokeAction(action);
	}

	/**
	 * @param text
	 */
	public void readTextOutLoud(String text) {
		TextToSpeechAction action = new TextToSpeechAction(text);
		parentDevice.invokeAction(action);
	}
}
