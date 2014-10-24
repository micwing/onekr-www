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
	public FileStore saveCardFile(Long cardId, MultipartFile mfile, Long uid) {
		String originalFilename = mfile.getOriginalFilename();
		
		String path;
		try {
			path = fileBiz.saveMultipartFile(mfile);
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
		fileStore.setStoreName(path);
		fileStore.setSuffixName(FileUtil.getPathOrUrlSuffix(originalFilename));
		fileStore.setType(getFileType4Filename(originalFilename));
		fileStore.setUpdateBy(now);
		fileStore.setUserId(uid);
		
		return fileStoreBiz.saveFileStore(fileStore);
	}
	
	private FileType getFileType4Filename(String filename) {
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

}
