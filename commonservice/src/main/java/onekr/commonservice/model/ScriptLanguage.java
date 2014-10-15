package onekr.commonservice.model;

import onekr.framework.type.LabeledEnum;


public enum ScriptLanguage implements LabeledEnum {
	GROOVY("groovy");
	
	String label;
	
	ScriptLanguage(String label){
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
