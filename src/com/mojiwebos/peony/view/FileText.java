package com.mojiwebos.peony.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

import com.mojiwebos.peony.model.FileModel;

public class FileText extends Composite {
	
	Text text;
	Button button;
	private BorderLayout borderL;
	private FileDialog fileDialog;
	
	//Models
	private FileModel file;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FileText(Composite parent, int style, String id) {
		super(parent, style);
		
		//Initial Model
		file = new FileModel(id);
		
		//Initial Widget
		borderL = new BorderLayout(0, 0);
		setLayout(borderL);
		text = new Text(this, SWT.BORDER);
		text.setLayoutData(BorderLayout.CENTER);
		text.setEditable(false);
		
		button = new Button(this, SWT.NONE);
		fileDialog = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String result = fileDialog.open();
				if(result != null && !"".equals(result)) {
					text.setText(result);
					file.setSrcPath(result);
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

	public FileModel getFile() {
		return file;
	}

	public void setFile(FileModel file) {
		this.file = file;
	}
	
	public void setFilterExtensions(String[] extensions) {
		fileDialog.setFilterExtensions(extensions);
	}
}
