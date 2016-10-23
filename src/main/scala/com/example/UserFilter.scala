package com.example

import com.google.inject.Inject
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Filter, Service, SimpleFilter}
import com.twitter.finatra.http.response.ResponseBuilder
import com.twitter.util.Future
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.handler.codec.http.{HttpHeaders, HttpResponse, HttpResponseStatus, HttpVersion}

/**
  * Created by Sergey on 23.10.16.
  **/

class UserFilter @Inject() extends SimpleFilter[Request, Response] {
  override def apply(request: Request, service: Service[Request, Response]): Future[Response] = {
    val tokenExist = request.cookies.getOrElse("auth_token", "false") == "true"
    if (tokenExist) service(request)
    else Future.value(Response(Status.Unauthorized))
  }
}
