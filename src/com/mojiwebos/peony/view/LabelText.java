package com.mojiwebos.peony.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class LabelText extends Composite{
	
	private Label label;
	private Text text;
	
	public LabelText(Composite parent, int style) {
		super(parent, style);
		
		//Initial Widget
		label = new Label(this, SWT.SHADOW_NONE | SWT.CENTER);
		label.setAlignment(SWT.RIGHT);
		label.setBounds(0, 3, 65, 15);
		label.setText("Label :");
		text = new Text(this, SWT.BORDER);
		text.setBounds(70, 0, 100, 20);
		
		this.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				label.setBounds(0, 3, 70, 15);
				setSize(getParent().getSize().x - 22, 20);
				text.setBounds(75, 0, getParent().getSize().x - 97, 20);
			}
		});
	}

	public void setLabelText(String text) {
		this.label.setText(text);
	}
	
	public String getText() {
		return this.text.getText();
	}
	
	public Label getLabelWidget() {
		return label;
	}
	
	public void setLabelWidget(Label label) {
		this.label = label;
	}
	
	public Text getTextWidget() {
		return text;
	}
	
	public void setTextWidget(Text text) {
		this.text = text;
	}
}
