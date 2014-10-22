package onekr.web.card;

import onekr.commonservice.common.intf.CommentBiz;
import onekr.commonservice.common.intf.CountBiz;
import onekr.web.base.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/card")
public class CardController extends BaseController {
	
	@Autowired
	private CommentBiz commentBiz;
	@Autowired
	private CountBiz countBiz;
	
	@RequestMapping(value = "/{cardId}/cover", method = RequestMethod.GET)
	public ModelAndView cover(@PathVariable("cardId") String cardId) {
		ModelAndView mav = new ModelAndView(CARD+"cover");
//		Article article = articleBiz.findArticle(articleId);
//		countBiz.addCount(GlobalConstants.BIZ_ARTICLE_VIEW_COUNT, article.getId()+"", null);
//		articleBiz.putTotalViewCount2Articles(Collections.singleton(article));
//		mav.addObject("article", article);
//		mav.addObject("comments", commentBiz.findComments(GlobalConstants.BIZ_ARTICLE_COMMENTS, articleId+""));
		return mav;
	}
	
	@RequestMapping(value = "/{cardId}/main", method = RequestMethod.GET)
	public ModelAndView main(@PathVariable("cardId") String cardId) {
		ModelAndView mav = new ModelAndView("redirect:/article");
		return mav;
	}
	
}
