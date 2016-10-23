package com.example

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

/**
  * Created by Sergey on 23.10.16.
  */
class AssetsController extends Controller {
  get("/css/:form/:file") { request: Request =>
    val path = request.params("form") + "/" + request.params("file")
    response.ok.file(path)
  }
}
