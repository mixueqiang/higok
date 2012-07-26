package com.higok.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * @author xueqiang.mi
 * @since 2012-7-26
 */
public class LogbackConfigListener implements ServletContextListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(LogbackConfigListener.class);

  private static final String CONFIG_LOCATION = "logbackConfigLocation";

  @Override
  public void contextInitialized(ServletContextEvent event) {
    // 从web.xml中加载指定文件名的日志配置文件
    String logbackConfigLocation = event.getServletContext().getInitParameter(CONFIG_LOCATION);
    String fn = event.getServletContext().getRealPath(logbackConfigLocation);
    try {
      LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
      loggerContext.reset();
      JoranConfigurator joranConfigurator = new JoranConfigurator();
      joranConfigurator.setContext(loggerContext);
      joranConfigurator.doConfigure(fn);
      LOGGER.debug("loaded slf4j configure file from {}", fn);
    } catch (JoranException e) {
      LOGGER.error("can loading slf4j configure file from " + fn, e);
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
  }

}
