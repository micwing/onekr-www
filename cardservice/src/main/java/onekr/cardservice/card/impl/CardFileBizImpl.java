package onekr.cardservice.card.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import onekr.cardservice.card.intf.CardFileBiz;
import onekr.cardservice.card.intf.CardPhotoDto;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class CardFileBizImpl implements CardFileBiz {
	
	@Autowired
	private FileBiz fileBiz;
	@Autowired
	private FileStoreBiz fileStoreBiz;
	
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
		fileStore.setRank(getNewRank4Card(cardId));
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
	public List<FileStore> listCardMusic(Long cardId) {
		List<FileStore> list = fileStoreBiz.listFileStore(Biz.CARD_MUSIC_FILE_STORE, cardId+"");
		return list;
	}
	
	@Override
	public void useMusic(Long cardId, Long fileStoreId, Long uid) {
		Date now = new Date();
		
		List<FileStore> list = listCardMusic(cardId);
		for (FileStore fs : list) {
			JSONObject json = JSON.parseObject(fs.getJson());
			boolean using = false;
			if (json != null) {
				using = json.getBoolean(CardFileBiz.CARD_MUSIC_JSON_ATTR_KEY_USE);
			} else {
				json = new JSONObject();
			}
			
			if (fs.getId().equals(fileStoreId) && !using) {
				json.put(CardFileBiz.CARD_MUSIC_JSON_ATTR_KEY_USE, true);
				fs.setJson(JSON.toJSONString(json));
				fs.setUpdateAt(now);
				fs.setUpdateBy(uid);
				fileStoreBiz.saveFileStore(fs);
			}
			if (!fs.getId().equals(fileStoreId) && using) {
				json.put(CardFileBiz.CARD_MUSIC_JSON_ATTR_KEY_USE, false);
				fs.setJson(JSON.toJSONString(json));
				fs.setUpdateAt(now);
				fs.setUpdateBy(uid);
				fileStoreBiz.saveFileStore(fs);
			}
		}
	}
	
	@Override
	public void deleteCardMusic(Long cardId, Long fileStoreId, Long uid) {
		FileStore fs = fileStoreBiz.findById(fileStoreId);
		CardPhotoDto dto = new CardPhotoDto(fs);
		
		fileBiz.deleteFile(dto.getPhoto().getStorePath());
		fileStoreBiz.delete(dto.getPhoto().getId());
	}
	
	@Override
	public FileStore getUseMusic(Long cardId) {
		List<FileStore> list = listCardMusic(cardId);
		if (CollectionUtils.isEmpty(list))
			return null;
		
		for (FileStore fs : list) {
			JSONObject json = JSON.parseObject(fs.getJson());
			if (json.getBooleanValue(CardFileBiz.CARD_MUSIC_JSON_ATTR_KEY_USE)) {
				return fs;
			}
		}
		return null;
	}

	@Override
	public FileStore[] saveMomentPhotoThumb(Long cardId,
			MultipartFile[] mfiles, Long uid) {
		FileStore[] stores = new FileStore[mfiles.length];
		for (int i = 0; i < mfiles.length ; i++) {
			stores[i] = saveCardPhotoThumb(cardId, mfiles[i], uid, Biz.MOMENT_PHOTO_THUMB_FILE_STORE);
		}
		return stores;
	}
	
	@Override
	public FileStore[] saveMomentPhoto(Long cardId, MultipartFile[] mfiles,
			FileStore[] cardPhotoThumb, Long uid) {
		FileStore[] stores = new FileStore[mfiles.length];
		for (int i = 0; i < mfiles.length ; i++) {
			stores[i] = saveCardPhoto(cardId, mfiles[i],cardPhotoThumb[i], uid, Biz.MOMENT_PHOTO_FILE_STORE);
		}
		return stores;
	}
	
	@Override
	public List<CardPhotoDto> listMomentPhoto(Long cardId) {
		return listCardPhoto(cardId, Biz.MOMENT_PHOTO_FILE_STORE, Biz.MOMENT_PHOTO_THUMB_FILE_STORE);
	}
	
	private FileStore saveCardPhoto(Long cardId, MultipartFile mfile,FileStore cardPhotoThumb, Long uid, Biz biz) {
		String originalFilename = mfile.getOriginalFilename();
		String path = File.separator+"card"+File.separator+cardId;
		String name;
		try {
			name = fileBiz.saveMultipartImage(mfile, CardFileBiz.SQUARE_IMAGE_PHOTO_MAX_WIDTH, path);
		} catch (Exception e) {
			throw new AppException(ErrorCode.SERVER_ERROR);
		}
		
		Date now = new Date();
		
		FileStore fileStore = new FileStore();
		fileStore.setBiz(biz.name());
		fileStore.setCreateAt(now);
		fileStore.setCreateBy(uid);
		fileStore.setDescription("");
		Map<String, Object> attr = new HashMap<String, Object>();
		attr.put(CardFileBiz.CARD_PHOTO_JSON_ATTR_KEY_THUMB, cardPhotoThumb.getId());
		fileStore.setJson(JSON.toJSONString(attr));
		fileStore.setOriginalName(mfile.getOriginalFilename());
		fileStore.setOwner(cardId+"");
		fileStore.setRank(getNewRank4Card(cardId));
		fileStore.setSize(mfile.getSize());
		fileStore.setStatus(Status.NORMAL);
		fileStore.setStorePath(path+File.separator+name);
		fileStore.setSuffixName(FileUtil.getPathOrUrlSuffix(originalFilename));
		fileStore.setType(Converter.getFileType(originalFilename));
		fileStore.setUpdateAt(now);
		fileStore.setUpdateBy(uid);
		
		return fileStoreBiz.saveFileStore(fileStore);
	}
	
	private FileStore saveCardPhotoThumb(Long cardId, MultipartFile mfile, Long uid, Biz biz) {
		String originalFilename = mfile.getOriginalFilename();
		String path = File.separator+"card"+File.separator+cardId+File.separator+"thumb";
		String name;
		try {
			name = fileBiz.saveMultipartImageThumb(mfile, CardFileBiz.SQUARE_IMAGE_THUMB_WIDTH, path);
		} catch (Exception e) {
			throw new AppException(ErrorCode.SERVER_ERROR);
		}
		
		Date now = new Date();
		
		FileStore fileStore = new FileStore();
		fileStore.setBiz(biz.name());
		fileStore.setCreateAt(now);
		fileStore.setCreateBy(uid);
		fileStore.setDescription("");
		fileStore.setJson("");
		fileStore.setOriginalName(mfile.getOriginalFilename());
		fileStore.setOwner(cardId+"");
		fileStore.setRank(getNewRank4Card(cardId));
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
	public FileStore[] saveCardPhoto(Long cardId,
			MultipartFile[] mfiles,FileStore[] cardPhotoThumb,Long uid) {
		FileStore[] stores = new FileStore[mfiles.length];
		for (int i = 0; i < mfiles.length ; i++) {
			stores[i] = saveCardPhoto(cardId, mfiles[i],cardPhotoThumb[i], uid, Biz.CARD_PHOTO_FILE_STORE);
		}
		return stores;
	}
	
	@Override
	public FileStore[] saveCardPhotoThumb(Long cardId,
			MultipartFile[] mfiles,Long uid) {
		FileStore[] stores = new FileStore[mfiles.length];
		for (int i = 0; i < mfiles.length ; i++) {
			stores[i] = saveCardPhotoThumb(cardId, mfiles[i], uid, Biz.CARD_PHOTO_THUMB_FILE_STORE);
		}
		return stores;
	}
	
	private long getNewRank4Card(Long cardId) {
		long max = 1;
		List<FileStore> fileStores = fileStoreBiz.listFileStore(Biz.CARD_PHOTO_FILE_STORE, cardId+"");
		if (CollectionUtils.isEmpty(fileStores))
			return max;
		for (FileStore fs : fileStores) {
			max = fs.getRank() > max ? (fs.getRank()+1) : max;
		}
		return max;
	}
	
	private List<CardPhotoDto> listCardPhoto(Long cardId, Biz photoBiz, Biz thumbBiz) {
		//原图
		List<FileStore> list = fileStoreBiz.listFileStore(photoBiz, cardId+"");
		if (CollectionUtils.isEmpty(list))
			return Collections.emptyList();
		//缩略图
		List<FileStore> thumbList = fileStoreBiz.listFileStore(thumbBiz, cardId+"");
		
		//组装
		List<CardPhotoDto> targetList = new ArrayList<CardPhotoDto>();
		Map<Long, FileStore> thumbMap = new HashMap<Long, FileStore>();
		if (!CollectionUtils.isEmpty(thumbList)) {
			for (FileStore fs : thumbList) {
				thumbMap.put(fs.getId(), fs);
			}
		}
		for (FileStore fs : list) {
			CardPhotoDto dto = new CardPhotoDto(fs);
			if (dto.getThumbId() != null) {				
				dto.setThumb(thumbMap.get(dto.getThumbId()));
			}
			targetList.add(dto);
		}
		return targetList;
	}

	@Override
	public List<CardPhotoDto> listCardPhoto(Long cardId) {
		return listCardPhoto(cardId, Biz.CARD_PHOTO_FILE_STORE, Biz.CARD_PHOTO_THUMB_FILE_STORE);
	}
	
	@Override
	public void usePhotoAsCover(Long cardId, Long fileStoreId, Long uid) {
		Date now = new Date();
		
		List<CardPhotoDto> list = listCardPhoto(cardId);
		for (CardPhotoDto dto : list) {
			if (dto.getId().equals(fileStoreId) && !dto.getIsCover()) {
				dto.setCover(true);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
			
			if (!dto.getId().equals(fileStoreId) && dto.getIsCover()) {
				dto.setCover(false);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
		}
	}
	
	@Override
	public void cancelPhotoAsCover(Long cardId, Long fileStoreId, Long uid) {
		Date now = new Date();
		
		List<CardPhotoDto> list = listCardPhoto(cardId);
		for (CardPhotoDto dto : list) {
			if (dto.getId().equals(fileStoreId) && dto.getIsCover()) {
				dto.setCover(false);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
			
			if (!dto.getId().equals(fileStoreId) && dto.getIsCover()) {
				dto.setCover(false);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
		}
	}
	
	@Override
	public CardPhotoDto getCardPhotoCover(Long cardId) {
		List<CardPhotoDto> list = listCardPhoto(cardId);
		if (CollectionUtils.isEmpty(list))
			return null;
		
		for (CardPhotoDto dto : list) {
			if (dto.getIsCover()) {
				return dto;
			}
		}
		return null;
	}
	
	@Override
	public void usePhotoAsPeople1(Long cardId, Long fileStoreId, Long uid) {
		Date now = new Date();
		
		List<CardPhotoDto> list = listCardPhoto(cardId);
		for (CardPhotoDto dto : list) {
			if (dto.getId().equals(fileStoreId) && !dto.getIsPeople1Photo()) {
				dto.setPeople1Photo(true);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
			
			if (!dto.getId().equals(fileStoreId) && dto.getIsPeople1Photo()) {
				dto.setPeople1Photo(false);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
		}
	}
	
	@Override
	public void cancelPhotoAsPeople1(Long cardId, Long fileStoreId, Long uid) {
		Date now = new Date();
		
		List<CardPhotoDto> list = listCardPhoto(cardId);
		for (CardPhotoDto dto : list) {
			if (dto.getId().equals(fileStoreId) && dto.getIsPeople1Photo()) {
				dto.setPeople1Photo(false);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
			
			if (!dto.getId().equals(fileStoreId) && dto.getIsPeople1Photo()) {
				dto.setPeople1Photo(false);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
		}
	}
	
	@Override
	public CardPhotoDto getCardPhotoPeople1(Long cardId) {
		List<CardPhotoDto> list = listCardPhoto(cardId);
		if (CollectionUtils.isEmpty(list))
			return null;
		
		for (CardPhotoDto dto : list) {
			if (dto.getIsPeople1Photo()) {
				return dto;
			}
		}
		return null;
	}
	
	@Override
	public void usePhotoAsPeople2(Long cardId, Long fileStoreId, Long uid) {
		Date now = new Date();
		
		List<CardPhotoDto> list = listCardPhoto(cardId);
		for (CardPhotoDto dto : list) {
			if (dto.getId().equals(fileStoreId) && !dto.getIsPeople2Photo()) {
				dto.setPeople2Photo(true);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
			
			if (!dto.getId().equals(fileStoreId) && dto.getIsPeople2Photo()) {
				dto.setPeople2Photo(false);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
		}
	}
	
	@Override
	public void cancelPhotoAsPeople2(Long cardId, Long fileStoreId, Long uid) {
		Date now = new Date();
		
		List<CardPhotoDto> list = listCardPhoto(cardId);
		for (CardPhotoDto dto : list) {
			if (dto.getId().equals(fileStoreId) && dto.getIsPeople2Photo()) {
				dto.setPeople2Photo(false);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
			
			if (!dto.getId().equals(fileStoreId) && dto.getIsPeople2Photo()) {
				dto.setPeople2Photo(false);
				dto.getPhoto().setUpdateAt(now);
				dto.getPhoto().setUpdateBy(uid);
				fileStoreBiz.saveFileStore(dto.getPhoto());
			}
		}
	}
	
	@Override
	public CardPhotoDto getCardPhotoPeople2(Long cardId) {
		List<CardPhotoDto> list = listCardPhoto(cardId);
		if (CollectionUtils.isEmpty(list))
			return null;
		
		for (CardPhotoDto dto : list) {
			if (dto.getIsPeople2Photo()) {
				return dto;
			}
		}
		return null;
	}
	
	@Override
	public void deleteCardPhoto(Long fileStoreId, Long uid) {
		FileStore fs = fileStoreBiz.findById(fileStoreId);
		CardPhotoDto dto = new CardPhotoDto(fs);
		
		if (dto.getThumbId() != null) {
			FileStore thumb = fileStoreBiz.findById(dto.getThumbId());
			fileBiz.deleteFile(thumb.getStorePath());
			fileStoreBiz.delete(thumb.getId());
		}
		
		fileBiz.deleteFile(dto.getPhoto().getStorePath());
		fileStoreBiz.delete(dto.getPhoto().getId());
		
	}
	
}
