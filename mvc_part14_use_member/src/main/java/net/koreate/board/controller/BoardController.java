package net.koreate.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import net.koreate.board.service.BoardService;
import net.koreate.board.vo.BoardVO;
import net.koreate.common.utils.PageMaker;
import net.koreate.common.utils.SearchCriteria;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService bs;
	
	// 게시글 목록 페이지
	@GetMapping("listReply")
	public String listReply(SearchCriteria cri, Model model) throws Exception {
		List<BoardVO> list = bs.listReply(cri);
		PageMaker pm = bs.getPageMaker(cri);
		model.addAttribute("list", list);
		model.addAttribute("pm", pm);
		return "board/listReply";
	}
	
	// 게시글 작성 페이지
	@GetMapping("register")
	public String register() {
		return "board/register";
	}
	
	@PostMapping("register")
	public String register(BoardVO vo) throws Exception {
		System.out.println("register : " + vo);
		bs.register(vo);
		return "redirect:/board/listReply";
	}
		
	@GetMapping("readPage")
	public String readPage(int bno, RedirectAttributes rttr) throws Exception {
		bs.updateCnt(bno);
		rttr.addAttribute("bno", bno);
		return "redirect:/board/read";
	}
	
	@GetMapping("read")
	public String read(int bno, Model model) throws Exception {
		BoardVO vo = bs.read(bno);
		model.addAttribute("board", vo);
		return "board/readPage";
	}
	
	@GetMapping("replyRegister")
	public String replyRegister(int bno, Model model) throws Exception {
		BoardVO origin = bs.read(bno);
		model.addAttribute("original", origin);
		return "board/replyRegister";
	}
	
	@PostMapping("replyRegister")
	public String replyRegister(BoardVO vo) throws Exception {
		bs.replyRegister(vo);
		return "redirect:/board/listReply";
	}
	
	@PostMapping("remove")
	public String remove(int bno) throws Exception {
		bs.remove(bno);
		return "redirect:/board/listReply";
	}
	
	@GetMapping("modifyPage")
	public String modifyPage(int bno, Model model) throws Exception {
		BoardVO vo = bs.read(bno);
		model.addAttribute("board", vo);
		return "board/modifyPage";
	}
	
	// 게시글 수정 완료
	@PostMapping("modifyPage")
	public String modifyPage(BoardVO vo, RedirectAttributes rttr) throws Exception {
		bs.modify(vo);
		rttr.addAttribute("bno", vo.getBno());	// get 방식으로 파라미터 값이 전달되어, 밑의 리턴의 끝에 ? 쿼리스트링에 파라미터가 전달됨
		
		
		return "redirect:/board/read";
	}
}
