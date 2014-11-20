package onekr.cardservice.model;

import onekr.framework.type.LabeledEnum;

public enum Template implements LabeledEnum{

	t10001("t10001"),
	t10002("t10002"),
	t10003("t10003"),
	t10004("t10004"),
	t10005("t10005"),
	t10006("t10006"),
	t10007("t10007"),
	t10008("t10008"),
	t10009("t10009"),
	t10010("t10010"),
	t10011("t10011"),
	t10012("t10012"),
	t10013("t10013"),
	
	t10015("t10015"),
	t10016("t10016");
	
	String label;

	Template(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
