package com.mojiwebos.peony.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;

public class FileText extends Composite {
	
	private Text text;
	private Button button;
	private BorderLayout borderL;
	
	private Image image;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FileText(Composite parent, int style, final String[] filters) {
		super(parent, style);
		borderL = new BorderLayout(0, 0);
		setLayout(borderL);
		
		text = new Text(this, SWT.BORDER);
		text.setLayoutData(BorderLayout.CENTER);
		text.setEditable(false);
		
		button = new Button(this, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
				if(filters.length > 0)
					fd.setFilterExtensions(filters);
				String result = fd.open();
				if(result != null && !"".equals(result)) {
					image = SWTResourceManager.getImage(result);
					text.setText(result);
				}
			}
		});
		button.setLayoutData(BorderLayout.EAST);
		button.setText("...");

	}
	
	public Text getTextWidget() {
		return this.text;
	}
	
	public Button getButtonWidget() {
		return this.button;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
