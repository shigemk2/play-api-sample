package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Enumerator
import play.api.data._
import play.api.data.Forms._

case class User(name: String, email: String)

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

  val userDataForm: Form[User] = Form (
    mapping(
      "name" -> text,
      "email"-> text
    )(User.apply)(User.unapply)
  )

  def create = Action { implicit request =>
    userDataForm.bindFromRequest.fold(
      errors => {
        Result(
          header = ResponseHeader(400, Map(CONTENT_TYPE -> "text/plain")),
          body = Enumerator("Bad Request!".getBytes())
        )
      },
      userdata => {
        println(userdata.name)
        println(userdata.email)
        Result(
          header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
          body = Enumerator("OK!".getBytes())
        )
      }
    )
  }
}
