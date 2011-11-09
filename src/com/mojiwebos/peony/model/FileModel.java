package com.mojiwebos.peony.model;

public class FileModel {
	
	private String id;
	private String srcPath;
	
	public FileModel(String id) {
		super();
		this.id = id;
	}
	public String getSrcPath() {
		return srcPath == null?"":srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "FileModel [id=" + id + ", srcPath=" + srcPath + "]";
	}
}
