package com.jyd.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jyd.common.Constrant;
import com.jyd.common.Tools;

public class JydfFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			String menuIndex = request.getParameter("m");
			if(StringUtils.isEmpty(menuIndex)){
				menuIndex=Constrant.menu_index_flist;
			}
			String uuid = Tools.readCookie(request, Constrant.ROLE_COOKIE_FLAG);
			if(StringUtils.isNotEmpty(uuid)){
				Tools.writeCookie(response, Constrant.ROLE_COOKIE_FLAG, uuid,Constrant.COOKIE_EXPIRES_TIME);//role的md5值
				if(null!=Constrant.permissionsMap.get(uuid))
					Tools.writeCookie(response, Constrant.P_COOKIE_FLAG, StringUtils.join(Constrant.permissionsMap.get(uuid), ","),Constrant.COOKIE_EXPIRES_TIME);//role的md5值
			}
			request.setAttribute("m", menuIndex);
			filterChain.doFilter(request, response);
	}

}
