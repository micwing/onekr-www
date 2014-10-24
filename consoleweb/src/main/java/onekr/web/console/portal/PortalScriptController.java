package onekr.web.console.portal;

import java.util.Collections;
import java.util.List;

import onekr.commonservice.model.Script;
import onekr.commonservice.model.ScriptType;
import onekr.framework.controller.BaseController;
import onekr.framework.result.Result;
import onekr.framework.spring.web.annotation.RequestJsonParam;
import onekr.portalservice.article.intf.ScriptBiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/script")
public class PortalScriptController extends BaseController {
	
	@Autowired
	private ScriptBiz scriptBiz;
	
	@RequestMapping(value = "/executeScript/{scriptId}", method = RequestMethod.GET)
	public ModelAndView executeScript(@PathVariable("scriptId") Long scriptId) {
		Script script = scriptBiz.findScriptById(scriptId);
		ModelAndView mav = new ModelAndView();
		if (script.getScriptType().ordinal() == ScriptType.ARTICLE.ordinal()) {
			mav.setViewName("portal:script-article-action");
			mav.addObject("urlScripts", scriptBiz.listScriptByType(Collections.singleton(ScriptType.URL)));
		} else if (script.getScriptType().ordinal() == ScriptType.URL.ordinal()) {
			mav.setViewName("portal:script-url-action");
		}
		mav.addObject("script", script);
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/doExceuteArticleScript", method = RequestMethod.POST)
	@ResponseBody
	public Result doExceuteArticleScript(
			@RequestJsonParam(value="urls", required = false) List<String> urls,
			@RequestParam("scriptId") Long scriptId,
			@RequestParam("author") String author,
			@RequestParam(value="preScriptId", required = false) Long preScriptId,
			@RequestJsonParam(value="preUrl", required = false) String preUrl
			) {
		//脚本预处理获取文章url
		if (preScriptId != null && preScriptId > 0) {
			Result preResult = scriptBiz.exceuteScript(preScriptId, new Object[] {preUrl});
			urls = (List<String>) preResult.getValue();
		}
		scriptBiz.exceuteScript(scriptId, new Object[] {urls, author});
		return new Result();
	}
	
	@RequestMapping(value = "/doExceuteUrlScript", method = RequestMethod.POST)
	@ResponseBody
	public Result doExceuteUrlScript(@RequestJsonParam("url") String url,
			@RequestParam("scriptId") Long scriptId
			) {
		Result result = scriptBiz.exceuteScript(scriptId, new Object[] {url});
		return result;
	}
	
	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
	public String doSave(Script script) {
		scriptBiz.saveScript(script);
		return "redirect:/console/script/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView scripts() {
		ModelAndView mav = new ModelAndView("portal:scripts");
		mav.addObject("scripts", scriptBiz.listScriptAll());
		return mav;
	}
	
	@RequestMapping(value = "/createScript", method = RequestMethod.GET)
	public ModelAndView createScript() {
		ModelAndView mav = new ModelAndView("portal:script");
		return mav;
	}
	
	@RequestMapping(value = "/modifyScript/{scriptId}", method = RequestMethod.GET)
	public ModelAndView modifyScript(@PathVariable("scriptId") Long scriptId) {
		ModelAndView mav = new ModelAndView("portal:script");
		mav.addObject("script", scriptBiz.findScriptById(scriptId));
		return mav;
	}
	
	@RequestMapping(value = "/findScriptById", method = RequestMethod.POST)
	@ResponseBody
	public Result findScriptById(@RequestParam("scriptId") Long scriptId) {
		Script script = scriptBiz.findScriptById(scriptId);
		Result result = new Result();
		result.setValue(script);
		return result;
	}
	
	@RequestMapping(value = "/doDelete/{scriptId}", method = RequestMethod.GET)
	public String doDelete(@PathVariable("scriptId") Long scriptId) {
		scriptBiz.deleteScript(Collections.singleton(scriptId));
		return "redirect:/console/script/list";
	}
}
