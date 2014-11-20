package onekr.cardservice.model;

import onekr.framework.type.LabeledEnum;

public enum Template implements LabeledEnum{

	t10006("WEDDING DAY"),
	
	t10007("喜结良缘");
	
	String label;

	Template(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
