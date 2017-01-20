package com.jyd.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jyd.beans.OperLog;
import com.jyd.beans.UserInfor;
import com.jyd.common.Constrant;
import com.jyd.common.Tools;
import com.jyd.service.SyLogService;
import com.jyd.vo.PageModel;

@Controller
@RequestMapping(value="/log")
public class SyslogController {
	@Autowired
	private SyLogService syLogService;
	
	
	public SyLogService getSyLogService() {
		return syLogService;
	}


	public void setSyLogService(SyLogService syLogService) {
		this.syLogService = syLogService;
	}


	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request, String keyWord,
			String start) {
		int curPage=0;
		OperLog log = new OperLog();
		log.setOperUserName(keyWord);
		if (StringUtils.isEmpty(start) || !StringUtils.isNumeric(start)) {
			log.setStart(0);
		} else {
			log.setStart(Integer.valueOf(start));
			curPage=Integer.valueOf(start);
		}

		log.setPageSize(Constrant.MAX_PAGE_SIZE);
		ModelAndView model = new ModelAndView();
		model.setViewName("log");

		String loginUserId = Tools.readCookie(request,
				Constrant.ROLE_COOKIE_FLAG);
		if (StringUtils.isEmpty(loginUserId)) {
			model.setViewName("timer");
			return model;
		}
		UserInfor loginUser = Constrant.loginUserMap.get(loginUserId);
		if (null == loginUser) {
			model.setViewName("timer");
			return model;
		}
		log.setOperUserNum(loginUser.getNum());// 数据权限控制

		PageModel pager = syLogService.list(log);
		pager.setCurPage(curPage);
		model.addObject("pager", pager);
		model.addObject("keyWord", keyWord);
		return model;
	}
}
