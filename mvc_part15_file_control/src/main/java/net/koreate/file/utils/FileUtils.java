package net.koreate.file.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	public static String uploadFile(String realPath, MultipartFile file) throws Exception {
		String uploadFileName = "";
		
		UUID uid = UUID.randomUUID();
		String originalName = file.getOriginalFilename();
		String savedName = uid.toString().replace("-", "");
		savedName += "_" + originalName.replace("_", " ");
		System.out.println(savedName);
		// \2022\08\04
		String datePath = calcPath(realPath);
		File f = new File(realPath + datePath, savedName);
		file.transferTo(f);
		
		// 업로드 된 파일의 확장자
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		System.out.println(formatName);
		if (MediaUtils.getMediaType(formatName) != null) {
			// 이미지 파일임
			uploadFileName = makeThumbnail(realPath, datePath, savedName);			
		} else {
			// 이미지 파일이 아님
			uploadFileName = makeFileName(realPath, datePath, savedName);			
		}
		
		System.out.println("uploadFileName : " + uploadFileName);
		
		return uploadFileName;
	}

	// 썸네일 생성후 URL 경로로 썸네일 이미지 경로 반환
	private static String makeThumbnail(String realPath, String datePath, String savedName) throws IOException{
		String name = "";
		
		File file = new File(realPath + datePath, savedName);
		// 해당 경로의 이미지 정보를 BufferedImage 타입으로 변환
		// ImageIO : 이미지 전용 입출력 클래스
		BufferedImage image = ImageIO.read(file);
		
		// scalr 객체를 이용해서 원본 이미지 복제
		// 복제시 크기 지정
		BufferedImage sourceImage = Scalr.resize(image, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);	// 이미지, 폭(이미지 크기에 맞게), 너비방식, 너비 수치(높이를 고정 지정 후 100으로 고정) // 비율에 맞게 자동
		
		String thumbnailImage = realPath + datePath + File.separator + "s_" + savedName;
		
		String ext = savedName.substring(savedName.lastIndexOf(".") +1);
		
		ImageIO.write(sourceImage, ext, new File(thumbnailImage));	// 이미지, 확장자, 경로와 파일명
		
		// URL 경로로 변경
		name = thumbnailImage.substring(realPath.length()).replace(File.separatorChar, '/');
		
		return name;
	}
	
	// URL 경로로 변환 >> \\ 을 /으로 변경 
	private static String makeFileName(String realPath, String datePath, String savedName) {
		String fileName = "";
		fileName = datePath + File.separator + savedName;
		fileName = fileName.replace(File.separatorChar, '/');
		
		return fileName;
	}


	public static String calcPath(String realPath) {
		String datePath = "";
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		System.out.println(datePath);
		mkDir(realPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	// 날짜 형식의 디렉토리 생성
	public static void mkDir(String realPath, String... path) {
		if (new File(realPath + path[path.length-1]).exists()) {
			return;
		}
		for (String p : path) {
			String mkDir = realPath + p;
			System.out.println("mkDir : " + mkDir);
			File file = new File(mkDir);
			if(!file.exists()) {
				file.mkdirs();
			}			
		}
	}
	
	public static boolean deleteFile(String realPath, String fileName) throws Exception {
		boolean isDeleted = false;
		// 이미지 파일의 경우 -> 원본과 썸네일 삭제
		
		// 원본 삭제
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		fileName = fileName.replace('/', File.separatorChar);
		isDeleted = new File(realPath + fileName).delete();
		
		if (MediaUtils.getMediaType(formatName) != null) {
			// 이미지일 경우 썸네일 삭제
			fileName = fileName.replace("s_", "");
			isDeleted = new File(realPath + fileName).delete();
		}
		
		return isDeleted;
	}
	
	// 요청한 파일 정보를 byte[] 로 변환
	public static byte[] getBytes(String realPath, String fileName) throws Exception {
		File file = new File(realPath, fileName);
		InputStream is = new FileInputStream(file);
		/*
		long length = file.length();
		length = is.available();	// 둘 중 아무거나 사용
		byte[] bytes = new byte[(int)length];
		
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte)is.read();
		}		
		is.close();
		return bytes;
		*/			
		return IOUtils.toByteArray(is);	// BufferedReader(주석보다 빠름) Stream으로 위의 주석처리 된 작업을 하여 바이트배열로 변환해줌
	}
	
	// 다운로드 파일 헤더 정보
	public static HttpHeaders getOctetHeaders(String fileName) throws Exception {
		HttpHeaders header = new HttpHeaders();
//		header.add("Content-Type", "application/octet-stream");	// octet : 8비트의 배열, 8bit = 1byte단위의 이진 데이터 // 파일로 전송됨을 의미
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 위 혹은 밑중 아무거나 사용
		
		fileName = fileName.substring(fileName.lastIndexOf("_") + 1);	// 파일전체이름(경로+파일) 뒤에서 찾은 _ 이후의 파일명이 원본 파일명
		
		// 부가 정보
		// URL은 인코딩이 ISO-8859-1이기 때문에 변환
		// attachment;fileName=.. 지정한 이름으로 파일을 다운로드 시켜준다. (파일의 실제 경로가 노출되지 않도록 함)
		// fileName의 값으로 String "..." 이 들어가는데 "를 표기하기 위해 \ 사용
		header.add("Content-disposition", "attachment;fileName=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
		
		return header;
	}
	
}