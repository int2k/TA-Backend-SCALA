package controllers.product

import controllers.product.ProductResource
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import services.product.ProductServiceComponent
import domain.product.Product

trait ProductController extends Controller {
  self: ProductServiceComponent =>0

  implicit val productWrites = new Writes[Product] {
    override def writes(product: Product): JsValue = {
      Json.obj(
        "id" -> product.id,
        "name" -> product.name,
        "price" -> product.price
      )
    }
  }

  def createProduct = Action(parse.json) {request =>
    unmarshalJsValue(request) { resource: ProductResource =>
      val product = Product(Option.empty, resource.name, resource.price)
      productService.createProduct(product)
      Created
    }
  }

  def updateProduct(id: Long) = Action(parse.json) {request =>
    unmarshalJsValue(request) { resource: ProductResource =>
      val product = Product(Option(id), resource.name, resource.price)
      productService.updateProduct(product)
      NoContent
    }
  }

  def findProductById(id: Long) = Action {
    val product = productService.tryFindById(id)
    if (product.isDefined) {
      Ok(Json.toJson(product))
    } else {
      NotFound
    }
  }

  def deleteProduct(id: Long) = Action {
    productService.delete(id)
    NoContent
  }

  def unmarshalJsValue[R](request: Request[JsValue])(block: R => Result)(implicit rds : Reads[R]): Result =
    request.body.validate[R](rds).fold(
      valid = block,
      invalid = e => {
        val error = e.mkString
        Logger.error(error)
        BadRequest(error)
      }
    )

}

case class ProductResource(name: String, price: Float)