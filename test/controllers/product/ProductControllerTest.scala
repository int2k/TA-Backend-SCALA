package controllers.product

import scala.concurrent.duration._
import scala.concurrent.Await
import org.junit._
import org.junit.Assert._
import org.mockito.Mockito._
import play.api.test._
import play.api.test.Helpers._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import services.product.ProductServiceComponent
import domain.product.Product

class ProductControllerTest {

  private val productController = new ProductController with ProductServiceComponentMock {}
  /*
  @Test
  def createProduct() {
      val name = "abc test"
      val price = 10
      val request = FakeRequest(POST, "/products")
                        .withBody(
                            Json.obj(
                                "name" -> name.
                                "price" -> price
                            )
                        )
      
      val hey: Result = Await.ready(productController.createProduct(request), Duration("10 seconds"))
      
      assertEquals(201, status(hey))
      verify(productController.productService).createProduct(Product(Option.empty, name, price))
  }
  
  @Test
  def updateProduct() {
      val id = 1
      val request = FakeRequest(PUT, s"/products/$id")
                        .withBody(
                            Json.obj(
                                "name" -> "abc test",
                                "price" -> 10
                            )
                        )
      
      val hey: Result = productController.createProduct(request)
      
      assertEquals(201, status(hey))
  }
  */

}

trait ProductServiceComponentMock extends ProductServiceComponent {

  override val productService = mock(classOf[ProductService])

}
