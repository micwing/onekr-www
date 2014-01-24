package onekr.web.portal;

import java.util.Collections;
import java.util.List;

import onekr.biz.article.intf.ArticleBiz;
import onekr.biz.article.intf.SearchArticleBiz;
import onekr.biz.common.intf.CommentBiz;
import onekr.biz.common.intf.CountBiz;
import onekr.biz.model.Article;
import onekr.biz.model.Comment;
import onekr.biz.utils.GlobalConstants;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.verifycode.VerifyCodeServlet;
import onekr.web.base.BaseController;

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
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {
	
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
		ModelAndView mav = new ModelAndView(PORTAL+"articleList");
		Page<Article> page = articleBiz.list(pageable);
		articleBiz.putTotalComment2Articles(page.getContent());
		articleBiz.putTotalViewCount2Articles(page.getContent());
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping(value = "/detail/{articleId}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable("articleId") Long articleId) {
		ModelAndView mav = new ModelAndView(PORTAL+"articleDetail");
		Article article = articleBiz.findArticle(articleId);
		countBiz.addCount(GlobalConstants.BIZ_ARTICLE_VIEW_COUNT, article.getId()+"", null);
		articleBiz.putTotalViewCount2Articles(Collections.singleton(article));
		mav.addObject("article", article);
		mav.addObject("comments", commentBiz.findComments(GlobalConstants.BIZ_ARTICLE_COMMENTS, articleId+""));
		return mav;
	}
	
	@RequestMapping(value = "/search/{keys}", method = RequestMethod.GET)
	public ModelAndView search(@PathVariable("keys") String keys) {
		ModelAndView mav = new ModelAndView(PORTAL+"articleSearch");
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
		if (!VerifyCodeServlet.validate(getRequest()) ) {
			throw new AppException(ErrorCode.PAPTCHA_NOT_MATCH);
		}
		Comment comment = new Comment();
		comment.setBiz(GlobalConstants.BIZ_ARTICLE_COMMENTS);
		comment.setContent(data.getContent());
		comment.setOwner(articleId+"");
		comment.setTitle(data.getTitle());
		comment.setUserName(data.getUserName());
		comment.setJson(JSON.toJSONString(Collections.singletonMap(Comment.KEY_EMAIL, data.getEmail())));
		commentBiz.saveComment(comment);
		return "redirect:/article/detail/"+articleId;
	}
}
