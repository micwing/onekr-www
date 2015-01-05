package onekr.cardservice.model;

import onekr.framework.type.LabeledEnum;

public enum Template implements LabeledEnum{
	//t10jsp模板+css模板|模板名称|展示请柬id|价格|是否热门
	t10001("t10001.海洋之心",  1,      58, true),
	t10002("t10002.幸福时光",  17,     58, false),
	t10003("t10003.喜结连理",  18,     58, false),
	t10004("t10004.粉红之恋",  19,     58, false),
	t10005("t10005.默认主题",  20,     58, false),
	t10006("t10006.异国情怀",  6,      58, true),
	t10007("t10007.繁华盛开",  7,      58, true),
	t10008("t10008.浪漫英伦",  8,      58, true),
	t10009("t10009.罗马假日",  9,      58, true),
	t10010("t10010.盛夏果实",  10,     58, true),
	t10011("t10011.良辰美景",  11,     58, true),
	t10012("t10012.初春物语",  12,     58, false),
	t10013("t10013.公主嫁到",  13,     58, false),
	t10014("t10014.bilingbiling",14, 58, false),
	t10015("t10015.凤鸾和鸣",  15,     58, false),
	t10016("t10016.紫为有你",  16,     58, true);
	
	/*
	
	t20017("t20017.XXX"),
	
	t20018("t20018.XXX"),
	
	 */
	
	String label;
	Integer exampleOrderId;
	Integer price;
	Boolean isHot;

	Template(String label, Integer exampleOrderId, Integer price, Boolean isHot) {
		this.label = label;
		this.exampleOrderId = exampleOrderId;
		this.price = price;
		this.isHot = isHot;
	}

	@Override
	public String getLabel() {
		return label;
	}
	
	public Integer getExampleOrderId() {
		return exampleOrderId;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public Boolean getIsHot() {
		return isHot;
	}
}
