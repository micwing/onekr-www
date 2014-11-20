package onekr.commonservice.filestore.dao;

import java.util.List;

import onekr.commonservice.model.FileStore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStoreDao extends JpaRepository<FileStore, Long> {
	
	List<FileStore> findByBizOrderByRankDesc(String biz);
	
	List<FileStore> findByBizAndOwnerOrderByRankDesc(String biz, String owner);
}
