//package onekr.cardservice.card.impl;
//
//import java.io.File;
//import java.util.Date;
//import java.util.List;
//
//import onekr.cardservice.card.dao.CardDao;
//import onekr.cardservice.card.intf.Card2dcodeFileBiz;
//import onekr.cardservice.utils.Converter;
//import onekr.commonservice.biz.Biz;
//import onekr.commonservice.filestore.intf.FileBiz;
//import onekr.commonservice.filestore.intf.FileStoreBiz;
//import onekr.commonservice.model.FileStore;
//import onekr.commonservice.model.Status;
//import onekr.framework.exception.AppException;
//import onekr.framework.exception.ErrorCode;
//import onekr.framework.utils.FileUtil;
//import onekr.framework.utils.PRUtil;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class Card2dcodeFileBizImpl implements Card2dcodeFileBiz {
//
//	@Autowired
//	private CardDao cardDao;
//	@Autowired
//	private FileBiz fileBiz;
//	@Autowired
//	private FileStoreBiz fileStoreBiz;
//
//	@Override
//	public FileStore generate2dcode(Long cardId, Long uid) {
//		PRUtil.encodePR(contents, 300, 300, imgPath);
//		
//		
//		
//		String originalFilename = mfile.getOriginalFilename();
//		String path = File.separator+"card"+File.separator+cardId;
//		String name;
//		try {
//			name = fileBiz.saveMultipartFile(mfile, path);
//		} catch (Exception e) {
//			throw new AppException(ErrorCode.SERVER_ERROR);
//		}
//		
//		Date now = new Date();
//		
//		FileStore fileStore = new FileStore();
//		fileStore.setBiz(Biz.CARD_MUSIC_FILE_STORE.name());
//		fileStore.setCreateAt(now);
//		fileStore.setCreateBy(uid);
//		fileStore.setDescription("");
//		fileStore.setOriginalName("2dcode.jpg");
//		fileStore.setOwner(cardId+"");
//		fileStore.setRank(0L);
//		fileStore.setSize(mfile.getSize());
//		fileStore.setStatus(Status.NORMAL);
//		fileStore.setStorePath(path+File.separator+name);
//		fileStore.setSuffixName(FileUtil.getPathOrUrlSuffix(originalFilename));
//		fileStore.setType(Converter.getFileType(originalFilename));
//		fileStore.setUpdateAt(now);
//		fileStore.setUpdateBy(uid);
//		
//		return fileStoreBiz.saveFileStore(fileStore);
//	}
//	
//	@Override
//	public FileStore get2dcodeFileStore(Long cardId, Long uid) {
//		List<FileStore> list = fileStoreBiz.listFileStore(Biz.CARD_2DCODE_FILE_STORE, cardId+"");
//		return list == null ? null : list.get(0);
//	}
//}
