package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Enumerator

class Application extends Controller {

  def index = Action {
    Ok("index")
  }

  def ok = Action {
    Result(
      header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
      body = Enumerator("OK!".getBytes())
    )
  }

  def bad = Action {
    Result(
      header = ResponseHeader(400, Map(CONTENT_TYPE -> "text/plain")),
      body = Enumerator("Bad Request!".getBytes())
    )
  }

  def error = Action {
    Result(
      header = ResponseHeader(500, Map(CONTENT_TYPE -> "text/plain")),
      body = Enumerator("Internal Server Error!".getBytes())
    )
  }
}
