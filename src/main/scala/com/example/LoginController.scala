package com.example

import com.twitter.finagle.http.{Cookie, Request}
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.{FormParam, QueryParam}

/**
  * Created by Sergey on 23.10.16.
  */

class LoginController extends Controller {
  post("/login") { request: Request =>
    val username = request.params("loginName")
    val password = request.params("loginPass")
    if (username == "god" && password == "multipass") {
      response
        .ok("You are logged in! That's very cool:)")
        .cookie("auth_token", "true")
    }
    else response.unauthorized()
  }
}
