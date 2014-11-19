package onekr.web.card;

import java.util.List;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardCommentBiz;
import onekr.cardservice.card.intf.CardFileBiz;
import onekr.cardservice.card.intf.CardPhotoDto;
import onekr.cardservice.model.Card;
import onekr.commonservice.model.Comment;
import onekr.commonservice.model.FileStore;
import onekr.framework.result.Result;
import onekr.identityservice.user.intf.UserBiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/card")
public class CardController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardFileBiz cardFileBiz;
	
	@Autowired
	private CardCommentBiz cardCommentBiz;
	
	@Autowired
	private UserBiz userBiz;
	
	@RequestMapping(value = "/cover/{cardId}", method = RequestMethod.GET)
	public ModelAndView cover(@PathVariable("cardId") Long cardId) {
		Card card = cardBiz.findById(cardId);
		CardPhotoDto coverPhoto = cardFileBiz.getCardPhotoCover(cardId);
		ModelAndView mav = new ModelAndView("single:card/"+card.getTempletId().substring(0,2)+"/cover");
		mav.addObject("card", card);
		mav.addObject("coverPhoto", coverPhoto);
		
		return mav;
	}
	
	@RequestMapping(value = "/main/{cardId}", method = RequestMethod.GET)
	public ModelAndView main(@PathVariable("cardId") Long cardId) {
		Card card = cardBiz.findById(cardId);
		CardPhotoDto coverPhoto = cardFileBiz.getCardPhotoCover(cardId);
		CardPhotoDto people1Photo = cardFileBiz.getCardPhotoPeople1(cardId);
		CardPhotoDto people2Photo = cardFileBiz.getCardPhotoPeople2(cardId);
		List<CardPhotoDto> photos = cardFileBiz.listCardPhoto(cardId);
		List<CardPhotoDto> moments = cardFileBiz.listMomentPhoto(cardId);
		FileStore music = cardFileBiz.getUseMusic(cardId);
		
		ModelAndView mav = new ModelAndView("single:card/"+card.getTempletId().substring(0,2)+"/main");
		mav.addObject("card", card);
		mav.addObject("coverPhoto", coverPhoto);
		mav.addObject("people1Photo", people1Photo);
		mav.addObject("people2Photo", people2Photo);
		mav.addObject("photos", photos);
		mav.addObject("music", music);
		mav.addObject("moments", moments);
		return mav;
	}
	
	@RequestMapping(value="/listComments",method=RequestMethod.POST)
	@ResponseBody
	public Result listComments(@RequestParam("cardId") Long cardId) {
		List<Comment> list = cardCommentBiz.listAll(cardId);
		Result result = new Result();
		result.setValue(list);
		return result;
	}
	
	@RequestMapping(value="/doUploadMemontPhoto",method=RequestMethod.POST)
	public String doUploadMemontPhoto(
    		@RequestParam("file") CommonsMultipartFile[] mfiles, 
    		@RequestParam("cardId") Long cardId) {
		Long uid = userBiz.getAnonymousId();
		FileStore[] thumbs = cardFileBiz.saveMomentPhotoThumb(cardId, mfiles, uid);
		cardFileBiz.saveMomentPhoto(cardId, mfiles,thumbs, uid);
        return "redirect:/card/main/"+cardId;
	}
	
	@RequestMapping(value="/doSaveComment",method=RequestMethod.POST)
	@ResponseBody
	public Result doSaveComment(
			@RequestParam("userName") String userName,
			@RequestParam("reply") String reply,
			@RequestParam("content") String content,
			@RequestParam("cardId") Long cardId) {
		Long uid = userBiz.getAnonymousId();
		cardCommentBiz.saveCardComment(cardId, null, uid, userName, content, reply);
		return new Result();
	}
}
