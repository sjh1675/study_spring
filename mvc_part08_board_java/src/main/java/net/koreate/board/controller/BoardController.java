package net.koreate.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import net.koreate.board.service.BoardService;
import net.koreate.board.util.Criteria;
import net.koreate.board.util.PageMaker;
import net.koreate.board.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bs;
	
//	@RequestMapping(value = "/register2", method = RequestMethod.GET)
	@GetMapping("/register")
	public void register() throws Exception {
		
		// 리턴이 void라서 없음
		// -> 요청 정보가 페이지가 된다
		// WEB-INF/views/board/register.jsp
		
		System.out.println("게시글 작성 페이지 요청");
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, 
			// Model model, 
			// HttpSession session
			RedirectAttributes rttr) throws Exception {
		System.out.println(board);
		String result = bs.regist(board);
		System.out.println(result);
		
//		model.addAttribute("msg", result);
//		session.setAttribute("message", result);		
		rttr.addFlashAttribute("result", result);	// 위의 두 줄과 결과가 같음
		
		return "redirect:/board/listAll";
	}
	
	
	@GetMapping("/listAll")
	public void listAll(Model model) throws Exception {
		List<BoardVO> list = bs.listAll();
		model.addAttribute("list", list);
	}
	
	@GetMapping("/read")		// 세션은 새로고침시 조회수 증가를 막기 위함(세션에 정보 기억)
	public void read(
			@RequestParam(name="bno") int bno,
			Model model,
			HttpSession session
			) throws Exception {
		bs.updateCnt(bno, session);
		model.addAttribute("boardVO", bs.read(bno));
	}

	@GetMapping("/modify")
	public void modify(int bno, Model model) throws Exception {
		model.addAttribute("boardVO", bs.read(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardVO board,
			RedirectAttributes rttr
			) throws Exception {
		bs.modify(board);
		rttr.addAttribute("bno", board.getBno()); // 오타의 여지가 적음
		return "redirect:/board/read";
		// return "redirect:/board/read?bno=" + board.getBno() + "&title=" + board.getTitle();
		
	}
	
	@GetMapping("/remove")
	public String remove(int bno, RedirectAttributes rttr) throws Exception {
		bs.remove(bno);
		rttr.addFlashAttribute("result", "삭제완료");
		return "redirect:/board/listAll";
	}
	
	// board/listPage
	// param : 요청 페이지, perPageNum
	@GetMapping("/listPage")
	public void listPage(Criteria cri, Model model) throws Exception {
		List<BoardVO> list = bs.listCriteria(cri);
		PageMaker pm = bs.getPageMaker(cri);
		model.addAttribute("list", list);
		model.addAttribute("pm", pm);
	}
	
	@GetMapping("/readPage")
	public String readPage(int bno, Model model, @ModelAttribute("cri") Criteria cri) throws Exception {
		System.out.println(cri);
		System.out.println(bno);
//		model.addAttribute("cri", cri);	// 생성한 클래스 정보는 모델에 실려서 간다
		// 따라서 Criteria cri 객체가 모델에 들어가는데, 키 값은 클래스 명의 앞글자를 소문자로 하여
		// criteria가 키 값이 된다
		// @ModelAttribute("이름")을 이용하여 키 값을 변경할 수 있다
		// @ModelAttribute를 이용하여 클래스가 아닌 값도 model에 바로 넣을 수 있다
		model.addAttribute("boardVO", bs.read(bno));
		return "/board/readPage";		
	}
	
	@GetMapping("/modifyPage")
	public void modifyPage(int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		model.addAttribute("boardVO", bs.read(bno));
	}
	
	@PostMapping("/modifyPage")
	public String modifyPage(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception {
		bs.modify(board);
		// 파라미터 값 전달 방식
		/*
		 * rttr.addAttribute("page", cri.getPage()); rttr.addAttribute("perPageNum",
		 * cri.getPerPageNum());
		 */
		
		// 데이터 전달 방식
		rttr.addFlashAttribute("cri", cri);
		rttr.addAttribute("bno", board.getBno());
		return "redirect:/board/readPage";
	}
	
	@PostMapping("/removePage")
	public String remove(int bno, Criteria cri, RedirectAttributes rttr) throws Exception {
		bs.remove(bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		return "redirect:/board/listPage";
	}
}
