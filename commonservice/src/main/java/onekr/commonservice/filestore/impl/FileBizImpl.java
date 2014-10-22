package onekr.commonservice.filestore.impl;

import java.io.File;
import java.io.FileOutputStream;

import onekr.commonservice.filestore.intf.FileBiz;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.utils.FileUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileBizImpl implements FileBiz {
	
	@Value("#{systemConfig['file.fileUploadDir']}")
	private String fileUploadDir;
	
	@Value("#{systemConfig['file.fileUploadDirMore']}")
	private String fileUploadDirMore;
	
	@Value("#{systemConfig['file.fileManagerUrl']}")
	private String fileManagerUrl;
	
	@Override
	public String saveUrlFile(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String saveMultipartFile(MultipartFile file) throws Exception {
		File uploadPathFile = new File(fileUploadDir+fileUploadDirMore);
        if (!uploadPathFile.exists()) {
        	uploadPathFile.mkdir();
        }
        
        String fileDir = uploadPathFile.getPath()+File.separator+file.getOriginalFilename();
        fileDir = FileUtil.noDuplicatedFilePath(fileDir);
        
        FileOutputStream fileOS=new FileOutputStream(fileDir);
        fileOS.write(file.getBytes());
        fileOS.close();
        
        return uploadPathFile.getPath();
	}
	
	@Override
	public void deleteFile(String filePath) {
		File file = new File(filePath);
		if(file.isFile()){
			file.delete();
		} else {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {				
				throw new AppException(ErrorCode.PATH_HAVE_FILE_CANNOT_DELETE);
			}
			file.delete();
		}
	}
}
