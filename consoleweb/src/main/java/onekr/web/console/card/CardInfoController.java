package onekr.web.console.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardMakeCodeBiz;
import onekr.cardservice.card.intf.CardPhotoDto;
import onekr.cardservice.card.intf.CardPhotoFileBiz;
import onekr.cardservice.model.Card;
import onekr.cardservice.model.CardMakeCode;
import onekr.cardservice.model.CardType;
import onekr.commonservice.model.Status;
import onekr.framework.contstants.Constants;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;
import onekr.identityservice.model.Group;
import onekr.identityservice.model.User;
import onekr.web.base.ConsoleBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/card/info")
public class CardInfoController extends ConsoleBaseController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardMakeCodeBiz cardMakeCodeBiz;
	
	@Autowired
	private CardPhotoFileBiz cardPhotoFileBiz;
	
	@RequestMapping(value = "/makecodeinput", method = RequestMethod.GET)
	public ModelAndView makecodeinput(@RequestParam(value="makecode", required=false) String makecode) {
		ModelAndView mav = new ModelAndView("card:card-makecodeinput");
		mav.addObject("makecode", makecode);
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam(value = "makecode") String makecode) {
		ModelAndView mav = new ModelAndView("card:card-info");
		CardMakeCode code = cardMakeCodeBiz.findNoUseCode(makecode);
		if (code == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "制作码不存在或已失效！");
		mav.addObject("makecode", makecode);
		return mav;
	}
	
	@RequestMapping(value = "/modify/{cardId}", method = RequestMethod.GET)
	public ModelAndView modify(ModelMap model, @PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("card:card-info", model);
		mav.addObject("card", cardBiz.findById(cardId));
		return mav;
	}
	
	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
	public ModelAndView doSave(Card card, String makecode) {
		User user = (User) getCurrentUser();
		boolean createFlag = false;
		if (card.getId() == null) {
			CardMakeCode code = cardMakeCodeBiz.findNoUseCode(makecode);
			if (code == null)
				throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "制作码不存在或已失效！");
			createFlag = true;
		}
		card = cardBiz.saveCard(card, user.getId());
		if (createFlag) {
			cardMakeCodeBiz.useCode(makecode, card.getId());
		}
		return modify(new ModelMap("result",new Result("保存成功！")),card.getId());
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(CardType cardType, @PageableDefaults(value = 20) Pageable pageable) {
		if (cardType == null)
			cardType = CardType.WED_CARD;
		if (pageable == null)
			pageable = new PageRequest(0, Constants.PAGE_DEFAULT_SIZE);
		User user = getCurrentUser();
		Page<Card> page = null;
		if (user.getGroup().equals(Group.ADMINISTRATOR)) {
			page = cardBiz.listCard(cardType, pageable);
		} else {
			page = cardBiz.listCard(cardType, user.getId(), pageable);
		}
		Map<Long, CardPhotoDto> cardPhotoMap = new HashMap<Long, CardPhotoDto>();
		if (page != null && !CollectionUtils.isEmpty(page.getContent())) {
			List<Card> content = page.getContent();
			List<Long> ids = new ArrayList<Long>();
			for (Card c : content) {
				ids.add(c.getId());
			}
			cardPhotoMap = cardPhotoFileBiz.getCardPhotoCoverMap(ids);
		}
		ModelAndView mav = new ModelAndView("card:card-list");
		mav.addObject("page", page);
		mav.addObject("cardPhotoMap", cardPhotoMap);
		mav.addObject("cardType", cardType);
		return mav;
	}
	
}
