package onekr.commonservice.filestore.impl;

import java.util.Date;
import java.util.List;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.filestore.dao.FileStoreDao;
import onekr.commonservice.filestore.intf.FileStoreBiz;
import onekr.commonservice.model.FileStore;
import onekr.commonservice.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileStoreBizImpl implements FileStoreBiz {
	
	@Autowired
	private FileStoreDao fileStoreDao;

	@Override
	public FileStore saveFileStore(FileStore fileStore) {
		if (fileStore.getStatus() == null)
			fileStore.setStatus(Status.NORMAL);
		
		Date now = new Date();
		FileStore entity;
		if (fileStore.getId() == null) {
			entity = new FileStore();
			entity.setCreateAt(now);
			entity.setCreateBy(fileStore.getCreateBy());
		} else {
			entity = fileStoreDao.findOne(fileStore.getId());
		}
		entity.setBiz(fileStore.getBiz());
		
		entity.setDescription(fileStore.getDescription());
		entity.setJson(fileStore.getJson());
		entity.setOriginalName(fileStore.getOriginalName());
		entity.setOwner(fileStore.getOwner());
		entity.setRank(fileStore.getRank());
		entity.setSize(fileStore.getSize());
		entity.setStatus(fileStore.getStatus());
		entity.setStorePath(fileStore.getStorePath());
		entity.setSuffixName(fileStore.getSuffixName());
		entity.setType(fileStore.getType());
		
		entity.setUpdateAt(now);
		entity.setUpdateBy(fileStore.getUpdateBy());
		
		return fileStoreDao.save(entity);
	}

	@Override
	public List<FileStore> listFileStore(Biz biz, String owner) {
		List<FileStore> list = fileStoreDao.findByBizAndOwnerOrderByRankDesc(biz.name(), owner);
		return list;
	}

}
