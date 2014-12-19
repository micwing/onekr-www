package onekr.commonservice.filestore.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
	
	@Override
	public File getFile(String relativeDirFile) {
		File file = new File(fileUploadDir+relativeDirFile);
		return file;
	}
	
	@Override
	public void rotate(File file, Double angle, Integer[] color)
			throws Exception {
		BufferedImage image = ImageUtil.readImage(new FileInputStream(file));
		BufferedImage rotateImage = ImageUtil.rotate(image, angle, color);
		ImageUtil.writeImage(rotateImage, FileUtil.getFileSuffix(file), file);
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
		
		BufferedImage thumb = ImageUtil.zoom(squareBufferedImage, squareThumbMaxHeight, squareThumbMaxHeight);
		
		if ( StringUtils.isEmpty(dirName) )
			throw new AppException(ErrorCode.ILLEGAL_PARAM, "dirName不能为空");
		
		File uploadPathFile = new File(fileUploadDir+dirName);
        if (!uploadPathFile.exists()) {
        	uploadPathFile.mkdirs();
        }
        
        String suffix = FileUtil.getPathOrUrlSuffix(file.getOriginalFilename());
        String newFileName = FileUtil.createNewFileName(suffix);
        String fileDir = uploadPathFile.getPath()+File.separator +newFileName;
        
        File newFile = new File(fileDir);
        ImageUtil.writeImage(thumb, suffix, newFile);
        
        return newFileName;
	}
	
	@Override
	public String saveMultipartImage(MultipartFile file, int max,
			String dirName) throws Exception {
		BufferedImage bufferedImage = ImageUtil.readImage(file.getInputStream());
		
		int orgheight = ImageUtil.getHeight(bufferedImage);
		int orgwidth = ImageUtil.getWidth(bufferedImage);
		
		int height = 0;
		int width = 0;
		BufferedImage target = null;
		if (orgwidth > orgheight) {
			if (orgwidth > max) {
				width = max;
				height = (int) ( ((double)orgheight) *  (  ((double)max) / ((double)orgwidth) ) );
				target = ImageUtil.zoom(bufferedImage, width, height);
			} else {
				width = orgwidth;
				height = orgheight;
				target = bufferedImage;
			}
		} else {
			if (orgheight > max) {
				height = max;
				width = (int) ( ((double)orgwidth) *  ( ((double)max) /  ((double)orgheight) ) );
				target = ImageUtil.zoom(bufferedImage, width, height);
			} else {
				width = orgwidth;
				height = orgheight;
				target = bufferedImage;
			}
		}
		
		if ( StringUtils.isEmpty(dirName) )
			throw new AppException(ErrorCode.ILLEGAL_PARAM, "dirName不能为空");
		
		File uploadPathFile = new File(fileUploadDir+dirName);
        if (!uploadPathFile.exists()) {
        	uploadPathFile.mkdirs();
        }
        
        String suffix = FileUtil.getPathOrUrlSuffix(file.getOriginalFilename());
        String newFileName = FileUtil.createNewFileName(suffix);
        String fileDir = uploadPathFile.getPath()+File.separator +newFileName;
        
        File newFile = new File(fileDir);
        ImageUtil.writeImage(target, suffix, newFile);
        
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
		
		File file = new File(fileUploadDir+filePath);
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
