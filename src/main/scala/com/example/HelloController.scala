package com.example

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

/**
  * Created by Sergey on 22.10.16.
  */
class HelloController extends Controller {
  /*
  get("/index") { request: Request =>
    val token = request.cookies.getOrElse("auth_token", "false")
    if (token == "false") response.ok.file("/login-form/index.html")
    else response.ok("Nice to see you, logged-in user!")
  }
  */

  filter[UserFilter].get("/index") { request: Request =>
    response.ok("Nice to see you through filter!")
  }

  get("/loginform") { request: Request =>
    response.ok.file("/login-form/index.html")
  }
}
