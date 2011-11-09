package com.mojiwebos.peony.util;

import java.util.HashMap;
import java.util.Map;

public class FileIDMap {

	private static Map<String, String> fileMap;
	static {
		fileMap = new HashMap<String, String>();
		
		fileMap.put("sysmgr.images.hp-logo"       , "/usr/palm/sysmgr/images/hp-logo.png");
		fileMap.put("sysmgr.images.hp-logo-bright", "/usr/palm/sysmgr/images/hp-logo-bright.png");
		fileMap.put("boot.boot-image.BootLogo"    , "/boot/boot-image/BootLogo.tga");
	}
	
	public static String get(String key) {
		return fileMap.get(key);
	}
}
