package onekr.cardservice.model;

import onekr.framework.type.LabeledEnum;

public enum CardType implements LabeledEnum {
	/**
	 * 婚礼请柬
	 */
	WED_CARD("婚礼请柬"),

	/**
	 * 宝宝请柬
	 */
	BABY_CARD("宝宝请柬"),

	/**
	 * 生日请柬
	 */
	BIRTHDAY_CARD("生日请柬");

	String label;

	CardType(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
