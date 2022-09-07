package com.koreate.betty.domain.member.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.koreate.betty.domain.member.dao.MemberCardRepository;
import com.koreate.betty.domain.member.dao.MemberRepository;
import com.koreate.betty.domain.member.dto.form.PointForm;
import com.koreate.betty.domain.member.dto.form.UpdateForm;
import com.koreate.betty.domain.member.vo.ChkLog;
import com.koreate.betty.domain.member.vo.Member;
import com.koreate.betty.domain.member.vo.MemberCard;
import com.koreate.betty.global.error.exception.NotFoundIdException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.koreate.file.utils.MediaUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

	private final MemberRepository memberRepository;
	private final MemberCardRepository memberCardRepository;
	private final ServletContext context;
	
	public Member findOne(String id) {
		Member findMember = memberRepository.findOne(id);
		if (findMember == null) {
			throw new NotFoundIdException();
		}
		return findMember;
	}
	
	public int changePw(String id, String pw) {
		int result = 0;
		
		result = memberRepository.updatePw(id, pw);
		
		return result;
	}
	
	public List<Member> findAll() {
		List<Member> list = memberRepository.findAll();
		return list;
	}

	public int deleteMember(String id) {
		int result = 0;
		
		result = memberRepository.delete(id);
		
		return result;
	}

	public Member updateMember(UpdateForm form) {
		Member member = form.createMember();
		imgUpload(form.getId(), form.getImg());
		memberRepository.update(member);
		return memberRepository.findOne(form.getId());
	}

	public int addPoint(PointForm form) {
		String id = form.getId();
		int point = form.getPoint();
		int result = memberCardRepository.updatePoint(id, point);
		return result;
	}

	public int findPointById(String id) {
		int point = memberCardRepository.findPointById(id);
		return point;
	}
	
	public List<ChkLog> findMyChkLog(String id) {
		return memberRepository.findMyChkLog(id);
	}
	
//	src\main\webapp\resources\img\member\origin
	public boolean imgUpload(String targetId, MultipartFile img) {
		log.info("img Upload Called");
		
		boolean uploaded = false;
		
		String originalName = img.getOriginalFilename();
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		log.info("\n\n\n\n originalName : {}", originalName);
		log.info("\n\n\n\n formatName : {}", formatName);
		
		if (MediaUtils.getMediaType(formatName) == null) { // 이미지 파일이 아닐 경우
			return uploaded;
		}
		
		String sp = File.separator;
		String originPath = context.getRealPath(sp+"resources"+sp+"img"+sp+"member"+sp+"origin");
		String middlePath = context.getRealPath(sp+"resources"+sp+"img"+sp+"member"+sp+"middle");
		String thumbPath = context.getRealPath(sp+"resources"+sp+"img"+sp+"member"+sp+"thumbnail");
		log.info("origin name = {}", originalName);
		log.info("originPath = {}", originPath);
		
		String imgName = targetId + "_" + originalName; 
		
		File file = new File(originPath, imgName);
		
		try {
			img.transferTo(file); // 오리진에 넣음			
			
			BufferedImage originImg = ImageIO.read(file);			
						
			BufferedImage middleImg = Thumbnails.of(originImg).size(300, 300).keepAspectRatio(false).asBufferedImage();
			BufferedImage thumbImg = Thumbnails.of(originImg).size(50, 50).keepAspectRatio(false).asBufferedImage();
			
			File mFile = new File(middlePath, imgName);
			ImageIO.write(middleImg, formatName, mFile);
			File tFile = new File(thumbPath, imgName);			
			ImageIO.write(thumbImg, formatName, tFile);
			
			uploaded = true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return uploaded;
	}

	public int updateMembership(String id, String membershipGrade) {
		return memberCardRepository.updateGrade(id, membershipGrade);
	}

	public MemberCard findGradeById(String id) {
		return memberCardRepository.findOne(id);
	}
	
	public int updateLend(String id, String grade) {
		return memberCardRepository.updateGrade(id, grade);
	}
}