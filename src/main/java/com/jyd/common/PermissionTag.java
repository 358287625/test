package com.jyd.common;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

public class PermissionTag extends TagSupport {

	/**
	 * EVAL_BODY_INCLUDE：把Body读入存在的输出流中，doStartTag()函数可用
	 * EVAL_PAGE：继续处理页面，doEndTag()函数可用
	 * SKIP_BODY：忽略对Body的处理，doStartTag()和doAfterBody()函数可用
	 * SKIP_PAGE：忽略对余下页面的处理，doEndTag()函数可用
	 * EVAL_BODY_TAG：已经废止，由EVAL_BODY_BUFFERED取代
	 * EVAL_BODY_BUFFERED：申请缓冲区，由setBodyContent
	 * ()函数得到的BodyContent对象来处理tag的body，如果类实现了BodyTag，那么doStartTag()可用，否则非法
	 */
	private static final long serialVersionUID = -6382874622925054015L;
	Cookie[] hasPers;
	String needPer;

	@Override
	public int doStartTag() {
		String perStr = null;
		if (hasPers == null || hasPers.length <= 0)
			return SKIP_BODY;

		if (StringUtils.isEmpty(needPer))
			return SKIP_BODY;

		for (Cookie cookie : hasPers) {
			if (!Constrant.P_COOKIE_FLAG.equalsIgnoreCase(cookie.getName()))
				continue;
			perStr = cookie.getValue();
			break;
		}
		List<String> ps = null;
		if (perStr != null && perStr.length() > 0)
			ps = Arrays.asList(perStr.split(","));

		if (null == ps || ps.size() <= 0)
			return SKIP_BODY;

		// 如果是管理员，具有一切权限
		int size = ps.size();
		for (int i = 0; i < size; i++) {
			if (Constrant.PERMISSION_ADMIN.equalsIgnoreCase(ps.get(i)))
				return EVAL_BODY_INCLUDE;
		}

		for (int j = 0; j < size; j++) {
			if (needPer.trim().equalsIgnoreCase(ps.get(j).trim()))// 找到一个就可以返回
			{
				System.out.println(ps.get(j).trim() + "--" + needPer);
				return EVAL_BODY_INCLUDE;
			}
		}

		return SKIP_BODY;
	}

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
	}

	public Cookie[] getHasPers() {
		return hasPers;
	}

	public void setHasPers(Cookie[] hasPers) {
		this.hasPers = hasPers;
	}

	public String getNeedPer() {
		return needPer;
	}

	public void setNeedPer(String needPer) {
		this.needPer = needPer;
	}
}
