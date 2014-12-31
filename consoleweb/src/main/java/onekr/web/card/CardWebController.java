package onekr.web.card;

import java.util.List;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardCommentBiz;
import onekr.cardservice.card.intf.CardMusicFileBiz;
import onekr.cardservice.card.intf.CardPhotoDto;
import onekr.cardservice.card.intf.CardPhotoFileBiz;
import onekr.cardservice.model.Card;
import onekr.commonservice.model.Comment;
import onekr.commonservice.model.FileStore;
import onekr.commonservice.model.Status;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
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

/**
 * 手机端访问的请柬页面
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/card")
public class CardWebController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardPhotoFileBiz cardPhotoFileBiz;
	
	@Autowired
	private CardMusicFileBiz cardMusicFileBiz;
	
	@Autowired
	private CardCommentBiz cardCommentBiz;
	
	@Autowired
	private UserBiz userBiz;
	
	/**
	 * 手机查看模板列表
	 * @return
	 */
	@RequestMapping(value = "/templatelist", method = RequestMethod.GET)
	public ModelAndView templatelist() {
		ModelAndView mav = new ModelAndView("single:cardtpl/templatelist");
		return mav;
	}
	
	/**
	 * 请柬封面
	 * @param cardId
	 * @return
	 */
	@RequestMapping(value = "/cover/{cardId}", method = RequestMethod.GET)
	public ModelAndView cover(@PathVariable("cardId") Long cardId) {
		Card card = cardBiz.findById(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "未找到对应的请柬");
		if (card.getStatus().equals(Status.PAUSED))
			throw new AppException(ErrorCode.ILLEGAL_STATE, "该请柬不可见");
		CardPhotoDto coverPhoto = cardPhotoFileBiz.getCardPhotoCover(cardId);
		ModelAndView mav = new ModelAndView("single:cardtpl/"+card.getTempletId().substring(0,3)+"/cover");
		mav.addObject("card", card);
		mav.addObject("coverPhoto", coverPhoto);
		
		return mav;
	}
	
	/**
	 * 请柬主体
	 * @param cardId
	 * @return
	 */
	@RequestMapping(value = "/main/{cardId}", method = RequestMethod.GET)
	public ModelAndView main(@PathVariable("cardId") Long cardId) {
		Card card = cardBiz.findById(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "未找到对应的请柬");
		if (card.getStatus().equals(Status.PAUSED))
			throw new AppException(ErrorCode.ILLEGAL_STATE, "该请柬不可见");
		CardPhotoDto coverPhoto = cardPhotoFileBiz.getCardPhotoCover(cardId);
		CardPhotoDto people1Photo = cardPhotoFileBiz.getCardPhotoPeople1(cardId);
		CardPhotoDto people2Photo = cardPhotoFileBiz.getCardPhotoPeople2(cardId);
		List<CardPhotoDto> photos = cardPhotoFileBiz.listCardPhoto(cardId);
		List<CardPhotoDto> moments = cardPhotoFileBiz.listMomentPhoto(cardId);
		FileStore music = cardMusicFileBiz.getUseMusic(cardId);
		
		ModelAndView mav = new ModelAndView("single:cardtpl/"+card.getTempletId().substring(0,3)+"/main");
		mav.addObject("card", card);
		mav.addObject("coverPhoto", coverPhoto);
		mav.addObject("people1Photo", people1Photo);
		mav.addObject("people2Photo", people2Photo);
		mav.addObject("photos", photos);
		mav.addObject("music", music);
		mav.addObject("moments", moments);
		return mav;
	}
	
	/**
	 * 请柬留言列表
	 * @param cardId
	 * @return
	 */
	@RequestMapping(value="/listComments",method=RequestMethod.POST)
	@ResponseBody
	public Result listComments(@RequestParam("cardId") Long cardId) {
		List<Comment> list = cardCommentBiz.listAll(cardId);
		Result result = new Result();
		result.setValue(list);
		return result;
	}
	
	/**
	 * 上传婚礼现场照片
	 * @param mfiles
	 * @param cardId
	 * @return
	 */
	@RequestMapping(value="/doUploadMemontPhoto",method=RequestMethod.POST)
	public String doUploadMemontPhoto(
    		@RequestParam("file") CommonsMultipartFile[] mfiles, 
    		@RequestParam("cardId") Long cardId) {
		Long uid = userBiz.getAnonymousId();
		FileStore[] thumbs = cardPhotoFileBiz.saveMomentPhotoThumb(cardId, mfiles, uid);
		cardPhotoFileBiz.saveMomentPhoto(cardId, mfiles,thumbs, uid);
        return "redirect:/card/main/"+cardId;
	}
	
	/**
	 * 保存请柬留言
	 * @param userName
	 * @param reply
	 * @param content
	 * @param cardId
	 * @return
	 */
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
