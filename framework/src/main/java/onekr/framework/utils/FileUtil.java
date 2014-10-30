package onekr.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class FileUtil {
	
	public static String[] IMAGE_FILE_TYPES = {"png", "jpg", "jpeg", "jpe", "gif", "bmp", "ico", "pic", "tif", "tiff", "jfif"};
	public static String[] AUDIO_FILE_TYPES = {"wav", "mp3", "aif", "midi", "wma", "ram", "au"};
	public static String[] VIDEO_FILE_TYPES = {"rm", "rmvb", "wmv", "avi", "flv", "swf", "mp4", "3gp", "mpeg", "mpg", "mov", "asf", "dat", "vob"};
	public static String[] DOC_FILE_TYPES = {"txt", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "docx", "pdf", "wps", "rtf"};
	public static String[] PACKAGE_FILE_TYPES = {"zip", "rar", "7z", "cab", "iso", "arj", "gz", "z"};
	
	/**
	 * 复制指定URL地址的文件到指定位置File
	 * @param url
	 * @param dest
	 * @throws Exception
	 */
	public static void copyURLToFile(String url, File dest) throws Exception {
		String fname = getPathOrUrlFileNameWithSuffix(url);
		String fparent = getParentOfPathOrUrl(url);
		URLConnection httpConnection = new URL(fparent+"/"+URLEncoder.encode(fname,"UTF-8")).openConnection();
		httpConnection.setRequestProperty("User-Agent",
			"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		
		InputStream input = httpConnection.getInputStream();
		try {
			FileOutputStream output = FileUtils.openOutputStream(dest);
			try {
				IOUtils.copy(input, output);
			} finally {
				IOUtils.closeQuietly(output);
			}
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	/**
	 * 获得上级URL或者Path
	 * @param str
	 * @return
	 */
	public static String getParentOfPathOrUrl(String str) {
		if (StringUtils.isEmpty(str))
			return "";
		int i1 = str.lastIndexOf("\\");
		int i2 = str.lastIndexOf("/");
		if (i1 > 0) {
			return str.substring(0, i1);
		}
		if (i2 > 0) {
			return str.substring(0, i2);
		}
		return str;
	}
	
	/**
	 * 获取文件名称（带后缀）
	 * @param str
	 * @return
	 */
	public static String getPathOrUrlFileNameWithSuffix(String str) {
		String fileName = org.springframework.util.StringUtils.getFilename(str);
		return fileName == null ? "" : fileName;
	}
	
	/**
	 * 获取后缀
	 * @param str
	 * @return
	 */
	public static String getPathOrUrlSuffix(String str) {
		String suffix = org.springframework.util.StringUtils.getFilenameExtension(str);
		return suffix == null ? "" : suffix;
	}
	
	/**
	 * 获取一个随机文件路径File
	 * @param fileUploadDir
	 * @param suffix
	 * @return
	 */
	public static String getRandomFilePath(String fileUploadDir, String suffix) {
		if (StringUtils.isEmpty(suffix)) {
			suffix = "";
		} else if (!suffix.startsWith(".")) {			
			suffix = "." + suffix;
		}
		fileUploadDir = new File(fileUploadDir).getPath();
		Date now = new Date();
		Random r = new Random();
		return fileUploadDir + File.separator 
				+ DateUtil.getShortStrDate(now) + File.separator 
				+ DateUtil.getShortStrDateTime(now) + "_" + r.nextInt(1000)
				+ suffix;
	}
	
	/**
	 * 在指定目录文件所在目录下生成一个与其他文件不重名的目录文件
	 * @param fileDir
	 * @return
	 */
	public static String noDuplicatedFilePath(String filePath) {
		File tmp = new File(filePath);
		filePath = tmp.getPath();
		String parentDir = filePath.substring(0,filePath.lastIndexOf(tmp.getName()));
		
        String original = FileUtil.getFileNameNoSuffix(tmp);
        String suffix = FileUtil.getFileSuffix(tmp);
        int num = 0;
        while (tmp.exists()) {
        	num++;
        	filePath = parentDir+ original + "["+ num +"]." + suffix;
        	tmp = new File(filePath);
        }
        return filePath;
	}
	
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	public static String createNewFileName(String suffix) {
		if (!suffix.startsWith(".")) {
			suffix = "." + suffix;
		}
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + suffix;
		return newFileName;
	}
	
	/**
	 * 文件地址转换成url
	 * @param f
	 * @return
	 */
	public static String cvtUrl(File f, String fileUploadDir, String fileBaseUrl) {
		fileUploadDir = new File(fileUploadDir).getPath();
		
		String fileAbsPath = f.getAbsolutePath();
		String fileDir = fileAbsPath.substring(fileAbsPath.indexOf(fileUploadDir) + fileUploadDir.length());
		String url = (fileBaseUrl+"/"+fileDir.replace("\\", "/")).replace("//", "/");
		return url;
	}
	
	/**
	 * 获取文件后缀
	 * @param f
	 * @return
	 */
	public static String getFileSuffix(File f) {
		return getPathOrUrlSuffix(f.getName());
	}
	
	/**
	 * 获取文件名，不包含后缀
	 * @param f
	 * @return
	 */
	public static String getFileNameNoSuffix(File f) {
		String fileName = f.getName();
		String suff = getPathOrUrlSuffix(fileName);
		if (StringUtils.isEmpty(suff))
			return fileName;
		int index = fileName.lastIndexOf(suff);
		return fileName.substring(0, index-1);
	}
	
	/**
	 * 根据后缀名判断是否是图片
	 * @param f
	 * @return
	 */
	public static boolean isImage(File f) {
		return isFileType(f, IMAGE_FILE_TYPES);
	}
	
	/**
	 * 根据后缀名判断是否是含有指定的后缀
	 * @param f
	 * @param fileTypes FileUtil.IMAGE_FILE_TYPES/AUDIO_FILE_TYPES/...
	 * @return
	 */
	public static boolean isFileType(File f, String[] fileTypes) {
		String extention = getFileSuffix(f);
		return ArrayUtils.contains(fileTypes, extention.toLowerCase());
	}
	
	/**
	 * 根据后缀名判断是否是含有指定的后缀
	 * @param f
	 * @param fileTypes FileUtil.IMAGE_FILE_TYPES/AUDIO_FILE_TYPES/...
	 * @return
	 */
	public static boolean isFileType(String filename, String[] fileTypes) {
		String extention = getPathOrUrlSuffix(filename);
		return ArrayUtils.contains(fileTypes, extention.toLowerCase());
	}
	
	/**
	 * 获取文件大小
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSize(File f) throws Exception {// 取得文件大小
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			s = -1;
		}
		return s;
	}
	
	/**
	 * 获取文件大小，描述性字符串
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static String getFileSizeStr(File f) throws Exception {
		return formetFileSize(getFileSize(f));
	}

	/**
	 * 判断路径下文件个数
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	public static long getFolderCount(File dir) throws Exception {
		File flist[] = dir.listFiles();
		return flist.length;
	}
	
	/**
	 * 文件大小转换成描述性字符串
	 * @param fileS
	 * @return
	 */
	public static String formetFileSize(long fileS) {
		if (fileS < 0) {
			return "error";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		String fileSizeString = "";
		if (fileS < 1) {
			fileSizeString = "0";
		} else if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}
}
