package com.example

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.{FormParam, QueryParam}

/**
  * Created by Sergey on 23.10.16.
  */

case class LoginRequest(
  @FormParam `login-pass`: String,
  @FormParam `login-name`: String
                       )

class LoginController extends Controller {
  post("/login") { request: LoginRequest =>
    val username = request.`login-name`
    val password = request.`login-pass`
    if (username == "god" && password == "multipass")
      response
        .ok("You are logged in! That's very cool:)")
        .cookie("auth_token", "true")
    else response.unauthorized("you have no access. your pass is bullshit. you are bullshit!!!")
  }
}
