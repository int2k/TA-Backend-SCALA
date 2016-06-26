package services.product

import domain.product.Product
import repositories.product.ProductRepositoryComponent

trait ProductServiceComponent {
    
    val productService: ProductService
    
    trait ProductService {
        
        def createProduct(product: Product): Product
        
        def updateProduct(product: Product)
        
        def tryFindById(id: Long): Option[Product]
       
        def delete(id: Long)
    
    }

}

trait ProductServiceComponentImpl extends ProductServiceComponent {
    self: ProductRepositoryComponent =>
    
    override val productService = new ProductServiceImpl
    
    class ProductServiceImpl extends ProductService {
        
        override def createProduct(product: Product): Product = {
            productRepository.createProduct(product)
        }
        
        override def updateProduct(product: Product) {
            productRepository.updateProduct(product)
        }
        
        override def tryFindById(id: Long): Option[Product] = {
            productRepository.tryFindById(id)
        }
        
        override def delete(id: Long) {
            productRepository.delete(id)
        }
        
    }
}