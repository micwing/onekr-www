package onekr.web.console.system;

import java.util.List;

import onekr.cardservice.card.intf.CardMusicFileBiz;
import onekr.commonservice.model.FileStore;
import onekr.framework.result.Result;
import onekr.identityservice.model.User;
import onekr.web.console.ConsoleBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/system/music")
public class SystemMusicController extends ConsoleBaseController {
	
	@Autowired
	private CardMusicFileBiz cardMusicFileBiz;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelMap model) {
		ModelAndView mav = new ModelAndView("system:music",model);
		List<FileStore> list = cardMusicFileBiz.listSysteMusic();
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value="/doUploadSystemMusic",method=RequestMethod.POST)
    public ModelAndView doUploadSystemMusic(
    		@RequestParam("file") MultipartFile mfiles) {
		User user = (User) getCurrentUser();
		cardMusicFileBiz.saveSystemMusic(mfiles, user.getId());
		return list(new ModelMap("result", new Result("上传成功！")));
    }
	
	@RequestMapping(value="/doDeleteMusic",method=RequestMethod.GET)
    public ModelAndView doDeleteMusic(
    		@RequestParam("fileStoreId") Long fileStoreId) {
		User user = (User) getCurrentUser();
		cardMusicFileBiz.deleteSystemMusic(fileStoreId, user.getId());
		return list(new ModelMap("result", new Result("删除成功！")));
    }
}

