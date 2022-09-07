package net.koreate.file.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

// 이미지로 판별할 MIME type을 저장해놓을 객체
public class MediaUtils {
		
	{			// 인스턴스 생성 직후 시행되는 블록 (호출이 불가하므로 인스턴스 생성 시) // 그냥 공부하라고 넣었음 아무 역할없는 코드
		
	}
	
	private static Map<String, MediaType> mediaMap;	
	
	static {	 // 클래스 정보가 메소드 영역(static)에 등록이 되고 나면 즉시 시행됨
		mediaMap = new HashMap<>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("JPEG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	public static MediaType getMediaType(String ext) {
		return mediaMap.get(ext.toUpperCase());		
	}
		
}