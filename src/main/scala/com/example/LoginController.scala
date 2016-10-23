package com.example

import java.util.concurrent.TimeUnit

import com.example.db.DB
import com.twitter.finagle.http.{Cookie, Request}
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.{FormParam, QueryParam}
import com.twitter.util.Await

import scala.concurrent.duration._

/**
  * Created by Sergey on 23.10.16.
  */

class LoginController extends Controller {
  post("/login") { request: Request =>
    val username = request.params("loginName")
    val password = request.params("loginPass")
    if (username == "god" && password == "multipass") {
      val f = DB.createToken(1).map { token =>
        info(s"token generated: $token")
        response
          .ok("You are logged in! That's very cool:)")
          .cookie("auth_token", token)
      }
      Await.result(f)
    }
    else response.unauthorized()
  }
}
