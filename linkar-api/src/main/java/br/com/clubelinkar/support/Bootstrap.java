package br.com.clubelinkar.support;

import br.com.clubelinkar.api.category.CategoryRepository;
import br.com.clubelinkar.api.company.CompanyRepository;
import br.com.clubelinkar.api.order.OrderRepository;
import br.com.clubelinkar.api.product.ProductRepository;
import br.com.clubelinkar.api.user.UserRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static br.com.clubelinkar.api.dummy.DummyDomainObjectMother.*;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final String RESET_DB = "resetDatabase";

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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // pra resetar a base, coloque a VM option <code>-DresetDatabase=true</code> ou
        // simplesmente <code>-DresetDatabase</code> como parametro de startup a aplicacao
        if(shouldResetDatabase()) {
            clearDatabase();
            loadData();
        }
    }

    private Boolean shouldResetDatabase() {
        Properties props = System.getProperties();

        return props.containsKey(RESET_DB)
                && (BooleanUtils.isNotFalse(BooleanUtils.toBoolean(props.getProperty(RESET_DB)))
                    || StringUtils.isBlank(props.getProperty(RESET_DB)));
    }

    private void clearDatabase() {
        productRepository.deleteAll();
        companyRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
        categoryRepository.deleteAll();
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
}
