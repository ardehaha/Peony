package com.mojiwebos.peony.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import com.mojiwebos.peony.model.FileModel;
import com.mojiwebos.peony.model.PackageModel;
import com.mojiwebos.peony.util.FileIDMap;


//Zen hack
public class BuildControl {
	
	private File root;
	
	public BuildControl() {
		root = new File(".peony.tmp");
		
		if(!root.exists())
			root.mkdir();
	}

	public void build(PackageModel packageModel) {
		
		this.buildFolder(packageModel.getFileList());
		try {
			this.copyFiles(packageModel.getFileList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buildFolder(List<FileModel> fileList) {
		
		for(FileModel fileModel : fileList) {
			File file = new File(root.getPath() + FileIDMap.get(fileModel.getId()));
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
		}
	}
	
	private void copyFiles(List<FileModel> fileList) throws IOException {
		File src, dst;
		for(FileModel fileModel : fileList) {
			if(fileModel.getSrcPath() != null && !"".equals(fileModel.getSrcPath())) {
				src = new File(fileModel.getSrcPath());
				dst = new File(root.getPath() + FileIDMap.get(fileModel.getId()));
				
		        int length = 0xFFFF;
		        System.out.println(src.getPath() + " <->" + dst.getPath());
		        FileInputStream in = new FileInputStream(src);
		        FileOutputStream out = new FileOutputStream(dst);
		        FileChannel inC = in.getChannel();
		        FileChannel outC = out.getChannel();
		        int i = 0;
		        while(true){
		            if(inC.position()==inC.size()){
		                inC.close();
		                outC.close();
		               break;
		            }
		            if((inC.size() - inC.position()) < length)
		                length = (int)(inC.size() - inC.position());
		            else
		                length = 0xFFFF;
		            inC.transferTo(inC.position(),length,outC);
		            inC.position(inC.position() + length);
		            i++;
		        }
			}
		}
	}
	
	public static void main(String[] args) {
		BuildControl bc = new BuildControl();
		PackageModel packageModel = new PackageModel();
		packageModel.setName("testName");
		packageModel.setID("testID");
		packageModel.setVersion("testVersion");
		packageModel.setDeveloper("testDeveloper");
		packageModel.setDescription("testDescription");
		
		packageModel.addFile(new FileModel("boot.boot-image.BootLogo"));
		packageModel.addFile(new FileModel("sysmgr.images.hp-logo"));
		packageModel.addFile(new FileModel("sysmgr.images.hp-logo-bright"));
	
		bc.buildFolder(packageModel.getFileList());
	}
}
