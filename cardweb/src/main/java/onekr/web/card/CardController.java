package onekr.web.card;

import java.util.List;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardFileBiz;
import onekr.cardservice.model.Card;
import onekr.commonservice.model.FileStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/card")
public class CardController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardFileBiz cardFileBiz;
	
	@RequestMapping(value = "/cover/{cardId}", method = RequestMethod.GET)
	public ModelAndView cover(@PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("single:card/cover");
		Card card = cardBiz.findById(cardId);
		FileStore coverPhoto = cardFileBiz.getCardPhotoByUse(cardId, CardFileBiz.CARD_COVER_PHOTO_DESC);
		mav.addObject("card", card);
		mav.addObject("coverPhoto", coverPhoto);
		
		return mav;
	}
	
	@RequestMapping(value = "/main/{cardId}", method = RequestMethod.GET)
	public ModelAndView main(@PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("single:card/main");
		Card card = cardBiz.findById(cardId);
		FileStore coverPhoto = cardFileBiz.getCardPhotoByUse(cardId, CardFileBiz.CARD_COVER_PHOTO_DESC);
		FileStore people1Photo = cardFileBiz.getCardPhotoByUse(cardId, CardFileBiz.CARD_PEOPLE1_PHOTO_DESC);
		FileStore people2Photo = cardFileBiz.getCardPhotoByUse(cardId, CardFileBiz.CARD_PEOPLE2_PHOTO_DESC);
		List<FileStore> photos = cardFileBiz.listCardPhoto(cardId);
		mav.addObject("card", card);
		mav.addObject("coverPhoto", coverPhoto);
		mav.addObject("people1Photo", people1Photo);
		mav.addObject("people2Photo", people2Photo);
		mav.addObject("photos", photos);
		
		return mav;
	}
	
}
