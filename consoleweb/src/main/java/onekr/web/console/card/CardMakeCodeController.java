package onekr.web.console.card;

import java.util.Date;

import onekr.cardservice.card.intf.CardMakeCodeBiz;
import onekr.cardservice.model.CardMakeCode;
import onekr.framework.result.Result;
import onekr.identityservice.model.User;
import onekr.web.console.ConsoleBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/card/makecode")
public class CardMakeCodeController extends ConsoleBaseController {
	
	@Autowired
	private CardMakeCodeBiz cardMakeCodeBiz;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "start", required = false) Date start,
			@RequestParam(value = "end", required = false) Date end, 
			Pageable pageable) {
		ModelAndView mav = new ModelAndView("card:makecode-list");
		if (start == null) {
			start = new Date(0);
		}
		if (end == null) {
			end = new Date();
		}
		Page<CardMakeCode> page = cardMakeCodeBiz.listAll(start, end, pageable);
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping(value = "/generate",method = RequestMethod.GET)
	public ModelAndView generate() {
		ModelAndView mav = new ModelAndView("card:makecode-generate");
		return mav;
	}
	
	@RequestMapping(value = "/doGenerate", method = RequestMethod.POST)
	@ResponseBody
	public Result doGenerate(@RequestParam("maker") String maker) {
		Result result = new Result();
		User user = getCurrentUser();
		String code = cardMakeCodeBiz.generateNewCode(maker, user.getId());
		result.setValue(code);
		return result;
	}
	
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	@ResponseBody
	public Result doDelete(@RequestParam("makecode") String makecode) {
		Result result = new Result();
		cardMakeCodeBiz.delete(makecode);
		return result;
	}
	
}
