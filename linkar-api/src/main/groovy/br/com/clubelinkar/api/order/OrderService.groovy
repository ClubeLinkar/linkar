package br.com.clubelinkar.api.order

import br.com.clubelinkar.api.company.Company
import br.com.clubelinkar.api.company.CompanyRepository
import br.com.clubelinkar.api.product.Product
import br.com.clubelinkar.api.product.ProductRepository
import br.com.clubelinkar.api.user.User
import br.com.clubelinkar.api.user.UserRepository
import br.com.clubelinkar.exception.InvalidProductException
import br.com.clubelinkar.exception.InvalidStoreException
import br.com.clubelinkar.exception.InvalidUserException
import br.com.clubelinkar.exception.RepeatedPurchaseException
import br.com.clubelinkar.support.date.IDateFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Lennon Jesus
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private IDateFactory dateFactory

    @Autowired
    private ProductRepository productRepository

    @Autowired
    private CompanyRepository companyRepository

    @Autowired
    private OrderRepository orderRepository

    @Autowired
    private UserRepository userRepository

    @Override
    public Order save(Order order) {

        validate(order)

        order.setDateTime(dateFactory.now())

        return orderRepository.save(order)
    }

    // TODO [felipe] nao seria melhor ter essa logica toda de validacao numa classe a parte?
    private void validate(Order order) {
        // verificar se userId e userPass coincidem
        User user = userRepository.findByEmailAndPassword(order.getUserEmail(), order.getUserPassword())
        if (user == null) throw new InvalidUserException()
        order.setUserId(user.getId())

        // verificar se companyId e storePass coincidem
        Company company = companyRepository.findByIdAndPassword(order.getCompanyId(), order.getStorePassword())
        if (company == null) throw new InvalidStoreException()

        // verificar se companyId e productCompanyId coincidem
        Product product = productRepository.findByIdAndCompanyId(order.getProductId(), order.getCompanyId())
        if (product == null || !company.getId().equals(product.getCompanyId())) throw new InvalidProductException()

        // verificar se nao existe um order by userId + companyId + productId
        Order existingOrder = orderRepository.findByUserIdAndCompanyIdAndProductId(user.getId(), company.getId(), product.getId())
        if (existingOrder != null) throw new RepeatedPurchaseException()
    }

}
