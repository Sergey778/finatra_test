package com.example

import com.example.db.DB
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
    DB.isValidToken(token.map(x => x.value).getOrElse("")) flatMap {
      case true => service(request)
      case false => redirectToLoginPage
    }
  }

  def redirectToLoginPage = Future {
    val response = Response(Status.TemporaryRedirect)
    response.location = "/loginform"
    response
  }
}
