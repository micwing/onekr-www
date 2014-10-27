package onekr.cardservice.card.impl;

import java.util.Date;
import java.util.List;

import onekr.cardservice.card.intf.CardFileBiz;
import onekr.commonservice.biz.Biz;
import onekr.commonservice.filestore.intf.FileBiz;
import onekr.commonservice.filestore.intf.FileStoreBiz;
import onekr.commonservice.model.FileStore;
import onekr.commonservice.model.FileType;
import onekr.commonservice.model.Status;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.utils.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CardFileBizImpl implements CardFileBiz {
	
	@Autowired
	private FileBiz fileBiz;
	@Autowired
	private FileStoreBiz fileStoreBiz;

	@Override
	public FileStore saveCardPhoto(Long cardId, MultipartFile mfile, String width, String height, Long uid) {
		String originalFilename = mfile.getOriginalFilename();
		String path;
		try {
			path = fileBiz.saveMultipartFile(mfile, "/card/"+cardId);
		} catch (Exception e) {
			throw new AppException(ErrorCode.SERVER_ERROR);
		}
		
		Date now = new Date();
		
		FileStore fileStore = new FileStore();
		fileStore.setBiz(Biz.CARD_PHOTO_FILE_STORE.name());
		fileStore.setCreateAt(now);
		fileStore.setCreateBy(uid);
		fileStore.setDescription("");
		fileStore.setJson("");
		fileStore.setOriginalName(mfile.getOriginalFilename());
		fileStore.setOwner(cardId+"");
		fileStore.setRank(getNewRank4Card(cardId));
		fileStore.setSize(mfile.getSize());
		fileStore.setStatus(Status.NORMAL);
		fileStore.setStorePath(path);
		fileStore.setSuffixName(FileUtil.getPathOrUrlSuffix(originalFilename));
		fileStore.setType(getFileType4Filename(originalFilename));
		fileStore.setUpdateAt(now);
		fileStore.setUpdateBy(uid);
		
		return fileStoreBiz.saveFileStore(fileStore);
	}
	
	@Override
	public FileStore[] saveCardPhoto(Long cardId,
			MultipartFile[] mfiles, String width, String height,Long uid) {
		FileStore[] stores = new FileStore[mfiles.length];
		for (int i = 0; i < mfiles.length ; i++) {
			stores[i] = saveCardPhoto(cardId, mfiles[i],width,height, uid);
		}
		return stores;
	}
	
	@Override
	public FileType getFileType4Filename(String filename) {
		if (FileUtil.isFileType(filename, FileUtil.IMAGE_FILE_TYPES)) {
			return FileType.IMAGE;
		} else if (FileUtil.isFileType(filename, FileUtil.AUDIO_FILE_TYPES)) {
			return FileType.AUDIO;
		} else if (FileUtil.isFileType(filename, FileUtil.VIDEO_FILE_TYPES)) {
			return FileType.VIDEO;
		} else if (FileUtil.isFileType(filename, FileUtil.DOC_FILE_TYPES)) {
			return FileType.DOC;
		} else if (FileUtil.isFileType(filename, FileUtil.PACKAGE_FILE_TYPES)) {
			return FileType.PACKAGE;
		} else {
			return FileType.OTHER;
		}
		
	}
	
	@Override
	public long getNewRank4Card(Long cardId) {
		long max = 1;
		List<FileStore> fileStores = fileStoreBiz.listFileStore(Biz.CARD_PHOTO_FILE_STORE, cardId+"");
		if (CollectionUtils.isEmpty(fileStores))
			return max;
		for (FileStore fs : fileStores) {
			max = fs.getRank() > max ? (fs.getRank()+1) : max;
		}
		return max;
	}

	@Override
	public List<FileStore> listCardPhoto(Long cardId) {
		List<FileStore> list = fileStoreBiz.listFileStore(Biz.CARD_PHOTO_FILE_STORE, cardId+"");
		return list;
	}
	
	@Override
	public FileStore usePhotoAs(Long fileStoreId, String cardPhotoDesc, Long uid) {
		FileStore fs = fileStoreBiz.findById(fileStoreId);
		if (fs == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		if (!fs.getBiz().equals(Biz.CARD_PHOTO_FILE_STORE.name())) 
			throw new AppException(ErrorCode.ILLEGAL_PARAM);
		
		Date now = new Date();
		
		List<FileStore> list = fileStoreBiz.listFileStore(Biz.CARD_PHOTO_FILE_STORE, fs.getOwner());
		for (FileStore e : list) {
			if (e.getDescription().contains("|"+cardPhotoDesc)) {
				e.setDescription(e.getDescription().replace("|"+cardPhotoDesc, ""));
				fs.setUpdateAt(now);
				fs.setUpdateBy(uid);
				fileStoreBiz.saveFileStore(e);
			}
		}
		
		fs.setDescription(fs.getDescription()+"|"+cardPhotoDesc);
		fs.setUpdateAt(now);
		fs.setUpdateBy(uid);
		return fileStoreBiz.saveFileStore(fs);
	}
	
	@Override
	public FileStore cancelPhotoAs(Long fileStoreId, String cardPhotoDesc, Long uid) {
		FileStore fs = fileStoreBiz.findById(fileStoreId);
		if (fs == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		if (!fs.getBiz().equals(Biz.CARD_PHOTO_FILE_STORE.name())) 
			throw new AppException(ErrorCode.ILLEGAL_PARAM);
		
		Date now = new Date();
		
		fs.setDescription(fs.getDescription().replace("|"+cardPhotoDesc, ""));
		fs.setUpdateAt(now);
		fs.setUpdateBy(uid);
		return fileStoreBiz.saveFileStore(fs);
	}
	
	@Override
	public FileStore delete(Long fileStoreId, Long uid) {
		FileStore fs = fileStoreBiz.findById(fileStoreId);
		fileBiz.deleteFile(fs.getStorePath());
		return fileStoreBiz.delete(fileStoreId);
	}
	
	@Override
	public FileStore saveCardMapPic(Long cardId, MultipartFile mfile, Long uid) {
		List<FileStore> lst = fileStoreBiz.listFileStore(Biz.CARD_MAPPIC_FILE_STORE, cardId+"");
		if (!CollectionUtils.isEmpty(lst)) {
			for (FileStore tmp : lst) {
				delete(tmp.getId(), uid);
			}
		}
		String originalFilename = mfile.getOriginalFilename();
		String path;
		try {
			path = fileBiz.saveMultipartFile(mfile, "/card/"+cardId);
		} catch (Exception e) {
			throw new AppException(ErrorCode.SERVER_ERROR);
		}
		
		Date now = new Date();
		
		FileStore fileStore = new FileStore();
		fileStore.setBiz(Biz.CARD_MAPPIC_FILE_STORE.name());
		fileStore.setCreateAt(now);
		fileStore.setCreateBy(uid);
		fileStore.setDescription("");
		fileStore.setJson("");
		fileStore.setOriginalName(mfile.getOriginalFilename());
		fileStore.setOwner(cardId+"");
		fileStore.setRank(0L);
		fileStore.setSize(mfile.getSize());
		fileStore.setStatus(Status.NORMAL);
		fileStore.setStorePath(path);
		fileStore.setSuffixName(FileUtil.getPathOrUrlSuffix(originalFilename));
		fileStore.setType(getFileType4Filename(originalFilename));
		fileStore.setUpdateAt(now);
		fileStore.setUpdateBy(uid);
		
		return fileStoreBiz.saveFileStore(fileStore);
	}
	
	@Override
	public FileStore findCardMappic(Long cardId) {
		List<FileStore> lst = fileStoreBiz.listFileStore(Biz.CARD_MAPPIC_FILE_STORE, cardId+"");
		return CollectionUtils.isEmpty(lst) ? null : lst.get(0);
	}
}
