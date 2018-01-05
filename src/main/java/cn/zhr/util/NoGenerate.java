package cn.zhr.util;

public class NoGenerate {

	public static String getNoPrefix(){
		return DateUtil.getNoSpSysDateString();
//				+DateUtil.getNoSpSysTimeString();
	}
	
	public static String getNo(String no){
		Integer noPrefix = Integer.parseInt(getNoPrefix());
		if(no.length()!=12){
			return noPrefix+"0000";
		}
		Integer prefix = Integer.parseInt(no.substring(0, 8));
		Integer suffix = Integer.parseInt(no.substring(8))+1;
		if(noPrefix>prefix){
			return noPrefix+"0000";
		}else if(noPrefix==prefix){
			return noPrefix+suffix+"";
		}else{
			return prefix+"0000";
		}
	}
}
