package onekr.framework.type;


/** 
 * @ClassName: Gender 
 * @Description: 
 * @author micwing
 * @date 2013-4-2 下午5:13:19 
 */
public enum Gender implements LabeledEnum {
	UNKONW("未知"),
	MALE("男"),
	FEMALE("女");
	
	String label;
	
	Gender(String label){
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
