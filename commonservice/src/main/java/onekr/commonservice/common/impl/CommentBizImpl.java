package onekr.commonservice.common.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.common.dao.CommentDao;
import onekr.commonservice.common.dao.CommonSpecificDao;
import onekr.commonservice.common.intf.CommentBiz;
import onekr.commonservice.model.Comment;
import onekr.commonservice.model.Status;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentBizImpl implements CommentBiz {
	
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private CommonSpecificDao commonSpecificDao;

	@Override
	public List<Comment> findCommentsByOwners(Biz biz, Collection<String> owners) {
		return commentDao.findByBizAndOwnerIn(biz.name(), owners);
	}
	
	@Override
	public Map<String, Long> findOwnerCountMap(Biz biz,
			Collection<String> owners) {
		return commonSpecificDao.countOwnerComments(biz.name(), owners);
	}
	
	@Override
	public List<Comment> findAll(Biz biz) {
		return commentDao.findByBiz(biz.name());
	}
	
	@Override
	public List<Comment> findComments(Biz biz, String owner) {
		return commentDao.findByBizAndOwnerOrderByCreateAtDesc(biz.name(), owner);
	}
	
	@Override
	public Comment findById(Long id) {
		return commentDao.findOne(id);
	}
	
	@Override
	public Comment saveComment(Comment param) {
		Comment entry = null;
		if (param.getId() == null) {
			entry = new Comment();
			entry.setBiz(param.getBiz());
			entry.setOwner(param.getOwner());
			entry.setStatus(param.getStatus() == null ? Status.NORMAL:param.getStatus());
			entry.setCreateAt(param.getCreateAt() == null ? new Date() : param.getCreateAt());
			entry.setBiz(param.getBiz());
			entry.setOwner(param.getOwner());
		} else {
			entry = commentDao.findOne(param.getId());
			if (entry == null)
				throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		}
		entry.setContent(param.getContent());
		entry.setTitle(param.getTitle());
		entry.setJson(param.getJson());
		if (param.getStatus() != null) {				
			entry.setStatus(param.getStatus());
		}
		entry.setUserId(param.getUserId());
		entry.setUserName(param.getUserName());
		return commentDao.save(entry);
	}
	
	@Override
	public Comment updateCommentStatus(Long id, Status status) {
		Comment c = commentDao.findOne(id);
		c.setStatus(status);
		commentDao.save(c);
		return c;
	}
	
	@Override
	public void delete(Long id) {
		commentDao.delete(id);
	}
	
}
