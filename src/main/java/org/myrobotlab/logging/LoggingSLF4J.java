package org.myrobotlab.logging;

import java.nio.charset.StandardCharsets;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.util.StatusPrinter;

public class LoggingSLF4J extends Logging {

  @Override
  public void addAppender(Object type) {
    // BIG NOOP
  }

  @Override
  public void addAppender(String type) {
    addAppender(type, null);
  }

  @Override
  public void addAppender(String type, String filename) {

    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    PatternLayoutEncoder ple = new PatternLayoutEncoder();

    // %date
    ple.setCharset(StandardCharsets.UTF_8);
    ple.setPattern("%date{HH:mm:ss.SSS} [%thread] %level %logger{10} [%file:%line] %msg%n");
    ple.setContext(lc);
    ple.start();

    // allows you to add appenders to different logging locations
    Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    logger.setAdditive(false);

    if (AppenderType.CONSOLE.equalsIgnoreCase(type)) {
      ConsoleAppender<ILoggingEvent> console = new ConsoleAppender<ILoggingEvent>();
      console.setName(type);
      // console.setLayout(layout); ???
      console.setEncoder(ple);
      console.setContext(lc);
      console.start();
      logger.addAppender(console);
    } else if (AppenderType.FILE.equalsIgnoreCase(type)) {
      // FIXME - replace with RollingFileAppender ???
      FileAppender<ILoggingEvent> fileAppender = new FileAppender<ILoggingEvent>();
      fileAppender.setName(type);
      fileAppender.setFile(LoggingFactory.getLogFileName());
      fileAppender.setEncoder(ple);
      fileAppender.setContext(lc);
      fileAppender.setAppend(false);
      fileAppender.start();
      logger.addAppender(fileAppender);
    } else {
      log.error("attempting to add unkown type of Appender {}", type);
      return;
    }
  }

  @Override
  public void configure() {
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    JoranConfigurator configurator = new JoranConfigurator();
    configurator.setContext(context);
    StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    removeAllAppenders();
    addAppender(AppenderType.CONSOLE);
    addAppender(AppenderType.FILE);
  }

  @Override
  public String getLevel() {
    Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    Level level = logger.getLevel();
    if (level.equals(Level.DEBUG)) {
      return "DEBUG";
    } else if (level.equals(Level.INFO)) {
      return "INFO";
    } else if (level.equals(Level.WARN)) {
      return "WARN";
    } else if (level.equals(Level.ERROR)) {
      return "ERROR";
    }
    return "UNKNOWN";
  }

  @Override
  public void removeAllAppenders() {
    Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    logger.detachAndStopAllAppenders();
  }

  @Override
  public void removeAppender(Object console) {
    // Another big NOOP

  }

  @Override
  public void removeAppender(String name) {

    Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    logger.detachAppender(name); // does this stop it too ?
  }

  @Override
  public void setLevel(String level) {
    setLevel(null, level);
  }

  @Override
  public void setLevel(String clazz, String targetLevel) {
    if (clazz == null || clazz.length() == 0) {
      clazz = Logger.ROOT_LOGGER_NAME;
    }

    Logger logger = (Logger) LoggerFactory.getLogger(clazz);

    if ("DEBUG".equalsIgnoreCase(targetLevel)) {
      logger.setLevel(Level.DEBUG);
    } else if ("TRACE".equalsIgnoreCase(targetLevel)) {
      logger.setLevel(Level.TRACE);
    } else if ("WARN".equalsIgnoreCase(targetLevel)) {
      logger.setLevel(Level.WARN);
    } else if ("ERROR".equalsIgnoreCase(targetLevel)) {
      logger.setLevel(Level.ERROR);
      // } else if ("FATAL".equalsIgnoreCase(level)) {
      // logger.setLevel(Level.FATAL);
    } else {
      logger.setLevel(Level.INFO);
    }
  }

}
