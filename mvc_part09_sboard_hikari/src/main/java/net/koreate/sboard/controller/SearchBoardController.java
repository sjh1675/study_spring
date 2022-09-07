package net.koreate.sboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import net.koreate.common.utils.PageMaker;
import net.koreate.common.utils.SearchCriteria;
import net.koreate.sboard.service.SearchBoardService;
import net.koreate.sboard.vo.SearchBoardVO;

@Controller
@RequestMapping("/sboard/*")
@RequiredArgsConstructor
public class SearchBoardController {
	
	private final SearchBoardService sbs;
	
	@GetMapping("listPage")
	public ModelAndView listPage(
			ModelAndView mav,
			SearchCriteria cri
			)throws Exception{
		mav.setViewName("sboard/listPage");
		System.out.println("listPage : " + cri);
		List<SearchBoardVO> list = sbs.list(cri);
		mav.addObject("list", list);
		PageMaker pm = sbs.getPageMaker(cri);
		mav.addObject("pm", pm);
		return mav;
	}
	
	@GetMapping("register")
	public String register() throws Exception {
		return "sboard/register";
	}
	
	@PostMapping("register")
	public String register(SearchBoardVO vo, RedirectAttributes rttr) throws Exception {
		String msg = sbs.register(vo);
		rttr.addFlashAttribute("result", msg);
		return "redirect:/sboard/listPage";
	}
	
	@GetMapping("readPage")
	public String readPage(HttpServletRequest request, HttpServletResponse response, int bno, @ModelAttribute("cri")SearchCriteria cri) throws Exception {
		SearchBoardVO vo = sbs.read(bno);
//		model.addAttribute("board", vo);
		request.setAttribute("board", vo);
		sbs.updateViewCnt(request, response, bno);
		return "sboard/readPage";
	}
	
	@GetMapping("modifyPage")
	public ModelAndView modifyPage(int bno, ModelAndView mav, @ModelAttribute("cri")SearchCriteria cri) throws Exception {
		mav.addObject("board", sbs.read(bno));
		mav.setViewName("sboard/modifyPage");		
		return mav;
	}
	
	@PostMapping("modifyPage")
	public String modifyPage(SearchBoardVO vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		System.out.println("modifyPage[POST] vo : " + vo);
		System.out.println("modifyPage[POST] cri : " + cri);
		rttr.addAttribute("bno", vo.getBno());
		String msg = sbs.modify(vo);
		rttr.addFlashAttribute("result", msg);
		// rttr.addFlashAttribute("cri", cri);
		// 위의 코드는 데이터 전달용이고, 새로고침시 새 요청을 받을 때 삭제되어 파라미터 전달용인 addAttribute를 사용
		// 파라미터는 문자열만 전달이 되므로 객체를 전달할 수 없어서 하나하나 직접 넘겨준다
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/sboard/readPage";
	}
	
	@PostMapping("removePage")
	public String removePage(int bno, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		
		String msg = sbs.remove(bno);
		rttr.addFlashAttribute("result", msg);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/sboard/listPage";
	}
}
