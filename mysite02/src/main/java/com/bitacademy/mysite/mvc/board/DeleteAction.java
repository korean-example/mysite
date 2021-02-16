package com.bitacademy.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.UserVo;
import com.bitacademy.web.mvc.Action;
import com.bitacademy.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		UserVo userVo = null;
		
		if(session != null) {
			userVo = (UserVo) session.getAttribute("authUser");
		}
		
		if (userVo == null) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		} else {
			BoardVo boardVo = new BoardVo();
			boardVo.setUserName(request.getParameter("name"));
			boardVo.setNo(Long.parseLong(request.getParameter("no")));
			new BoardRepository().delete(boardVo);
			
			WebUtil.redirect(request, response, request.getContextPath() + "/board?page=" + request.getParameter("page"));
		}
	}

}
