package br.com.clubelinkar.support.dummy

import br.com.clubelinkar.api.category.CategoryMother
import br.com.clubelinkar.api.category.CategoryRepository
import br.com.clubelinkar.api.company.CompanyMother
import br.com.clubelinkar.api.company.CompanyRepository
import br.com.clubelinkar.api.order.OrderRepository
import br.com.clubelinkar.api.product.ProductMother
import br.com.clubelinkar.api.product.ProductRepository
import br.com.clubelinkar.api.salesman.SalesmanMother
import br.com.clubelinkar.api.salesman.SalesmanRepository
import br.com.clubelinkar.api.user.UserRepository
import br.com.clubelinkar.support.event.TimelineEventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

import static br.com.clubelinkar.api.user.UserMother.carlaVidal
import static br.com.clubelinkar.api.user.UserMother.lennonJesus
import static org.apache.commons.lang3.BooleanUtils.isNotFalse
import static org.apache.commons.lang3.BooleanUtils.toBoolean
import static org.apache.commons.lang3.StringUtils.isBlank

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final String RESET_DB = "resetDatabase"

    @Autowired
    private ProductRepository productRepository

    @Autowired
    private CompanyRepository companyRepository

    @Autowired
    private OrderRepository orderRepository

    @Autowired
    private UserRepository userRepository

    @Autowired
    private CategoryRepository categoryRepository

    @Autowired
    private SalesmanRepository salesmanRepository

    @Autowired
    private TimelineEventRepository timelineEventRepository

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // pra resetar a base, coloque a VM option <code>-DresetDatabase=true</code> ou
        // simplesmente <code>-DresetDatabase</code> como parametro de startup a aplicacao
        if(shouldResetDatabase()) {
            clearDatabase()
            loadData()
        }
    }

    private Boolean shouldResetDatabase() {
        Properties props = System.getProperties()

        return props.containsKey(RESET_DB) &&
                (isNotFalse(toBoolean(props.getProperty(RESET_DB))) ||
                        isBlank(props.getProperty(RESET_DB)))
    }

    private void clearDatabase() {
        timelineEventRepository.deleteAll()
        productRepository.deleteAll()
        companyRepository.deleteAll()
        orderRepository.deleteAll()
        userRepository.deleteAll()
        categoryRepository.deleteAll()
        salesmanRepository.deleteAll()

    }

    private void loadData() {
        userRepository.save(lennonJesus())
        userRepository.save(carlaVidal())

        companyRepository.save(CompanyMother.allMotos())
        companyRepository.save(CompanyMother.mulherNaModa())

        productRepository.save(ProductMother.riserGuidao())
        productRepository.save(ProductMother.espadrilla())

        categoryRepository.save(CategoryMother.servicosMecanica())
        categoryRepository.save(CategoryMother.pecasMoto())

        salesmanRepository.save(SalesmanMother.salesman())
        salesmanRepository.save(SalesmanMother.newSalesman())

    }
}
