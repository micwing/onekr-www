package onekr.biz.model;

import onekr.framework.type.LabeledEnum;

/**
 * 文件类型枚举
 */
public enum FileType implements LabeledEnum {
	OTHER("其他"),
	IMAGE("图片"),
	DOC("文档"),
	AUDIO("音频"),
	VIDEO("视频"),
	PACKAGE("压缩文件");
	
	String label;
	
	FileType(String label){
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
