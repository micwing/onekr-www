package onekr.web.console.card;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardPhotoDto;
import onekr.cardservice.card.intf.CardPhotoFileBiz;
import onekr.cardservice.model.Card;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;
import onekr.identityservice.model.User;
import onekr.web.base.ConsoleBaseController;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/card/momentphoto")
public class CardMomentPhotoController extends ConsoleBaseController {
	
	public static final String CARD_COVER_PHOTO_DESC = "cover";
	public static final String CARD_PEOPLE1_PHOTO_DESC = "people1";
	public static final String CARD_PEOPLE2_PHOTO_DESC = "people2";
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardPhotoFileBiz cardFileBiz;
	
	@RequestMapping(value = "/cardphoto/{cardId}", method = RequestMethod.GET)
	public ModelAndView momentphoto(ModelMap model, @PathVariable("cardId") Long cardId,
			@RequestParam(value = "msg",required=false) String msg) {
		ModelAndView mav = new ModelAndView("card:card-moment-photo", model);
		Card card = cardBiz.findById(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		List<CardPhotoDto> list = cardFileBiz.listMomentPhoto(cardId);
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
	
}
