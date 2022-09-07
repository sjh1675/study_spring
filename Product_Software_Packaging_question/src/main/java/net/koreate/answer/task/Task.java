package net.koreate.answer.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {
	
	@Scheduled(cron="0 15 * * * *")
	public void fileCheck() throws Exception {
		System.out.println("매시 15분 0초에 콘솔에 출력됩니다.");
	}
}
