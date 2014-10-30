package onekr.commonservice.filestore.intf;

import org.springframework.web.multipart.MultipartFile;

public interface FileBiz {
	
	public static final String fileBaseUrl = "/attached";
	
	public static final String fileUploadDirCard = "card/";
	
	public static final String fileUploadDirMore = "more/";

	String saveUrlFile(String url);
	
	String saveMultipartFile(MultipartFile file) throws Exception;
	
	String saveMultipartFile(MultipartFile file, String dir) throws Exception;
	
	void deleteFile(String filePath);
}
