package com.mojiwebos.peony.view;

import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.FlowLayout;

import com.mojiwebos.peony.control.*;
import com.mojiwebos.peony.model.PackageModel;
import com.mojiwebos.peony.util.FadeImageThread;
import com.mojiwebos.peony.util.FileIDMap;

public class HomeShell {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("com.mojiwebos.res.i18n.HomeShell"); //$NON-NLS-1$

	private Shell shell;
	private Composite compositeL;
	
	//Package Group Info
	private LabelText lxtName;
	private LabelText lxtID;
	private LabelText lxtVersion;
	private LabelText lxtDeveloper;
	private Label lblDescription;
	private Text txtDescription;
	
	//Boot Logo Group Info
	private Label lblBootLogo;
	private Label lblHpLogo;
	private Label lblHpLogoB;
	private ImageFileText fxtBootLogo;
	private ImageFileText fxtHpLogo;
	private ImageFileText fxtHpLogoB;
	//Buttons
	private Composite compositeButton;
	private Button btnPreview;
	private Button btnGenerate;
	
	private Composite compositeR;
	private Label lblImg;

	//System Thread
	private FadeImageThread fadeImageThread;
	
	//Controllers
	private ImageControl imageControl;
	private BuildControl buildControl;
	
	
	//Models
	private PackageModel packageModel;
	
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HomeShell window = new HomeShell();
		window.open();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();

