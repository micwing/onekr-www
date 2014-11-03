package onekr.web.console.card;

import java.util.List;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardFileBiz;
import onekr.cardservice.card.intf.CardPhotoDto;
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
	
	public static final String CARD_COVER_PHOTO_DESC = "cover";
	public static final String CARD_PEOPLE1_PHOTO_DESC = "people1";
	public static final String CARD_PEOPLE2_PHOTO_DESC = "people2";
	
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
		List<CardPhotoDto> list = cardFileBiz.listCardPhoto(cardId);
		mav.addObject("photos", list);
		mav.addObject("card", card);
		return mav;
	}
	
	@RequestMapping(value="/doUploadFile",method=RequestMethod.POST)
    public String doUploadFile(
    		@RequestParam("file") CommonsMultipartFile[] mfiles, 
    		@RequestParam("cardId") Long cardId
//    		,
//    		@RequestParam("width") String width,
//    		@RequestParam("height") String height
    		) {       
		User user = (User) getCurrentUser();
		FileStore[] thumbs = cardFileBiz.saveCardPhotoThumb(cardId, mfiles, user.getId());
		cardFileBiz.saveCardPhoto(cardId, mfiles,thumbs, user.getId());
        return "redirect:/card/photo/cardphoto/"+cardId;
    }
	
	@RequestMapping(value="/doUseWay",method=RequestMethod.GET)
    public String doUseCover(@RequestParam("cardId") Long cardId, 
    		@RequestParam("desc") String desc, @RequestParam("fileStoreId") Long fileStoreId) {       
		User user = (User) getCurrentUser();
		if (desc.equals(CARD_COVER_PHOTO_DESC)) {			
			cardFileBiz.usePhotoAsCover(cardId, fileStoreId, user.getId());
		} else if (desc.equals(CARD_PEOPLE1_PHOTO_DESC)) {
			cardFileBiz.usePhotoAsPeople1(cardId, fileStoreId, user.getId());
		} else if (desc.equals(CARD_PEOPLE2_PHOTO_DESC)) {
			cardFileBiz.usePhotoAsPeople2(cardId, fileStoreId, user.getId());
		}
        return "redirect:/card/photo/cardphoto/"+cardId;
    }
	
	@RequestMapping(value="/doCancelWay",method=RequestMethod.GET)
    public String doCancelWay(@RequestParam("cardId") Long cardId, 
    		@RequestParam("desc") String desc, @RequestParam("fileStoreId") Long fileStoreId) {       
		User user = (User) getCurrentUser();
		if (desc.equals(CARD_COVER_PHOTO_DESC)) {			
			cardFileBiz.cancelPhotoAsCover(cardId, fileStoreId, user.getId());
		} else if (desc.equals(CARD_PEOPLE1_PHOTO_DESC)) {
			cardFileBiz.cancelPhotoAsPeople1(cardId, fileStoreId, user.getId());
		} else if (desc.equals(CARD_PEOPLE2_PHOTO_DESC)) {
			cardFileBiz.cancelPhotoAsPeople2(cardId, fileStoreId, user.getId());
		}
        return "redirect:/card/photo/cardphoto/"+cardId;
    }
	
	@RequestMapping(value="/doDelete",method=RequestMethod.GET)
    public String doDelete(
    		@RequestParam("cardId") Long cardId,
    		@RequestParam("fileStoreId") Long fileStoreId) { 
		User user = (User) getCurrentUser();
		cardFileBiz.deleteCardPhoto(fileStoreId, user.getId());
        return "redirect:/card/photo/cardphoto/"+cardId;
    }
	
}
