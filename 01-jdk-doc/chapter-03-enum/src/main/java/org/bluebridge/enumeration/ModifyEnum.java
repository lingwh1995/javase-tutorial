package org.bluebridge.enumeration;

/**
 * 访问修饰符枚举
 */
public enum ModifyEnum {

	PUBLIC(1,"公有的"),
	PRIVATE(2,"私有的");
	
	private final Integer code;
	private final String desc;
	
	ModifyEnum(Integer code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public Integer getCode(){
		return code;
	}
	
	public String getDesc(){
		return desc;
	}
}