package onekr.commonservice.filestore.dao;

import onekr.commonservice.model.FileStore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStoreDao extends JpaRepository<FileStore, Long> {
}
