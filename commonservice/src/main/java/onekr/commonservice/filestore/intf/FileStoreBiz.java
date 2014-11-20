package onekr.commonservice.filestore.intf;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.model.FileStore;

@Validated
public interface FileStoreBiz {

	FileStore saveFileStore(@NotNull FileStore fileStore);
	
	List<FileStore> listFileStore(@NotNull Biz biz);
	
	List<FileStore> listFileStore(@NotNull Biz biz, @NotNull String owner);
	
	FileStore findById(@NotNull @Min(1) Long fileStoreId);
	
	FileStore delete(@NotNull @Min(1) Long fileStoreId);
}
