package onekr.web.console.portal;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onekr.commonservice.filestore.intf.FileBiz;
import onekr.framework.controller.FileUploadSupportController;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;
import onekr.framework.utils.FileUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/file")
public class PortalFileController extends FileUploadSupportController {
	@Value("#{systemConfig['file.fileUploadDir']}")
	private String fileUploadDir;
	
	@Autowired
	private FileBiz fileBiz;
	
	/**
     * 手动上传文件
     */
    @RequestMapping(value="/doUploadFile2",method=RequestMethod.POST)
    public String doUploadFile2(@RequestParam("file") CommonsMultipartFile file) throws Exception{       
        String path = fileBiz.saveMultipartFile(file);
        return "redirect:/console/file/list?dir="+FileUtil.getParentOfPathOrUrl(path);
    }
	
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	@ResponseBody
	public Result doDelete(String dir) {
		if (StringUtils.isEmpty(dir)) {
			throw new AppException(ErrorCode.MISS_PARAM);
		}
		fileBiz.deleteFile(dir);
		return new Result();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(String dir) {
		ModelAndView mav = new ModelAndView("portal:file-list");
		if (StringUtils.isEmpty(dir)) {
			dir = fileUploadDir;
		}
		//检查目录
		File uploadDir = new File(dir);
		if(!uploadDir.isDirectory()){
			throw new AppException(ErrorCode.SERVER_ERROR, "上传目录不存在。");
		}
		mav.addObject("file", uploadDir);
		mav.addObject("dir", uploadDir.getPath());
		mav.addObject("pdir", uploadDir.getParentFile().getPath());
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView("portal:file-add");
		return mav;
	}
	
	/**
	 * 通过kindeditor上传文件 
	 */
	@RequestMapping(value = "/doUploadFile", method = RequestMethod.POST)
	public void doUploadFile(
			HttpServletRequest  request,  
            HttpServletResponse response) throws Exception{
		super.doUploadFile(request, response);
	}
	
	@RequestMapping(value = "/fileManager")
	public void fileManager(
			HttpServletRequest request,
			HttpServletResponse response) {
		super.fileManager(request, response);
	}

	
	public String getFileUploadDir() {
		return fileUploadDir;
	}

	public String getFileManagerUrl() {
		return FileBiz.fileBaseUrl;
	}
	
	
	
}

