package com.koreate.betty.domain.offline.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreate.betty.domain.offline.dao.OfflineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfflineService {
	
	private final OfflineRepository offlineRepository;
	
	// 좌석 현황을 확인한다
	public List<Integer> seatStatus(){
		return offlineRepository.seatStatus();
	}
	
	
	// 회원이 번호를 골라 체크인한다		/아이디/시간/자리
	public int checkIn(String id, Integer seat) {
		int result = offlineRepository.checkIn(id, seat);
		return result;
	}
	
	// 회원이 체크아웃한다
	public int checkOut(String id) {
		int result = offlineRepository.checkOut(id);
		return result;
	}
	
	
	
}
