package com.mojiwebos.peony.control;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class FadeImageThread extends Thread {

	private Shell shell;
	private FadeImageRun fadeImageRun;
	private boolean threadAlive = false;

	public FadeImageThread(Shell shell, Label lblImage) {
		super();
		this.shell = shell;
		fadeImageRun = new FadeImageRun(lblImage);
	}
	
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				if(threadAlive)
					shell.getDisplay().asyncExec(fadeImageRun);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	public void setAlive(boolean alive) {
		this.threadAlive = alive;
	}
	
	public boolean getAlive() {
		return this.threadAlive;
	}
	
	public void setFadeImage(Image fadeImage) {
		fadeImageRun.setFadeImage(fadeImage);
	}
	
	private class FadeImageRun implements Runnable {

		private Label lblImage;
		private Image fadeImage;
		private int i = 0;
		private boolean isAdd = false;
		private ImageControl imageControl;
		
		public FadeImageRun(Label lblImage) {
			super();
			this.lblImage = lblImage;
			this.imageControl = new ImageControl(null);
		}
		
		@Override
		public void run() {
			if(fadeImage != null) {
				lblImage.setImage(imageControl.fade(fadeImage, i));
				if(isAdd) {
					i += 15;
					if(i > 255) {
						isAdd = false;
						i = 255;
					}
				} else {
					i -= 15;
					if(i < 0) {
						isAdd = true;
						i = 0;
					}
				}
			}
		}

		public Image getFadeImage() {
			return fadeImage;
		}

		public void setFadeImage(Image fadeImage) {
			this.fadeImage = fadeImage;
		}
	}
}
