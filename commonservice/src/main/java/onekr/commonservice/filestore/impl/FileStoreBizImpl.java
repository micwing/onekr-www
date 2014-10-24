package onekr.commonservice.filestore.impl;

import java.util.List;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.filestore.dao.FileStoreDao;
import onekr.commonservice.filestore.intf.FileStoreBiz;
import onekr.commonservice.model.FileStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileStoreBizImpl implements FileStoreBiz {
	
	@Autowired
	private FileStoreDao fileStoreDao;

	@Override
	public FileStore saveFileStore(FileStore fileStore) {
		FileStore entity;
		if (fileStore.getId() == null) {
			entity = new FileStore();
		} else {
			entity = fileStoreDao.findOne(fileStore.getId());
		}
		//TODO
		return null;
	}

	@Override
	public List<FileStore> listFileStore(Biz biz, String owner) {
		// TODO Auto-generated method stub
		return null;
	}

}