		initControllers();
		createContents();
		initListeners();
		
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
		buildControl = new BuildControl();
	}

	/**
	 * Create contents of the window.
	 */
	private void createContents() {
		shell = new Shell(SWT.MIN | SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(HomeShell.class, "/com/mojiwebos/res/img/default-app-icon.png"));
		shell.setText(BUNDLE.getString("HomeShell.shell.text"));
		shell.setMinimumSize(648, 512);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		compositeL = new Composite(shell, SWT.NONE);
		RowLayout rl_compositeL = new RowLayout(SWT.VERTICAL);
		rl_compositeL.fill = true;
		rl_compositeL.spacing = 10;
		rl_compositeL.marginHeight = 10;
		rl_compositeL.marginWidth = 5;
		compositeL.setLayout(rl_compositeL);

		//Package Info
		Group gPkgInfo = new Group(compositeL, SWT.NONE);
		gPkgInfo.setText(BUNDLE.getString("HomeShell.gPkgInfo.text"));
		RowLayout rl_gPkgInfo = new RowLayout(SWT.VERTICAL);
		rl_gPkgInfo.spacing = 5;
		rl_gPkgInfo.marginHeight = 5;
		rl_gPkgInfo.marginWidth = 5;
		gPkgInfo.setLayout(rl_gPkgInfo);
		
		lxtName = new LabelText(gPkgInfo, SWT.NONE);
		lxtName.setLabelText(BUNDLE.getString("HomeShell.lxtName.labelText"));
		
		lxtID = new LabelText(gPkgInfo, SWT.NONE);
		lxtID.setLabelText(BUNDLE.getString("HomeShell.lxtID.labelText")); //$NON-NLS-1$
		
		lxtVersion = new LabelText(gPkgInfo, SWT.NONE);
		lxtVersion.setLabelText(BUNDLE.getString("HomeShell.lxtVersion.labelText")); //$NON-NLS-1$
		
		lxtDeveloper = new LabelText(gPkgInfo, SWT.NONE);
		lxtDeveloper.setLabelText(BUNDLE.getString("HomeShell.lxtDeveloper.labelText")); //$NON-NLS-1$
		
		lblDescription = new Label(gPkgInfo, SWT.NONE);
		lblDescription.setText(BUNDLE.getString("HomeShell.lblDescription.text")); //$NON-NLS-1$
		
		txtDescription = new Text(gPkgInfo, SWT.BORDER | SWT.MULTI);
		txtDescription.setLayoutData(new RowData(280, 50));
		
		//Boot Info
		Group gBootInfo = new Group(compositeL, SWT.NONE);
		gBootInfo.setText(BUNDLE.getString("HomeShell.gBootInfo.text")); //$NON-NLS-1$
		RowLayout rl_gBootInfo = new RowLayout(SWT.VERTICAL);
		rl_gBootInfo.spacing = 5;
		rl_gBootInfo.marginWidth = 5;
		rl_gBootInfo.marginHeight = 5;
		gBootInfo.setLayout(rl_gBootInfo);

		lblBootLogo = new Label(gBootInfo, SWT.NONE);
		lblBootLogo.setText(FileIDMap.get("boot.boot-image.BootLogo"));

		fxtBootLogo = new ImageFileText(gBootInfo, SWT.NONE, "boot.boot-image.BootLogo");
		fxtBootLogo.setLayoutData(new RowData(280, SWT.DEFAULT));
		fxtBootLogo.setFilterExtensions(new String[] { "*.tga" });

		lblHpLogo = new Label(gBootInfo, SWT.NONE);
		lblHpLogo.setText(FileIDMap.get("sysmgr.images.hp-logo"));

		fxtHpLogo = new ImageFileText(gBootInfo, SWT.NONE, "sysmgr.images.hp-logo");
		fxtHpLogo.setLayoutData(new RowData(280, SWT.DEFAULT));
		fxtHpLogo.setFilterExtensions(new String[] { "*.png" });

		lblHpLogoB = new Label(gBootInfo, SWT.NONE);
		lblHpLogoB.setText(FileIDMap.get("sysmgr.images.hp-logo-bright"));

		fxtHpLogoB = new ImageFileText(gBootInfo, SWT.NONE, "sysmgr.images.hp-logo-bright");
		fxtHpLogoB.setLayoutData(new RowData(280, SWT.DEFAULT));
		fxtHpLogoB.setFilterExtensions(new String[] { "*.png" });

		//Preview
		compositeR = new Composite(shell, SWT.BORDER);

		lblImg = new Label(compositeR, SWT.CENTER);
		lblImg.setBackgroundImage(imageControl.getDefaultImage());
		
		//Execute Buttons
		compositeButton = new Composite(compositeL, SWT.NONE);
		compositeButton.setLayoutData(new RowData(280, SWT.DEFAULT));
		compositeButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnPreview = new Button(compositeButton, SWT.NONE);
		btnPreview.setText(BUNDLE.getString("HomeShell.btnPreview.text.preview")); //$NON-NLS-1$
		
		btnGenerate = new Button(compositeButton, SWT.NONE);
		btnGenerate.setText(BUNDLE.getString("HomeShell.btnGenerate.text")); //$NON-NLS-1$
		
		shell.pack();
		//Resize Widget
		lblImg.setBounds(0, 0, 320, 480);
	}

	private void initListeners() {
		
		btnPreview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				if( fxtHpLogo.getTextWidget().getText() != null && !"".equals( fxtHpLogo.getTextWidget().getText()) &&
				   fxtHpLogoB.getTextWidget().getText() != null && !"".equals(fxtHpLogoB.getTextWidget().getText())) {
					if(!fadeImageThread.getAlive()) {
						fadeImageThread.setFadeImage(fxtHpLogoB.getImage());
						lblImg.setBackgroundImage(imageControl.overlay(
							imageControl.getDefaultImage(),
							fxtHpLogo.getImage()
						));
						
						fadeImageThread.setAlive(true);
						btnPreview.setText(BUNDLE.getString("HomeShell.btnPreview.text.stop"));
					} else {
						fadeImageThread.setAlive(false);
						btnPreview.setText(BUNDLE.getString("HomeShell.btnPreview.text.preview"));
					}
				}
			}
		});
		
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				packageModel = new PackageModel();
				packageModel.setName(lxtName.getText());
				packageModel.setID(lxtID.getText());
				packageModel.setVersion(lxtVersion.getText());
				packageModel.setDeveloper(lxtDeveloper.getText());
				packageModel.setDescription(txtDescription.getText());
				
				packageModel.addFile(fxtBootLogo.getFile());
				packageModel.addFile(fxtHpLogo.getFile());
				packageModel.addFile(fxtHpLogoB.getFile());
				
				FileDialog fileDialog = new FileDialog(shell, SWT.SAVE | SWT.SINGLE);
				fileDialog.setFileName(packageModel.getID() + "_" + packageModel.getVersion() + "_all.ipk");
				String outPath = fileDialog.open();
				System.out.println(outPath);
//				buildControl.build(packageModel);
			}
		});
		
//		this.bindImageLabel(fxtBootLogo, lblImg);
		this.bindImageLabel(fxtHpLogo  , lblImg);
		this.bindImageLabel(fxtHpLogoB , lblImg);
	}

	private void bindImageLabel(final ImageFileText flieText, final Label label) {
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
