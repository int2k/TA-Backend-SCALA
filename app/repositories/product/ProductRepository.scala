package repositories.product

import domain.product.Product
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

trait ProductRepositoryComponent {
  val productRepository: ProductRepository

  trait ProductRepository {

    def createProduct(product: Product): Product

    def updateProduct(product: Product)

    def tryFindById(id: Long): Option[Product]

    def delete(id: Long)

  }
}

trait ProductRepositoryComponentImpl extends ProductRepositoryComponent {
  override val productRepository = new ProductRepositoryImpl

  class ProductRepositoryImpl extends ProductRepository {

    val products = new ConcurrentHashMap[Long, Product]
    val idSequence = new AtomicLong(0)

    override def createProduct(product: Product): Product = {
      val newId = idSequence.incrementAndGet()
      val createdProduct = product.copy(id = Option(newId))
      products.put(newId, createdProduct)
      createdProduct
    }

    override def updateProduct(product: Product) {
      products.put(product.id.get, product)
    }

    override def tryFindById(id: Long): Option[Product] = {
      Option(products.get(id))
    }


    override def delete(id: Long) {
      products.remove(id)
    }

  }

}