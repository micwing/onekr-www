package onekr.commonservice.filestore.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import onekr.commonservice.filestore.intf.FileBiz;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.utils.FileUtil;
import onekr.framework.utils.ImageUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileBizImpl implements FileBiz {
	
	@Value("#{systemConfig['file.fileUploadDir']}")
	private String fileUploadDir;
	
	@Override
	public String saveUrlFile(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String saveMultipartImageThumb(MultipartFile file, int squareThumbMaxHeight, String dirName) throws Exception {
		BufferedImage bufferedImage = ImageUtil.readImage(file.getInputStream());
		
		int height = ImageUtil.getHeight(bufferedImage);
		int width = ImageUtil.getWidth(bufferedImage);
		
		//正方形图片
		BufferedImage squareBufferedImage = bufferedImage;
		if (height > width) {
			squareBufferedImage = ImageUtil.cut(bufferedImage, 0, (height - width)/2, width, width);
		} else if (height < width) {
			squareBufferedImage = ImageUtil.cut(bufferedImage, (width - height)/2, 0, height, height);
		}
		
//		BufferedImage thumb = ImageUtil.scaleByWidthAndHeight(squareBufferedImage, squareThumbMaxHeight, squareThumbMaxHeight, false, false);
		BufferedImage thumb = ImageUtil.zoom(squareBufferedImage, squareThumbMaxHeight, squareThumbMaxHeight);
		
//		BufferedImage thumb = ImageUtil.scaleByWidthAndHeight(bufferedImage, thumbWidth, thumbHeight, false, false, suffix);
//		
//		int height = ImageUtil.getHeight(thumb);
//		int width = ImageUtil.getWidth(thumb);
//		
//		if ((thumb.getHeight() > height) || (thumb.getWidth() > width)) {
//			
//			if (height > width) {
//				ImageUtil.cut(thumb, 0, (height - thumbHeight)/2, width, thumbHeight);
//			} else if (height < width) {
//				ImageUtil.cut(thumb, (width - thumbWidth)/2, 0, thumbWidth, height);
//			}
//		} 
		
		
		if ( StringUtils.isEmpty(dirName) )
			throw new AppException(ErrorCode.ILLEGAL_PARAM, "dirName");
		
		File uploadPathFile = new File(fileUploadDir+dirName);
        if (!uploadPathFile.exists()) {
        	uploadPathFile.mkdir();
        }
        
        String suffix = FileUtil.getPathOrUrlSuffix(file.getOriginalFilename());
        String newFileName = FileUtil.createNewFileName(suffix);
        String fileDir = uploadPathFile.getPath()+File.separator +newFileName;
        
        File newFile = new File(fileDir);
        ImageUtil.writeImage(thumb, suffix, newFile);
        
        return newFileName;
	}
	
	@Override
	public String saveMultipartFile(MultipartFile file) throws Exception {
        return saveMultipartFile(file, FileBiz.fileUploadDirMore);
	}
	
	@Override
	public String saveMultipartFile(MultipartFile file, String dirName) throws Exception {
		if ( StringUtils.isEmpty(dirName) ) {
			dirName = FileBiz.fileUploadDirMore;
		}
		File uploadPathFile = new File(fileUploadDir+dirName);
        if (!uploadPathFile.exists()) {
        	uploadPathFile.mkdir();
        }
        
//        String fileDir = uploadPathFile.getPath()+File.separator+file.getOriginalFilename();
//        fileDir = FileUtil.noDuplicatedFilePath(fileDir);
        String fileName = FileUtil.createNewFileName(FileUtil.getPathOrUrlSuffix(file.getOriginalFilename()));
        String fileDir = uploadPathFile.getPath()+File.separator +fileName;
        
        FileOutputStream fileOS=new FileOutputStream(fileDir);
        fileOS.write(file.getBytes());
        fileOS.close();
        
        return fileName;
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
