/**
 *
 */
module org.starrier.common {
    requires lombok;
    requires org.apache.commons.codec;
    requires com.google.common;
    requires jjwt;
    requires org.apache.commons.lang3;
    requires org.joda.time;
    requires java.sql;
    requires spring.core;
    requires java.validation;
    requires spring.web;
    requires spring.jcl;
    requires gson;
    requires org.aspectj.weaver;
    requires spring.context;
    requires annotations;
    requires tomcat.embed.core;
    exports org.starrier.common.utils;
    exports org.starrier.common.annotation;
}