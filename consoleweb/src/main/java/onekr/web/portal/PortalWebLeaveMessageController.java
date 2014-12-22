package onekr.web.portal;
//package onekr.web.console.portal;
//
//import java.util.Date;
//
//import onekr.framework.controller.BaseController;
//import onekr.framework.result.Result;
//import onekr.portalservice.leaveComment.intf.LeaveCommentBiz;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
//@Controller
//@RequestMapping(value = "/leaveMessage")
//public class WebLeaveMessageController extends BaseController {
//	
//	@Autowired
//	private LeaveCommentBiz leaveCommentBiz;
//	
//	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
//	@ResponseBody
//	public Result doSave(
//			String senderName, 
//			String senderEmail, 
//			String content) {
//		Result result = new Result();
//		leaveCommentBiz.saveComment(null,senderName, senderEmail,new Date(), content, true);
//		return result;
//	}
//	
//	@RequestMapping(value = "/doAddGoodBad", method = RequestMethod.POST)
//	@ResponseBody
//	public Result doAddGood(Long commentId, Boolean isGood) {
//		Result result = new Result();
//		Long value = leaveCommentBiz.addGoodBad(commentId, isGood);
//		result.setValue(value);
//		return result;
//	}
//	
//}
