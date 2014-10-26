package onekr.framework.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

public abstract class FileUploadSupportController extends BaseController {

	protected void doUploadFile(
			HttpServletRequest  request,  
            HttpServletResponse response) throws Exception{
		/**
		 * KindEditor JSP
		 * 
		 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。
		 * 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
		 * 
		 */

		//文件保存目录路径
		String savePath = getFileUploadDir();
		
		//文件保存目录URL
		String saveUrl  = request.getContextPath() + getFileManagerUrl();
		
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = getExtMap();

		//最大文件大小
		long maxSize = getMaxSize();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		if(!ServletFileUpload.isMultipartContent(request)){
			writeResponse(response, getError("请选择文件。"));
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			writeResponse(response, getError("上传目录不存在。"));
			return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			writeResponse(response, getError("上传目录没有写权限。"));
			return;
		}

		//目录名
		String dirName = getDirName(request);
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			writeResponse(response,  getError("目录名不正确。"));
			return;
		}
		//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		
		String ymd = getFoldName(request);
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			long fileSize = item.getSize();
			if (!item.isFormField()) {
				//检查文件大小
				if(item.getSize() > maxSize){
					writeResponse(response, getError("上传文件大小超过限制。"));
					return;
				}
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
					writeResponse(response, getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					return;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				try{
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				}catch(Exception e){
					writeResponse(response, getError("上传文件失败。"));
					return;
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				writeResponse(response, obj.toJSONString());
				return;
			}
		}
	}
	
	protected void fileManager(
			HttpServletRequest request,
			HttpServletResponse response) {
		/**
		 * KindEditor JSP
		 *
		 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。
		 * 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
		 *
		 */

		//根目录路径，可以指定绝对路径，比如 /var/www/attached/
		String rootPath = getFileUploadDir();
		
		//根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String rootUrl  = request.getContextPath() + getFileManagerUrl();
		
		//图片扩展名
		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

		String dirName = request.getParameter("dir");
		if (dirName != null) {
//			if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
//				writeResponse(response, "Invalid Directory name.");
//				return;
//			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		//根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		//排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

		//不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			writeResponse(response, "Access is not allowed.");
			return;
		}
		//最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			writeResponse(response, "Parameter is not valid.");
			return;
		}
		//目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if(!currentPathFile.isDirectory()){
			writeResponse(response, "Directory does not exist.");
			return;
		}

		//遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if(currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if(file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if(file.isFile()){
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		response.setContentType("application/json; charset=UTF-8");
		writeResponse(response,  result.toJSONString());
	}
	
	protected String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
	protected void writeResponse(HttpServletResponse response, String str) {
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.println(str);
			writer.flush();
		} catch (IOException e) {
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	/**
	 * 上传文件类型限制
	 * @return
	 */
	public HashMap<String, String> getExtMap() {
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		return extMap;
	}
	
	/**
	 * 上传文件大小限制
	 * @return
	 */
	public long getMaxSize() {
		return 1000000;
	}
	
	/**
	 * 文件访问的根url
	 * @return
	 */
	public abstract String getFileManagerUrl();
	
	/**
	 * 文件上传的根目录（第一级目录）
	 * @return
	 */
	public abstract String getFileUploadDir();
	
	/**
	 * 文件上传的类型目录（第二级目录）
	 * @param request
	 * @return
	 */
	public String getDirName(HttpServletRequest request) {
		return request.getParameter("dir");
	}
	
	/**
	 * 文件上传的拥有者目录（第三级目录）
	 * @param request
	 * @return
	 */
	public String getFoldName(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		return ymd;
	}
	
}

class NameComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable)a;
		Hashtable hashB = (Hashtable)b;
		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
		}
	}
}
class SizeComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable)a;
		Hashtable hashB = (Hashtable)b;
		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
			return 1;
		} else {
			if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
				return 1;
			} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
class TypeComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable)a;
		Hashtable hashB = (Hashtable)b;
		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
		}
	}
}