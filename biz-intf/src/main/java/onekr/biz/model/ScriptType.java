package onekr.biz.model;

import onekr.framework.type.LabeledEnum;


public enum ScriptType implements LabeledEnum {
	ARTICLE("article"),
	URL("url");
	
	String label;
	
	ScriptType(String label){
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
