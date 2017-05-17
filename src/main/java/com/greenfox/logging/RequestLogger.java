package com.greenfox.logging;


import com.greenfox.model.LogEntry;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

@Component
public class RequestLogger {

  Logger infoLogger = LoggerFactory.getLogger("infoLogger");
  Logger errorLogger = LoggerFactory.getLogger("errorLogger");


  public void info(HttpServletRequest request) {
    infoLogger.info(
        new LogEntry(new UrlPathHelper().getPathWithinApplication(request), request.getMethod(),
            request.getParameterMap()).toString());
  }

  public void error(HttpServletRequest request) {
    errorLogger.error(new LogEntry(new UrlPathHelper().getPathWithinApplication(request), request.getMethod(),
            request.getParameterMap()).toString());
  }
}
