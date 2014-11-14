package onekr.web.identity.card;

import java.util.List;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardFileBiz;
import onekr.cardservice.model.Card;
import onekr.commonservice.model.FileStore;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.identityservice.model.User;
import onekr.web.identity.IdentityBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/card/music")
public class CardMusicController extends IdentityBaseController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardFileBiz cardFileBiz;
	
	@RequestMapping(value = "/cardmusic/{cardId}", method = RequestMethod.GET)
	public ModelAndView cardmusic(@PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("card:card-music");
		Card card = cardBiz.findById(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		List<FileStore> list = cardFileBiz.listCardMusic(cardId);
		mav.addObject("list", list);
		mav.addObject("card", card);
		return mav;
	}
	
	@RequestMapping(value="/doUploadFile",method=RequestMethod.POST)
    public String doUploadFile(
    		@RequestParam("file") MultipartFile mfiles, 
    		@RequestParam("cardId") Long cardId) {
		User user = (User) getCurrentUser();
		cardFileBiz.saveCardMusic(cardId, mfiles, user.getId());
        return "redirect:/card/music/cardmusic/"+cardId;
    }
	
	@RequestMapping(value="/doSetMusic",method=RequestMethod.GET)
    public String doSetMusic(
    		@RequestParam("fileStoreId") Long fileStoreId,
    		@RequestParam("cardId") Long cardId) {
		User user = (User) getCurrentUser();
		cardFileBiz.useMusic(cardId, fileStoreId, user.getId());
        return "redirect:/card/music/cardmusic/"+cardId;
    }
	
	@RequestMapping(value="/doDeleteMusic",method=RequestMethod.GET)
    public String doDeleteMusic(
    		@RequestParam("fileStoreId") Long fileStoreId,
    		@RequestParam("cardId") Long cardId) {
		User user = (User) getCurrentUser();
		cardFileBiz.deleteCardMusic(cardId, fileStoreId, user.getId());
        return "redirect:/card/music/cardmusic/"+cardId;
    }
}
