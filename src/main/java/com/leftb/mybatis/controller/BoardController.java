package com.leftb.mybatis.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leftb.mybatis.dao.BoardDao;

@Controller
public class BoardController {

	@Autowired //의존 자동 주입
	private SqlSession sqlSession;

	@RequestMapping(value = {"/", "/list"})
	public String list(Model model) {

		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		model.addAttribute("list", dao.list());
		model.addAttribute("total", dao.list().size());

		return "list";
	}

	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "write_form";
	}

	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request, Model model) {

		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.write(request.getParameter("bname"),
				request.getParameter("btitle"),
				request.getParameter("bcontent"));

		return "redirect:list";
	}

	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request, Model model) {

		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		String bid = request.getParameter("bid");
		dao.uphit(bid);
		model.addAttribute("dto", dao.content(bid));

		return "view";
	}

	@RequestMapping(value = "/modify_form")
	public String modify_form(HttpServletRequest request, Model model) {

		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		model.addAttribute("dto", dao.content(request.getParameter("bid")));

		return "modify_form";
	}

	@RequestMapping(value = "/modify")
	public String modify(HttpServletRequest request, Model model) {

		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.modify(request.getParameter("bid"),
				request.getParameter("btitle"),
				request.getParameter("bcontent"));

		return "redirect:list";
	}

	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model) {

		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.delete(request.getParameter("bid"));

		return "redirect:list";
	}
}
