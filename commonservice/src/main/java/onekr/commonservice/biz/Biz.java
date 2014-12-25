package onekr.commonservice.biz;

public enum Biz {
	
	/**系统*/
	SYSTEM,
	
	/**文章评论*/
	PORTAL_ARTICLE_COMMENTS,
	
	/**门户留言*/
	PORTAL_COMMENTS,
	
	/**文章查看计数*/
	PORTAL_ARTICLE_VIEW_COUNT,
	
	/**门户留言好评计数*/
	PORTAL_COMMENTS_GOOD_COUNT,
	
	/**门户留言差评计数*/
	PORTAL_COMMENTS_BAD_COUNT,
	
	/**card留言*/
	CARD_COMMENTS,
	
	/**card照片点赞计数*/
//	CARD_PHOTO_PRAISE_COUNT,
	
	/**card照片存储*/
	CARD_PHOTO_FILE_STORE,
	/**card照片缩略图存储*/
	CARD_PHOTO_THUMB_FILE_STORE,
	
	/**card音乐存储*/
	CARD_MUSIC_FILE_STORE,
	
	/**婚礼现场照片存储*/
	MOMENT_PHOTO_FILE_STORE,
	/**婚礼现场照片缩略图存储*/
	MOMENT_PHOTO_THUMB_FILE_STORE,
	
	/**婚礼制作码订单*/
	WEDING_MAKECODE_ORDER;
}
