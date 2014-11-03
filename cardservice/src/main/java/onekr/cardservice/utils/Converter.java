package onekr.cardservice.utils;

import onekr.commonservice.model.FileType;
import onekr.framework.utils.FileUtil;

public class Converter {
	public static FileType getFileType(String filename) {
		if (FileUtil.isFileType(filename, FileUtil.IMAGE_FILE_TYPES)) {
			return FileType.IMAGE;
		} else if (FileUtil.isFileType(filename, FileUtil.AUDIO_FILE_TYPES)) {
			return FileType.AUDIO;
		} else if (FileUtil.isFileType(filename, FileUtil.VIDEO_FILE_TYPES)) {
			return FileType.VIDEO;
		} else if (FileUtil.isFileType(filename, FileUtil.DOC_FILE_TYPES)) {
			return FileType.DOC;
		} else if (FileUtil.isFileType(filename, FileUtil.PACKAGE_FILE_TYPES)) {
			return FileType.PACKAGE;
		} else {
			return FileType.OTHER;
		}
		
	}
}
