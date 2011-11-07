package com.mojiwebos.control;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.SWTResourceManager;

public class ImageControl {

	private Image defaultImage;

	public ImageControl(String defaultPath) {
		super();
		defaultImage = SWTResourceManager.getImage(ImageControl.class, defaultPath);
	}
	
	public Image overlay(Image buttomImage, Image topImage) {
		
		ImageData topData = topImage.getImageData();
		ImageData bottomData = buttomImage.getImageData();
		
		int zeroX = (bottomData.width  - topData.width ) / 2 - 1;
		int zeroY = (bottomData.height - topData.height) / 2 - 1;
		
		for(int y = 0; y < topData.height; y++) {
			for(int x = 0; x < topData.width; x++) {
				bottomData.setPixel(zeroX + x, zeroY + y, topData.getPixel(x, y));
			}
		}
		
		return new Image(Display.getCurrent(), bottomData);
	}
	
	public Image fade(Image image, int alpha) {
		ImageData imageData = image.getImageData();
		
		byte[] aplhas = new byte[imageData.height * imageData.width];
		
		for(int i = 0; i < aplhas.length; i++) {
			aplhas[i] = (byte)alpha;
		}
		
		imageData.alphaData = aplhas;
		return new Image(Display.getCurrent(), imageData);
	}

	public Image getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(Image defaultImage) {
		this.defaultImage = defaultImage;
	}
}
