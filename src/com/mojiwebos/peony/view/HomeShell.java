package com.mojiwebos.peony.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.mojiwebos.control.FadeImageThread;
import com.mojiwebos.control.ImageControl;

public class HomeShell {

	private Shell shell;
	private Label lblBootLogo;
	private Label lblHpLogo;
	private Label lblHpLogoB;
	private FileText fxtBootLogo;
	private FileText fxtHpLogo;
	private FileText fxtHpLogoB;
	private Label lblImg;

	private ImageControl imageControl;
	private Button btnPreview;
	
	private FadeImageThread fadeImageThread;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HomeShell window = new HomeShell();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();

		initControllers();
		createContents();
		shell.open();
		shell.layout();
		fadeImageThread = new FadeImageThread(shell, this.lblImg);
		fadeImageThread.start();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		if(fadeImageThread.isAlive()) {
			fadeImageThread.interrupt();
		}
	}

	private void initControllers() {
		imageControl = new ImageControl("/com/mojiwebos/res/img/background.png");
	}

	/**
	 * Create contents of the window.
	 */
	private void createContents() {
		shell = new Shell(SWT.MIN | SWT.CLOSE);
		shell.setSize(660, 479);
		shell.setText("Peony - DIY Your webOS Boot Logo");
		shell.setMinimumSize(648, 512);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite compositeL = new Composite(shell, SWT.NONE);
		FillLayout fl_compositeL = new FillLayout(SWT.VERTICAL);
		fl_compositeL.marginHeight = 5;
		fl_compositeL.marginWidth = 5;
		fl_compositeL.spacing = 5;
		compositeL.setLayout(fl_compositeL);

		Group gPkgInfo = new Group(compositeL, SWT.NONE);
		gPkgInfo.setText("Package Info");

		Group gBootInfo = new Group(compositeL, SWT.NONE);
		gBootInfo.setText("Boot");
		RowLayout rl_gBootInfo = new RowLayout(SWT.VERTICAL);
		rl_gBootInfo.spacing = 5;
		rl_gBootInfo.marginWidth = 5;
		rl_gBootInfo.marginHeight = 5;
		gBootInfo.setLayout(rl_gBootInfo);

		lblBootLogo = new Label(gBootInfo, SWT.NONE);
		lblBootLogo.setText("BootLogo.tga");

		fxtBootLogo = new FileText(gBootInfo, SWT.NONE,
				new String[] { "*.png" });
		fxtBootLogo.setLayoutData(new RowData(290, SWT.DEFAULT));

		lblHpLogo = new Label(gBootInfo, SWT.NONE);
		lblHpLogo.setText("hp-logo.png");

		fxtHpLogo = new FileText(gBootInfo, SWT.NONE, new String[] { "*.png" });
		fxtHpLogo.setLayoutData(new RowData(290, SWT.DEFAULT));

		lblHpLogoB = new Label(gBootInfo, SWT.NONE);
		lblHpLogoB.setText("hp-logo-bright.png");

		fxtHpLogoB = new FileText(gBootInfo, SWT.NONE, new String[] { "*.png" });
		fxtHpLogoB.setLayoutData(new RowData(290, SWT.DEFAULT));

		Composite compositeR = new Composite(shell, SWT.BORDER);
		compositeR.setLayout(new FillLayout(SWT.HORIZONTAL));

		lblImg = new Label(compositeR, SWT.CENTER);
		lblImg.setBackgroundImage(imageControl.getDefaultImage());

		btnPreview = new Button(gBootInfo, SWT.NONE);
		btnPreview.setText("Preview");
		
		btnPreview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				if(!fadeImageThread.getAlive()) {
					fadeImageThread.setFadeImage(fxtHpLogoB.getImage());
					lblImg.setBackgroundImage(imageControl.overlay(
						imageControl.getDefaultImage(),
						fxtHpLogo.getImage()
					));
					
					fadeImageThread.setAlive(true);
					btnPreview.setText("Stop");
				} else {
					fadeImageThread.setAlive(false);
					btnPreview.setText("Preview");
				}
			}
		});

		this.bindImageLabel(fxtBootLogo, lblImg);
		this.bindImageLabel(fxtHpLogo  , lblImg);
		this.bindImageLabel(fxtHpLogoB , lblImg);

		shell.pack();
	}

	private void bindImageLabel(final FileText flieText, final Label label) {
		flieText.getTextWidget().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (flieText.getTextWidget().getText() != null && !"".equals(flieText.getTextWidget().getText())) {
					label.setImage(flieText.getImage());
				}
			}
		});

		flieText.getTextWidget().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (flieText.getTextWidget().getText() != null && !"".equals(flieText.getTextWidget().getText())) {
					label.setImage(flieText.getImage());
				}
			}
		});
	}
}
