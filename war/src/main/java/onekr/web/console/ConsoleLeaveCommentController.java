package onekr.web.console;

import java.util.Date;

import onekr.commonservice.leaveComment.intf.LeaveCommentBiz;
import onekr.framework.result.Result;
import onekr.web.base.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/leaveComment")
public class ConsoleLeaveCommentController extends BaseController {
	
	@Autowired
	private LeaveCommentBiz commentBiz;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(CONSOLE+"leaveComment-list");
		mav.addObject("comments", commentBiz.listAll());
		return mav;
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	@ResponseBody
	public Result findComment(Long commentId) {
		Result result = new Result();
		result.setValue(commentBiz.getComment(commentId));
		return result;
	}
	
	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(
			Long id,
			String senderName, 
			String senderEmail, 
			Date createAt,
			String content,
			String replyContent,
			Date replyAt,
			Boolean isNoticeSender) {
		Result result = new Result();
		commentBiz.saveComment(id,senderName, senderEmail, createAt, content, false);
		commentBiz.replyComment(id, replyContent, replyAt, isNoticeSender);
		return result;
	}
	
//	@RequestMapping(value = "/doReply", method = RequestMethod.POST)
//	@ResponseBody
//	public Result doReply(
//			Long id,
//			String replyContent,
//			Date replyAt,
//			Boolean isNoticeSender) {
//		Result result = new Result();
//		commentBiz.replyComment(id, replyContent, replyAt, isNoticeSender);
//		return result;
//	}
	
	@RequestMapping(value = "/doNoticeSender", method = RequestMethod.POST)
	@ResponseBody
	public Result doNoticeSender(Long commentId) {
		Result result = new Result();
		commentBiz.notifySender4CommentReply(commentId);
		return result;
	}
	
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	@ResponseBody
	public Result doDelete(Long commentId) {
		Result result = new Result();
		commentBiz.deleteComment(commentId);
		return result;
	}
	
}
