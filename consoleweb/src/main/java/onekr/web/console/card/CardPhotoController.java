package onekr.web.console.card;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardPhotoFileBiz;
import onekr.cardservice.card.intf.CardPhotoDto;
import onekr.cardservice.model.Card;
import onekr.commonservice.model.FileStore;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;
import onekr.identityservice.model.User;
import onekr.web.console.ConsoleBaseController;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/card/photo")
public class CardPhotoController extends ConsoleBaseController {
	
	public static final String CARD_COVER_PHOTO_DESC = "cover";
	public static final String CARD_PEOPLE1_PHOTO_DESC = "people1";
	public static final String CARD_PEOPLE2_PHOTO_DESC = "people2";
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardPhotoFileBiz cardFileBiz;
	
	@RequestMapping(value = "/cardphoto/{cardId}", method = RequestMethod.GET)
	public ModelAndView cardphoto(ModelMap model, @PathVariable("cardId") Long cardId,
			@RequestParam(value = "msg",required=false) String msg) {
		ModelAndView mav = new ModelAndView("card:card-photo", model);
		Card card = cardBiz.findById(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		List<CardPhotoDto> list = cardFileBiz.listCardPhoto(cardId);
		mav.addObject("photos", list);
		mav.addObject("card", card);
		if (!StringUtils.isEmpty(msg)) {			
			try {
				mav.addObject("result", new Result(URLDecoder.decode(msg, "utf8")));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return mav;
	}
	
	@RequestMapping(value="/doUploadFile",method=RequestMethod.POST)
    public ModelAndView doUploadFile(
    		@RequestParam("file") CommonsMultipartFile[] mfiles, 
    		@RequestParam("cardId") Long cardId) {
		User user = (User) getCurrentUser();
		FileStore[] thumbs = cardFileBiz.saveCardPhotoThumb(cardId, mfiles, user.getId());
		cardFileBiz.saveCardPhoto(cardId, mfiles,thumbs, user.getId());
        return cardphoto(new ModelMap("result", new Result("上传成功！")), cardId, null);
    }
	
	@RequestMapping(value="/doUseWay",method=RequestMethod.POST)
	@ResponseBody
    public Result doUseWay(@RequestParam("cardId") Long cardId, 
    		@RequestParam("desc") String desc, @RequestParam("fileStoreId") Long fileStoreId) {       
		User user = (User) getCurrentUser();
		if (desc.equals(CARD_COVER_PHOTO_DESC)) {			
			cardFileBiz.usePhotoAsCover(cardId, fileStoreId, user.getId());
		} else if (desc.equals(CARD_PEOPLE1_PHOTO_DESC)) {
			cardFileBiz.usePhotoAsPeople1(cardId, fileStoreId, user.getId());
		} else if (desc.equals(CARD_PEOPLE2_PHOTO_DESC)) {
			cardFileBiz.usePhotoAsPeople2(cardId, fileStoreId, user.getId());
		}
		return new Result("设置成功！");
    }
	
	@RequestMapping(value="/doCancelWay",method=RequestMethod.POST)
	@ResponseBody
    public Result doCancelWay(@RequestParam("cardId") Long cardId, 
    		@RequestParam("desc") String desc, @RequestParam("fileStoreId") Long fileStoreId) {       
		User user = (User) getCurrentUser();
		if (desc.equals(CARD_COVER_PHOTO_DESC)) {			
			cardFileBiz.cancelPhotoAsCover(cardId, fileStoreId, user.getId());
		} else if (desc.equals(CARD_PEOPLE1_PHOTO_DESC)) {
			cardFileBiz.cancelPhotoAsPeople1(cardId, fileStoreId, user.getId());
		} else if (desc.equals(CARD_PEOPLE2_PHOTO_DESC)) {
			cardFileBiz.cancelPhotoAsPeople2(cardId, fileStoreId, user.getId());
		}
		return new Result("取消成功！");
    }
	
	@RequestMapping(value="/doDelete",method=RequestMethod.POST)
	@ResponseBody
    public Result doDelete(
    		@RequestParam("cardId") Long cardId,
    		@RequestParam("fileStoreId") Long fileStoreId) { 
		User user = (User) getCurrentUser();
		cardFileBiz.deleteCardPhoto(fileStoreId, user.getId());
		return new Result("删除成功！");
    }
	
}
