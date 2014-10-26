package onekr.web.console.card;

import java.util.List;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardFileBiz;
import onekr.cardservice.model.Card;
import onekr.commonservice.model.FileStore;
import onekr.framework.controller.BaseController;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.identityservice.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/card/photo")
public class CardPhotoController extends BaseController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardFileBiz cardFileBiz;
	
	@RequestMapping(value = "/cardphoto/{cardId}", method = RequestMethod.GET)
	public ModelAndView cardphoto(@PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("card:card-photo");
		Card card = cardBiz.findById(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		List<FileStore> list = cardFileBiz.listCardPhoto(cardId);
		mav.addObject("photos", list);
		mav.addObject("card", card);
		return mav;
	}
	
	@RequestMapping(value="/doUploadFile",method=RequestMethod.POST)
    public String doUploadFile2(
    		@RequestParam("file") CommonsMultipartFile[] mfiles, 
    		@RequestParam("cardId") Long cardId,
    		@RequestParam("width") String width,
    		@RequestParam("height") String height) {       
		User user = (User) getCurrentUser();
		cardFileBiz.saveCardPhoto(cardId, mfiles,width,height, user.getId());
        return "redirect:/card/photo/cardphoto/"+cardId;
    }
	
	
//	@RequestMapping(value = "/doCardUploadFile", method = RequestMethod.POST)
//	public void doCardUploadFile(
//			HttpServletRequest  request,  
//            HttpServletResponse response) throws Exception{
//		doUploadFile(request, response);
//	}
//	
//	public HashMap<String, String> getExtMap() {
//		HashMap<String, String> extMap = new HashMap<String, String>();
//		extMap.put("card", "gif,jpg,jpeg,png,bmp,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
//		return extMap;
//	}
//	
//	public String getFileManagerUrl() {
//		return fileManagerUrl;
//	}
//	
//	public String getFileUploadDir() {
//		return fileUploadDir;
//	}
//	
//	public String getDirName(HttpServletRequest request) {
//		return "card";
//	}
//	
//	public String getFoldName(HttpServletRequest request) {
//		String cardId = request.getParameter("cardId");
//		if (StringUtils.isEmpty(cardId))
//			throw new AppException(ErrorCode.MISS_PARAM);
//		return cardId;
//	}
}
