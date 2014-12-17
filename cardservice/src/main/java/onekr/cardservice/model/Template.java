package onekr.cardservice.model;

import onekr.framework.type.LabeledEnum;

public enum Template implements LabeledEnum{

	t10001("t10001.海洋之心"),
	t10002("t10002.幸福时光"),
	t10003("t10003.喜结连理"),
	t10004("t10004.粉红之恋"),
	t10005("t10005.喜结良缘"),
	t10006("t10006.异国情怀"),
	t10007("t10007.繁华盛开"),
	t10008("t10008.浪漫英伦"),
	t10009("t10009.罗马假日"),
	t10010("t10010.盛夏果实"),
	t10011("t10011.良辰美景"),
	t10012("t10012.初春物语"),
	t10013("t10013.公主嫁到"),
	t10014("t10014.bilingbiling"),
	t10015("t10015.凤鸾和鸣"),
	t10016("t10016.紫为有你");
	
	String label;

	Template(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
