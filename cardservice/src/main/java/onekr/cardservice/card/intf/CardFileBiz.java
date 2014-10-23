package onekr.cardservice.card.intf;

import org.springframework.web.multipart.MultipartFile;

import onekr.commonservice.model.FileStore;

public interface CardFileBiz {

	FileStore saveCardFile(MultipartFile mfile);
}
