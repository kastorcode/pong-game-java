package services;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Audio {
	private boolean playing, looping;
	private AudioInputStream audioInputStream;
	private Clip clip;

	
	public Audio (String name) {
		try {
			playing = false;
			looping = false;
			audioInputStream = AudioSystem.getAudioInputStream(new File("res/audios/" + name).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		}
		catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	
	public Audio loop () {
		looping = true;
		return this;
	}
	
	
	public void play () {
		clip.setMicrosecondPosition(0);
		clip.start();
	}

	
	public void toggle () {
		if (playing) {
			clip.stop();
			playing = false;
		}
		else {
			if (looping) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			else {
				clip.start();
			}

			playing = true;
		}
    }
}