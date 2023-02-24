package com.suresoft.study.template.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.GenericFilterBean;

public class AjaxSessionTimeoutFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
	 
	        if (isAjaxRequest(req)) {
	                try {
	                       chain.doFilter(req, res);
	                } catch (AccessDeniedException e) {
	                       res.sendError(HttpServletResponse.SC_FORBIDDEN);
	                } catch (AuthenticationException e) {
	                       res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	                }
	        } else {
	                chain.doFilter(req, res);
	        }
	}
	 
	private boolean isAjaxRequest(HttpServletRequest req) {
	        return req.getHeader("AJAX") != null && req.getHeader("AJAX").equals(Boolean.TRUE.toString());
	}

}
