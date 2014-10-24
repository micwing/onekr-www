package onekr.portalservice.article.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.common.intf.CommentBiz;
import onekr.commonservice.common.intf.CountBiz;
import onekr.commonservice.model.Count;
import onekr.portalservice.article.dao.ArticleDao;
import onekr.portalservice.article.intf.ArticleBiz;
import onekr.portalservice.model.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ArticleBizImpl implements ArticleBiz {
	
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private CommentBiz commentBiz;
	@Autowired
	private CountBiz countBiz;
	
	@Override
	public void saveArticle(Article article) {
		Date now =new Date();
		Article entry = null;
		if (article.getId() != null) {
			entry = articleDao.findOne(article.getId());
		} else {
			entry = new Article();
			if (article.getCreateAt() != null)
				entry.setCreateAt(article.getCreateAt());
			else
				entry.setCreateAt(now);
		}
		entry.setImageUrl(article.getImageUrl());
		entry.setContent(article.getContent());
		entry.setTitle(article.getTitle());
		entry.setSummary(article.getSummary());
		entry.setAuthor(article.getAuthor());
		entry.setFromUrl(article.getFromUrl());
		
		articleDao.save(entry);
	}

	@Override
	public Article findArticle(Long articleId) {
		return articleDao.findOne(articleId);
	}

	@Override
	public Page<Article> list(Pageable pageable) {
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "createAt"));
		Sort sort = new Sort(orders);
		Page<Article> page = articleDao.findAll(new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort));
		return page;
	}
	
	@Override
	public void putTotalComment2Articles(Collection<Article> articles) {
		if (CollectionUtils.isEmpty(articles))
			return;
		Set<String> ids = new HashSet<String>();
		for (Article article : articles) {
			ids.add(article.getId()+"");
		}
		Map<String, Long> map = commentBiz.findOwnerCountMap(Biz.PORTAL_ARTICLE_COMMENTS, ids);
		for (Article article : articles) {
			String idstr = article.getId()+"";
			if (map.containsKey(idstr))
				article.setTotalComment(map.get(idstr));
			else
				article.setTotalComment(0L);
		}
	}
	
	@Override
	public void putTotalViewCount2Articles(Collection<Article> articles) {
		if (CollectionUtils.isEmpty(articles))
			return;
		Set<String> ids = new HashSet<String>();
		for (Article article : articles) {
			ids.add(article.getId()+"");
		}
		Map<String, Count> map = countBiz.findCounts(Biz.PORTAL_ARTICLE_VIEW_COUNT, ids);
		for (Article article : articles) {
			String idstr = article.getId()+"";
			if (map.containsKey(idstr))
				article.setTotalViewCount(map.get(idstr).getNum());
			else
				article.setTotalViewCount(0L);
		}
	}
	
	@Override
	public void deleteArticle(List<Long> ids) {
		for (Long id : ids) {
			articleDao.delete(id);
		}
	}
	
	@Override
	public List<Article> findAll() {
		return articleDao.findAll();
	}
}
