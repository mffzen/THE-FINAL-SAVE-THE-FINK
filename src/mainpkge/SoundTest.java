package mainpkge;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class SoundTest {

	public static void main(String[] args) throws Exception {

		// ljudfil som finns sparad i mappen

		URL ljudfil = SoundTest.class.getResource("/back.wav"); // skapar
																// ljudfilen
		AudioClip clip = Applet.newAudioClip(ljudfil); // g�r till musikfil
		clip.play(); // spelar musikfilen
		Thread.sleep(1000); // g�r s� att filen kan spelas upp l�ngre

		/*
		 * clip.loop(); //forts�tter spela upp filen om och om igen
		 * Thread.sleep(20000); clip.stop(); //st�nger loopen
		 * 
		 */

		// Ljudfil fr�n url
		/*
		 * URL url = new URL("http://www.edu4java.com/sound/back.wav");
		 * AudioClip clip = Applet.newAudioClip(url); clip.play();
		 * Thread.sleep(20000);
		 */

	}

}