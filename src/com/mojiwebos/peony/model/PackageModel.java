package com.mojiwebos.peony.model;

import java.util.ArrayList;
import java.util.List;

public class PackageModel {

	private String name;
	private String ID;
	private String version;
	private String developer;
	private String description;
	
	private List<FileModel> fileList;

	public PackageModel() {
		super();
		fileList = new ArrayList<FileModel>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FileModel> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileModel> fileList) {
		this.fileList = fileList;
	}
	
	public void addFile(FileModel fileModel) {
		this.fileList.add(fileModel);
	}

	@Override
	public String toString() {
		return "PackageModel [name=" + name + ", ID=" + ID + ", version="
				+ version + ", developer=" + developer + ", description="
				+ description + ", fileList=" + fileList + "]";
	}
}
