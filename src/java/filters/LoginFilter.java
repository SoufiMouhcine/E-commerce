/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;
import ManagedBean.AuthBean;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Filter checks if LoginBean has loginIn property set to true.
 * If it is not set then request is being redirected to the login.xhml page.
 * 
 * @author itcuties
 *
 */
public class LoginFilter implements Filter {

	/**
	 * Checks if user is logged in. If not it redirects to the login.xhtml page.
	 */
	public void doFilter(ServletRequest servletRequest,
      ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// Get the loginBean from session attribute
                HttpServletRequest httpServletRequest =
        (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse =
        (HttpServletResponse) servletResponse;
		          AuthBean authBean = (AuthBean) httpServletRequest.getSession().getAttribute("authBean");
		
		// For the first application request there is no loginBean in the session so user needs to log in
		// For other requests loginBean is present but we need to check if user has logged in successfully
		if (authBean==null || !authBean.getIslogged()) {
			String contextPath = httpServletRequest.getContextPath();
			httpServletResponse.sendRedirect(contextPath + "/visitor/login.xhtml");
                        
		}
		
		filterChain.doFilter(servletRequest, servletResponse);
			
	}

	public void init(FilterConfig config) throws ServletException {
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}	
	
}
