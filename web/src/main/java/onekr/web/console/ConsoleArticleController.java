package onekr.web.console;

import java.io.IOException;
import java.util.Collections;

import onekr.biz.article.intf.ArticleBiz;
import onekr.biz.article.intf.ScriptBiz;
import onekr.biz.article.intf.SearchArticleBiz;
import onekr.biz.model.Article;
import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;
import onekr.web.base.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/article")
public class ConsoleArticleController extends BaseController {
	
	@Autowired
	private ArticleBiz articleBiz;
	@Autowired
	private ScriptBiz scriptBiz;
	@Autowired
	private SearchArticleBiz searchArticleBiz;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Pageable pageable) {
		ModelAndView mav = new ModelAndView(CONSOLE+"article-list");
		mav.addObject("page", articleBiz.list(pageable));
		return mav;
	}
	
	@RequestMapping(value = "/articleConfig", method = RequestMethod.GET)
	public ModelAndView articleConfig() {
		ModelAndView mav = new ModelAndView(CONSOLE+"article-search");
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView(CONSOLE+"article-add");
		return mav;
	}
	
	@RequestMapping(value = "/modify/{articleId}", method = RequestMethod.GET)
	public ModelAndView modify(@PathVariable("articleId") Long articleId) {
		ModelAndView mav = new ModelAndView(CONSOLE+"article-add");
		mav.addObject("article", articleBiz.findArticle(articleId));
		return mav;
	}
	
	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
	public String doSave(Article article) {
		articleBiz.saveArticle(article);
		return "redirect:/console/article/list";
	}
	
	@RequestMapping(value = "/doDelete/{articleId}", method = RequestMethod.GET)
	public String doDelete(@PathVariable("articleId") Long articleId) {
		articleBiz.deleteArticle(Collections.singletonList(articleId));
		return "redirect:/console/article/list";
	}
	
	@RequestMapping(value = "/doIndex", method = RequestMethod.POST)
	@ResponseBody
	public Result doIndex() {
		try {
			searchArticleBiz.indexFile(articleBiz.findAll());
		} catch (IOException e) {
			return new Result(ErrorCode.SERVER_ERROR);
		}
		return new Result();
	}
	
}
