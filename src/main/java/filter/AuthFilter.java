package filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import view.AuthMb;

@WebFilter(filterName="authFilter",urlPatterns="*.xhtml")
public class AuthFilter implements Filter{

	@Inject
	private AuthMb authMb;
	

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servReq = (HttpServletRequest) req;
		HttpServletResponse servResp = (HttpServletResponse) resp;
		
		if(servReq.getRequestURI().equals("/home.xhtml") && !authMb.isLogged()){
			servResp.sendRedirect("register.xhtml");
		}  else {
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
