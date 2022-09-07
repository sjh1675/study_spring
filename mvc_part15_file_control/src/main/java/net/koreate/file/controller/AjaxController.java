package net.koreate.file.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import net.koreate.file.utils.FileUtils;

@RestController
@RequiredArgsConstructor
public class AjaxController {

	private final String uploadFolder;
	private final ServletContext context;
	
	String realPath;
	
	@PostConstruct	// 빈이 등록되고 난 후 사용할 준비가 완료되었을 때 최초 한번에 실행되어 필요한 값을 초기화
	public void initPath() {
		realPath = context.getRealPath(File.separator + uploadFolder);	// File.separator : 운영체제에 맞는 구분자가 된다  >> "\\"
		System.out.println("realPath : " + realPath);
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();
			System.out.println("디렉토리 생성 완료");
		}
		System.out.println("사용 준비 완료");
	}
	
	@PostMapping("uploadAjax")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		String original = file.getOriginalFilename();
		System.out.println("fileName");
		/*
		file.transferTo(new File(realPath, original));
		 
		String path = FileUtils.calcPath(realPath); System.out.println("path : " +
		path);
		 */
		
		String savedName = FileUtils.uploadFile(realPath, file);

		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/plain;charset=utf-8");
		ResponseEntity<String> entity = new ResponseEntity<>(savedName, header, HttpStatus.OK);
		return entity;
	}
	
	// 파일삭제요청 처리
	@DeleteMapping("deleteFile")
	public ResponseEntity<String> deleteFile(String fileName) throws Exception {
		ResponseEntity<String> entity = null;
		boolean isDeleted = FileUtils.deleteFile(realPath, fileName);
		
		if (isDeleted) {
			entity = new ResponseEntity<>("DELETED", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@PostMapping("uploadFiles")
	public ResponseEntity<List<String>> uploadFiles(List<MultipartFile> files) throws Exception {
		List<String> names = new ArrayList<>();
		
		for (MultipartFile m : files) {
			names.add(FileUtils.uploadFile(realPath, m));
		}
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<>(names, header, HttpStatus.OK);
	}
	
	@GetMapping("downloadFile")
	public ResponseEntity<byte[]> uploadFile(String fileName) throws Exception {
		// 바이트배열을 그냥 엔티티에 담아서 던져주면 파일인지 몰라서 json으로 받는다
		// 이럴 때 헤더 정보를 추가하여 파일임을 알려줘야 파일로 전달할 수 있다
		// 헤더 정보를 이미지로 변경하여 브라우저에 바로 이미지를 출력할 수도 있다
		
		return new ResponseEntity<>(FileUtils.getBytes(realPath, fileName), FileUtils.getOctetHeaders(fileName), HttpStatus.CREATED);
	}

}