package onekr.biz.sys.intf;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileBiz {

	String saveUrlFile(String url);
	
	String saveMultipartFile(MultipartFile file) throws Exception;
	
	void deleteFile(String filePath);
}
