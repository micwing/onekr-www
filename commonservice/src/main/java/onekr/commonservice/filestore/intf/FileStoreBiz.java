package onekr.commonservice.filestore.intf;

import java.util.List;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.model.FileStore;


public interface FileStoreBiz {

	FileStore saveFileStore(FileStore fileStore);
	
	List<FileStore> listFileStore(Biz biz, String owner);
}
