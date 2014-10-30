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
@RequestMapping(value = "/card/map")
public class CardMapController extends BaseController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardFileBiz cardFileBiz;
	
	//http://api.map.baidu.com/staticimage?width=600&height=400&center=120.70021967046,31.30336591322&markers=120.70021967046,31.30336591322&zoom=10&markerStyles=m,,0xff0000
	@RequestMapping(value = "/cardmap/{cardId}", method = RequestMethod.GET)
	public ModelAndView cardmap(@PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("card:card-map");
		mav.addObject("card", cardBiz.findById(cardId));
		mav.addObject("mappic", cardFileBiz.findCardMappic(cardId));
		return mav;
	}
	
	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
	public String doSave(Card card) {
		User user = (User) getCurrentUser();
		card = cardBiz.saveCard(card, user.getId());
		return "redirect:/card/map/cardmap/"+card.getId();
	}
	
	@RequestMapping(value="/doUploadFile",method=RequestMethod.POST)
    public String doUploadFile(
    		@RequestParam("file") CommonsMultipartFile mfile, 
    		@RequestParam("cardId") Long cardId) {
		User user = (User) getCurrentUser();
		cardFileBiz.saveCardMapPic(cardId, mfile, user.getId());
        return "redirect:/card/map/cardmap/"+cardId;
    }
	
	@RequestMapping(value="/doDelete",method=RequestMethod.GET)
    public String doDelete(@RequestParam("cardId") Long cardId,
    		@RequestParam("fileStoreId") Long fileStoreId) { 
		User user = (User) getCurrentUser();
		cardFileBiz.delete(fileStoreId, user.getId());
		return "redirect:/card/map/cardmap/"+cardId;
    }
}
