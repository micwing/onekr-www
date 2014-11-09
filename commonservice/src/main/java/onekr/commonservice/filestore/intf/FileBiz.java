package onekr.commonservice.filestore.intf;

import org.springframework.web.multipart.MultipartFile;

public interface FileBiz {
	
	public static final String fileBaseUrl = "attached";
	
	public static final String fileUploadDirCard = "card/";
	
	public static final String fileUploadDirMore = "more/";
	
	String saveUrlFile(String url);
	
	String saveMultipartFile(MultipartFile file) throws Exception;
	
	/**
	 * 
	 * @param file
	 * @param dir 在fileUploadDir下的文件存放目录，不包含fileUploadDir部分，是相对于fileUploadDir的目录。
	 * @return 文件在fileUploadDir下的文件存放路径，不包含fileUploadDir部分，是相对于fileUploadDir的路径。
	 * @throws Exception
	 */
	String saveMultipartFile(MultipartFile file, String dir) throws Exception;
	
	/**
	 * 保存图片
	 * @param file
	 * @param squareThumbMaxHeight
	 * @param dirName
	 * @return
	 * @throws Exception
	 */
	String saveMultipartImage(MultipartFile file, int maxHeight, String dirName) throws Exception;
	
	/**
	 * 保存图片缩略图
	 * @param file
	 * @param squareThumbMaxHeight
	 * @param dirName
	 * @return
	 * @throws Exception
	 */
	String saveMultipartImageThumb(MultipartFile file, int squareThumbMaxHeight, String dirName) throws Exception;
	
	void deleteFile(String filePath);
}
