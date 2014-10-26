package onekr.cardservice.model;

import onekr.framework.type.LabeledEnum;

public enum CardType implements LabeledEnum {
	/**
	 * 新人婚礼请柬
	 */
	WED_CARD("新人婚礼请柬"),

	/**
	 * 满月百天请柬
	 */
	BABY_CARD("满月百天请柬"),

	/**
	 * 寿星生日请柬
	 */
	BIRTHDAY_CARD("寿星生日请柬");

	String label;

	CardType(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
