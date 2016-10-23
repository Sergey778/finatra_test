package com.example

import com.google.inject.Inject
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Filter, Service, SimpleFilter}
import com.twitter.util.Future

/**
  * Created by Sergey on 23.10.16.
  **/

class UserFilter @Inject() extends SimpleFilter[Request, Response] {
  override def apply(request: Request, service: Service[Request, Response]): Future[Response] = {
    val token = request.cookies.get("auth_token")
    if (token.map(x => x.value).contains("true")) service(request)
    else Future {
      val response = Response(Status.TemporaryRedirect)
      //response.xForwardedFor = "/loginform"
      response.location = "/loginform"
      response
    }
  }
}
