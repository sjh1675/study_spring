package net.koreate.common.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import net.koreate.board.dao.AttachmentDAO;

@Component
@RequiredArgsConstructor
public class FileCheckTask {
	
	private final String uploadFolder;
	private final ServletContext context;
	private final AttachmentDAO dao;
	
	// cron schedule
	
	// 스케쥴 어노테이션
//	@Scheduled(cron="* * * * * *")
	public void testTask() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(new Date()));
	}
	// @Scheduled(cron="0 0 4 * * *")	// 매일 4시 0분 0초
	@Scheduled(cron="0 * * * * *")	// 매분 0초
	public void fileCheck() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		long time = 1000*60*60*24;
		String datePath = sdf.format(new Date(System.currentTimeMillis() - time));
		datePath = datePath.replace('/', File.separatorChar);
		String realPath = context.getRealPath(File.separator+uploadFolder);
		List<String> list = dao.getTrashAttach();
		System.out.println(list);		
		removeList(realPath, datePath, list);
	}
	
	// uploadForder, 년월일, 테이블에 저장된 파일 이름 목록
	public void removeList(String realPath, String datePath, List<String> list) {
		// 삭제할 리스트 목록
		List<String> removeFiles = new ArrayList<>(); 
		
		File file = new File(realPath, datePath);
		if (file.exists()) {
			List<File> files = Arrays.asList(file.listFiles());
			for (File f : files) {
				String fileName = f.getName();
				System.out.println(fileName);
				datePath = datePath.replace(File.separatorChar, '/');		// 운영체제 경로에서 url 경로로 변경
				String filePath = datePath + fileName;
				String thumbnail = datePath + "s_" + fileName;
				if (!list.contains(filePath) && !list.contains(thumbnail)) {
					removeFiles.add(filePath);
				}
			}
			
			for (String s : removeFiles) {
				System.out.println("remove file : " + s);
				new File(realPath + (s).replace('/', File.separatorChar)).delete();
			}
		}
	}
}