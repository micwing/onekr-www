package onekr.web.console.card;

import java.util.List;

import onekr.cardservice.card.intf.CardCommentBiz;
import onekr.commonservice.model.Comment;
import onekr.framework.controller.BaseController;
import onekr.framework.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/console/card/comment")
public class CardCommentController extends BaseController {

	@Autowired
	private CardCommentBiz cardCommentBiz;
	
	@RequestMapping(value = "/list/{cardId}", method = RequestMethod.GET)
	public ModelAndView commentlist(@PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("card:card-comments");
		List<Comment> list = cardCommentBiz.listAll(cardId);
		mav.addObject("comments", list);
		mav.addObject("cardId", cardId);
		return mav;
	}
	
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	@ResponseBody
	public Result doDelete(@RequestParam("cardId") Long cardId,
			@RequestParam("commentId") Long commentId) {
		cardCommentBiz.deleteComment(cardId, commentId);
		return new Result();
	}
}
