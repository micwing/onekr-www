package onekr.cardservice.card.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import onekr.cardservice.card.dao.CardDao;
import onekr.cardservice.card.intf.CardMusicFileBiz;
import onekr.cardservice.model.Card;
import onekr.cardservice.utils.Converter;
import onekr.commonservice.biz.Biz;
import onekr.commonservice.filestore.intf.FileBiz;
import onekr.commonservice.filestore.intf.FileStoreBiz;
import onekr.commonservice.model.FileStore;
import onekr.commonservice.model.Status;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.utils.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CardMusicFileBizImpl implements CardMusicFileBiz {
	
	@Autowired
	private FileBiz fileBiz;
	@Autowired
	private FileStoreBiz fileStoreBiz;
	@Autowired
	private CardDao cardDao;
	
	@Override
	public FileStore saveCardMusic(Long cardId, MultipartFile mfile, Long uid) {
		String originalFilename = mfile.getOriginalFilename();
		String path = File.separator+"card"+File.separator+cardId;
		String name;
		try {
			name = fileBiz.saveMultipartFile(mfile, path);
		} catch (Exception e) {
			throw new AppException(ErrorCode.SERVER_ERROR);
		}
		
		Date now = new Date();
		
		FileStore fileStore = new FileStore();
		fileStore.setBiz(Biz.CARD_MUSIC_FILE_STORE.name());
		fileStore.setCreateAt(now);
		fileStore.setCreateBy(uid);
		fileStore.setDescription("");
		fileStore.setOriginalName(mfile.getOriginalFilename());
		fileStore.setOwner(cardId+"");
		fileStore.setRank(0L);
		fileStore.setSize(mfile.getSize());
		fileStore.setStatus(Status.NORMAL);
		fileStore.setStorePath(path+File.separator+name);
		fileStore.setSuffixName(FileUtil.getPathOrUrlSuffix(originalFilename));
		fileStore.setType(Converter.getFileType(originalFilename));
		fileStore.setUpdateAt(now);
		fileStore.setUpdateBy(uid);
		
		return fileStoreBiz.saveFileStore(fileStore);
	}
	
	@Override
	public FileStore saveSystemMusic(MultipartFile mfile, Long uid) {
		String originalFilename = mfile.getOriginalFilename();
		String path = File.separator+"card"+File.separator+SYSTEM_MUSIC_FILE_STORE_OWNER;
		String name;
		try {
			name = fileBiz.saveMultipartFile(mfile, path);
		} catch (Exception e) {
			throw new AppException(ErrorCode.SERVER_ERROR);
		}
		
		Date now = new Date();
		
		FileStore fileStore = new FileStore();
		fileStore.setBiz(Biz.CARD_MUSIC_FILE_STORE.name());
		fileStore.setCreateAt(now);
		fileStore.setCreateBy(uid);
		fileStore.setDescription("");
		fileStore.setOriginalName(mfile.getOriginalFilename());
		fileStore.setOwner(SYSTEM_MUSIC_FILE_STORE_OWNER);
		fileStore.setRank(0L);
		fileStore.setSize(mfile.getSize());
		fileStore.setStatus(Status.NORMAL);
		fileStore.setStorePath(path+File.separator+name);
		fileStore.setSuffixName(FileUtil.getPathOrUrlSuffix(originalFilename));
		fileStore.setType(Converter.getFileType(originalFilename));
		fileStore.setUpdateAt(now);
		fileStore.setUpdateBy(uid);
		
		return fileStoreBiz.saveFileStore(fileStore);
	}
	
	@Override
	public List<FileStore> listSystemMusic() {
		List<FileStore> list = fileStoreBiz.listFileStore(Biz.CARD_MUSIC_FILE_STORE, SYSTEM_MUSIC_FILE_STORE_OWNER);
		return list;
	}
	
	@Override
	public List<FileStore> listUserUploadMusic(Long cardId) {
		List<FileStore> list2 = fileStoreBiz.listFileStore(Biz.CARD_MUSIC_FILE_STORE, cardId+"");
		return list2;
	}
	
	@Override
	public void useMusic(Long cardId, Long fileStoreId, Long uid) {
		FileStore fs = fileStoreBiz.findById(fileStoreId);
		
		Date now = new Date();
		Card card = cardDao.findOne(cardId);
		
		card.setMusicFileStore(fs);
		card.setUpdateAt(now);
		card.setUpdateBy(uid);
		cardDao.save(card);
		
//		List<FileStore> list = listCardMusic(cardId);
//		for (FileStore fs : list) {
//			JSONObject json = JSON.parseObject(fs.getJson());
//			boolean using = false;
//			if (json != null) {
//				using = json.getBoolean(CardFileBiz.CARD_MUSIC_JSON_ATTR_KEY_USE);
//			} else {
//				json = new JSONObject();
//			}
//			
//			if (fs.getId().equals(fileStoreId) && !using) {
//				json.put(CardFileBiz.CARD_MUSIC_JSON_ATTR_KEY_USE, true);
//				fs.setJson(JSON.toJSONString(json));
//				fs.setUpdateAt(now);
//				fs.setUpdateBy(uid);
//				fileStoreBiz.saveFileStore(fs);
//			}
//			if (!fs.getId().equals(fileStoreId) && using) {
//				json.put(CardFileBiz.CARD_MUSIC_JSON_ATTR_KEY_USE, false);
//				fs.setJson(JSON.toJSONString(json));
//				fs.setUpdateAt(now);
//				fs.setUpdateBy(uid);
//				fileStoreBiz.saveFileStore(fs);
//			}
//		}
	}
	
	@Override
	public void deleteCardMusic(Long cardId, Long fileStoreId, Long uid) {
		Card card = cardDao.findOne(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		
		List<FileStore> mlist = fileStoreBiz.listFileStore(Biz.CARD_MUSIC_FILE_STORE, cardId+"");
		FileStore fs = fileStoreBiz.findById(fileStoreId);
		if (!mlist.contains(fs)) {
			throw new AppException(ErrorCode.NO_PERMISSON);
		}
		
		fileBiz.deleteFile(fs.getStorePath());
		fileStoreBiz.delete(fs.getId());
		
		if (card.getMusicFileStore() != null && card.getMusicFileStore().getId().equals(fileStoreId)) {			
			card.setMusicFileStore(null);
			card.setUpdateAt(new Date());
			card.setUpdateBy(uid);
			cardDao.save(card);
		}
	}
	
	@Override
	public void deleteSystemMusic(Long fileStoreId, Long uid) {
		List<FileStore> mlist = fileStoreBiz.listFileStore(Biz.CARD_MUSIC_FILE_STORE, SYSTEM_MUSIC_FILE_STORE_OWNER);
		FileStore fs = fileStoreBiz.findById(fileStoreId);
		if (!mlist.contains(fs)) {
			throw new AppException(ErrorCode.NO_PERMISSON);
		}
		
		List<Card> all = cardDao.findAll();
		for (Card c : all) {
			if (c.getMusicFileStore() != null && c.getMusicFileStore().getId().equals(fileStoreId)) {
				throw new AppException(ErrorCode.ENTITY_ALREADY_EXIST, "该音乐已被已使用，不能删除！");
			}
		}
		
		fileBiz.deleteFile(fs.getStorePath());
		fileStoreBiz.delete(fs.getId());
	}
	
	@Override
	public FileStore getUseMusic(Long cardId) {
		Card card = cardDao.findOne(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		
		return card.getMusicFileStore();
//		List<FileStore> list = listCardMusic(cardId);
//		if (CollectionUtils.isEmpty(list))
//			return null;
//		
//		for (FileStore fs : list) {
//			JSONObject json = JSON.parseObject(fs.getJson());
//			if (json.getBooleanValue(CardFileBiz.CARD_MUSIC_JSON_ATTR_KEY_USE)) {
//				return fs;
//			}
//		}
//		return null;
	}

}
