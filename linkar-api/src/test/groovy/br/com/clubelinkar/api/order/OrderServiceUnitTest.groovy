package br.com.clubelinkar.api.order

import br.com.clubelinkar.api.product.Product
import br.com.clubelinkar.api.product.ProductRepository
import br.com.clubelinkar.api.store.Store
import br.com.clubelinkar.api.store.StoreRepository
import br.com.clubelinkar.api.user.User
import br.com.clubelinkar.api.user.UserRepository
import br.com.clubelinkar.exception.InvalidProductException
import br.com.clubelinkar.exception.InvalidStoreException
import br.com.clubelinkar.exception.InvalidUserException
import br.com.clubelinkar.exception.RepeatedPurchaseException
import br.com.clubelinkar.support.util.DateFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.runners.MockitoJUnitRunner

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

/**
 * @author Lennon Jesus
 */
@RunWith(MockitoJUnitRunner)
class OrderServiceUnitTest {

    @Mock
    private DateFactory dateFactoryMock

    @Mock
    private ProductRepository productRepositoryMock

    @Mock
    private StoreRepository storeRepositoryMock

    @Mock
    private OrderRepository purchaseRepositoryMock

    @Mock
    private UserRepository customerRepositoryMock

    @InjectMocks
    @Spy
    def OrderService purchaseServiceSpy = new OrderService()

    @Before
    public void setup() {
        when(customerRepositoryMock.findByEmailAndPassword(purchase.userEmail, purchase.userPassword)).thenReturn(customer)
        when(storeRepositoryMock.findByIdAndPassword(purchase.storeId, purchase.storePassword)).thenReturn(store)
        when(productRepositoryMock.findByIdAndStoreId(purchase.productId, purchase.storeId)).thenReturn(product)
        when(purchaseRepositoryMock.findByUserIdAndStoreIdAndProductId(customer.id, store.id, product.id)).thenReturn(null)
        when(purchaseRepositoryMock.save(purchase)).thenReturn(purchase);
    }

    @Test
    public void "Deve concretizar compra corretamente"() {

        purchaseServiceSpy.save(purchase)

        verify(dateFactoryMock).now()
        verify(purchaseServiceSpy).validate(purchase)
        verify(purchaseRepositoryMock).save(purchase)
    }

    @Test(expected = InvalidUserException)
    public void "Não deve concretizar compra se as credenciais do usuários forem inválidas"() {
        when(customerRepositoryMock.findByEmailAndPassword(purchase.userEmail, purchase.userPassword)).thenReturn(null)
        purchaseServiceSpy.validate(purchase)
    }

    @Test(expected = InvalidStoreException)
    public void "Não deve concretizar compra se as credenciais do lojista forem inválidas"() {
        when(storeRepositoryMock.findByIdAndPassword(purchase.storeId, purchase.storePassword)).thenReturn(null)
        purchaseServiceSpy.validate(purchase)
    }

    @Test(expected = InvalidProductException)
    public void "Não deve concretizar compra se o produto comprado não pertencer à loja selecionada"() {
        when(productRepositoryMock.findByIdAndStoreId(purchase.productId, purchase.storeId)).thenReturn(null)
        purchaseServiceSpy.validate(purchase)
    }

    @Test(expected = RepeatedPurchaseException)
    public void "Não deve concretizar compra já existir uma compra anterior para o mesmo produto, na mesma loja, para o mesmo usuário"() {
        when(purchaseRepositoryMock.findByUserIdAndStoreIdAndProductId(customer.id, store.id, product.id)).thenReturn(purchase)
        purchaseServiceSpy.validate(purchase)
    }

    def User getCustomer() {
        new User(id: "customer_1", name: "Lennon Jesus", email: "lennon.jesus@gmail.com", password: "123456")
    }

    def Product getProduct() {
        new Product(id: "product_1", name: "Produto 1", storeId: store.id)
    }

    def Store getStore() {
        new Store(id: "store_1", name: "All Motos", password: "654321")
    }

    def Order getPurchase() {
        new Order(
                userId: customer.id,
                userEmail: customer.email,
                userPassword: customer.password,
                storeId: store.id,
                storePassword: store.password,
                productId: product.id
        )
    }

}
