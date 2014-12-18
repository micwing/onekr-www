package onekr.web.console.card;

import java.util.List;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardMusicFileBiz;
import onekr.cardservice.model.Card;
import onekr.commonservice.model.FileStore;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;
import onekr.identityservice.model.User;
import onekr.web.console.ConsoleBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/card/music")
public class CardMusicController extends ConsoleBaseController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardMusicFileBiz cardMusicFileBiz;
	
	@RequestMapping(value = "/cardmusic/{cardId}", method = RequestMethod.GET)
	public ModelAndView cardmusic(ModelMap model, @PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("card:card-music",model);
		Card card = cardBiz.findById(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		List<FileStore> list = cardMusicFileBiz.listSystemMusic();
		List<FileStore> list2 = cardMusicFileBiz.listUserUploadMusic(cardId);
		list.addAll(list2);
		mav.addObject("list", list);
		mav.addObject("card", card);
		return mav;
	}
	
	@RequestMapping(value="/doUploadFile",method=RequestMethod.POST)
    public ModelAndView doUploadFile(
    		@RequestParam("file") MultipartFile mfiles, 
    		@RequestParam("cardId") Long cardId) {
		User user = (User) getCurrentUser();
		List<FileStore> list2 = cardMusicFileBiz.listUserUploadMusic(cardId);
		if (!CollectionUtils.isEmpty(list2) && list2.size() >= 3) {
			throw new AppException(ErrorCode.RANGE_ERROR, "上传音乐数量超过限制");
		}
		cardMusicFileBiz.saveCardMusic(cardId, mfiles, user.getId());
		return cardmusic(new ModelMap("result", new Result("上传成功！")), cardId);
    }
	
	@RequestMapping(value="/doSetMusic",method=RequestMethod.GET)
    public ModelAndView doSetMusic(
    		@RequestParam("fileStoreId") Long fileStoreId,
    		@RequestParam("cardId") Long cardId) {
		User user = (User) getCurrentUser();
		cardMusicFileBiz.useMusic(cardId, fileStoreId, user.getId());
		return cardmusic(new ModelMap("result", new Result("设置成功！")), cardId);
    }
	
	@RequestMapping(value="/doDeleteMusic",method=RequestMethod.GET)
    public ModelAndView doDeleteMusic(
    		@RequestParam("fileStoreId") Long fileStoreId,
    		@RequestParam("cardId") Long cardId) {
		User user = (User) getCurrentUser();
		cardMusicFileBiz.deleteCardMusic(cardId, fileStoreId, user.getId());
		return cardmusic(new ModelMap("result", new Result("删除成功！")), cardId);
    }
}
