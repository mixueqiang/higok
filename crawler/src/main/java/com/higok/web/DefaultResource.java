package com.higok.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.view.Viewable;

/**
 * @author xueqiang.mi
 * @since 2012-7-26
 */
@Path("/")
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class DefaultResource {
  private static final Log LOGGER = LogFactory.getLog(DefaultResource.class);

  @GET
  public Response get() {
    LOGGER.info("Hello World!");
    return Response.ok(new Viewable("index")).build();
  }

}
