package com.bitacademy.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.web.mvc.Action;
import com.bitacademy.web.util.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.valueOf(request.getParameter("page"));
		List<BoardVo> boardlist = new BoardRepository().fetch(page);
		request.setAttribute("boardList", boardlist);
		request.setAttribute("pageLimit", (int)Math.ceil(new BoardRepository().getBoardVoCount() / 5.0));
		request.setAttribute("index", Math.round(page / 6) * 5 + 1);
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}
}
