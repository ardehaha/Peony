package com.mojiwebos.peony.view;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

public class ImageFileText extends FileText {

	private Image image;
	
	public ImageFileText(Composite parent, int style, String id) {
		super(parent, style, id);

		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if(text.getText() != null && !"".equals(text.getText())) {
					image = SWTResourceManager.getImage(text.getText());
				}
			}
		});
		
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (text.getText() != null && !"".equals(text.getText())) {
					image = SWTResourceManager.getImage(text.getText());
				}
			}
		});
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
