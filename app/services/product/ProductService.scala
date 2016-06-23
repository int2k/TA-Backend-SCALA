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

trait UserServiceComponentImpl extends UserServiceComponent {
    self: UserRepositoryComponent =>
    
    override val userService = new UserServiceImpl
    
    class UserServiceImpl extends UserService {
        
        override def createUser(user: User): User = {
            userRepository.createUser(user)
        }
        
        override def updateUser(user: User) {
            userRepository.updateUser(user)
        }
        
        override def tryFindById(id: Long): Option[User] = {
            userRepository.tryFindById(id)
        }

        override def tryFindByEmail(email: String): Option[User] = {
            userRepository.tryFindByEmail(email)
        }
        
        override def delete(id: Long) {
            userRepository.delete(id)
        }
        
    }
}