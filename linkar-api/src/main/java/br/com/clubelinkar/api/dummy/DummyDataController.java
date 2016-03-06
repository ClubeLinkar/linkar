package br.com.clubelinkar.api.dummy;

import br.com.clubelinkar.api.category.CategoryRepository;
import br.com.clubelinkar.api.order.OrderRepository;
import br.com.clubelinkar.api.product.ProductRepository;
import br.com.clubelinkar.api.company.CompanyRepository;
import br.com.clubelinkar.api.user.UserRepository;
import br.com.clubelinkar.exception.RepeatedUserCPFException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.clubelinkar.api.dummy.DummyDomainObjectMother.*;

/**
 * @author Lennon Jesus
 */

@RestController
@Deprecated // @see Bootstrap
public class DummyDataController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/dummy")
    public String prepareDatabase() {
        clearDatabase();
        loadData();

        return "Dummy Loaded!. IMPORTANT: this controller will be discontinued! See Bootstrap class!";
    }

    @RequestMapping("/exception")
    public String throwException() {
        throw new RepeatedUserCPFException();
    }

    private void loadData() {
        userRepository.save(getUser());
        userRepository.save(getAnotherUser());

        companyRepository.save(getCompany());
        companyRepository.save(getAnotherCompany());

        productRepository.save(getProduct());
        productRepository.save(getAnotherProduct());

        categoryRepository.save(getCategory());
        categoryRepository.save(getAnotherCategory());

    }

    private void clearDatabase() {
        productRepository.deleteAll();
        companyRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
        categoryRepository.deleteAll();
    }

}