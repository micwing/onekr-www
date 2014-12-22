package onekr.web.portal;

import java.util.Collections;
import java.util.List;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.common.intf.CommentBiz;
import onekr.commonservice.common.intf.CountBiz;
import onekr.commonservice.model.Comment;
import onekr.framework.controller.BaseController;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.verifycode.ImageIOVerifyCodeServlet;
import onekr.portalservice.article.intf.ArticleBiz;
import onekr.portalservice.article.intf.SearchArticleBiz;
import onekr.portalservice.model.Article;
import onekr.portalservice.utils.GlobalConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;


@Controller
@RequestMapping(value = "/portal/article")
public class PortalWebArticleController extends BaseController {
	
	@Autowired
	private ArticleBiz articleBiz;
	@Autowired
	private SearchArticleBiz searchArticleBiz;
	@Autowired
	private CommentBiz commentBiz;
	@Autowired
	private CountBiz countBiz;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(Pageable pageable) {
		ModelAndView mav = new ModelAndView("portalweb:articleList");
		Page<Article> page = articleBiz.list(pageable);
		articleBiz.putTotalComment2Articles(page.getContent());
		articleBiz.putTotalViewCount2Articles(page.getContent());
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping(value = "/detail/{articleId}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable("articleId") Long articleId) {
		ModelAndView mav = new ModelAndView("portalweb:articleDetail");
		Article article = articleBiz.findArticle(articleId);
		countBiz.addCount(Biz.PORTAL_ARTICLE_VIEW_COUNT, article.getId()+"", null);
		articleBiz.putTotalViewCount2Articles(Collections.singleton(article));
		mav.addObject("article", article);
		mav.addObject("comments", commentBiz.findComments(Biz.PORTAL_ARTICLE_COMMENTS, articleId+""));
		return mav;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		ModelAndView mav = new ModelAndView("redirect:/article");
		return mav;
	}
	
	@RequestMapping(value = "/search/{keys}", method = RequestMethod.GET)
	public ModelAndView search(@PathVariable("keys") String keys) {
		ModelAndView mav = new ModelAndView("portalweb:articleSearch");
		mav.addObject("keys", keys);
		try {
			List<Article> list = searchArticleBiz.search(keys);
			articleBiz.putTotalComment2Articles(list);
			articleBiz.putTotalViewCount2Articles(list);
			mav.addObject("articles", list);
		} catch (Exception e) {
			mav.addObject("articles", Collections.emptyList());
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value = "/comment/{articleId}", method = RequestMethod.POST)
	public String comment(@PathVariable("articleId") Long articleId, Comment data) {
		if (!ImageIOVerifyCodeServlet.validate(getRequest()) ) {
			throw new AppException(ErrorCode.PAPTCHA_NOT_MATCH);
		}
		Comment comment = new Comment();
		comment.setBiz(GlobalConstants.BIZ_ARTICLE_COMMENTS);
		comment.setContent(data.getContent());
		comment.setOwner(articleId+"");
		comment.setTitle(data.getTitle());
		comment.setUserName(data.getUserName());
//		comment.setJson(JSON.toJSONString(Collections.singletonMap(Comment.KEY_EMAIL, data.getEmail())));
		commentBiz.saveComment(comment);
		return "redirect:/article/detail/"+articleId;
	}
}
