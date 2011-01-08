/*
 * Copyright (C) 2010 Parleys.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.parleys.server.frontend.web.ipad.filters;

import com.parleys.server.frontend.service.ParleysService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This filter works around some JSF quirks and logs in and sets a cookie to remember the user.
 *
 * @author Jan-Kees van Andel
 */
public class LoginFilter implements Filter {

    public static final String PARLEYS_REMEMBER_ME_IPAD = "parleysRememberMeIPad";

    private WebApplicationContext applicationContext;

    @Override
    public void init(FilterConfig filterConfig) {
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse response,
                         final FilterChain chain) throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        final PrintWriter writer = res.getWriter();
        res.setStatus(HttpServletResponse.SC_OK);
        res.setHeader("Cache-Control", "must-revalidate");
        res.setHeader("Expires", "Fri, 30 Oct 1998 14:19:41 GMT");
        try {
            final String username = req.getParameter("username");
            final String password = req.getParameter("password");
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                final ParleysService bean = applicationContext.getBean(ParleysService.class);
                final Long userId = bean.getUserId(username, password);

                String usernameAndPassword = username + ";" + password;
                String encrypted = AESEncrypter.INSTANCE.encrypt(usernameAndPassword);
                Cookie rememberMeCookie = new Cookie(PARLEYS_REMEMBER_ME_IPAD, encrypted);
                rememberMeCookie.setMaxAge(3600 * 24 * 7 * 26); // A half year
                res.addCookie(rememberMeCookie);

                writeUserId(writer, userId);
            }
        } catch (Exception e) {
            writeError(writer);
        }
    }

    private void writeUserId(PrintWriter writer, Long userId) {
        writer.write("{\"error\": false, \"message\": null, \"userId\": " + userId + "}");
    }

    private void writeError(PrintWriter writer) {
        writer.write("{\"error\": true, \"message\": \"Invalid credentials\"}");
    }

    @Override
    public void destroy() {
    }
}
