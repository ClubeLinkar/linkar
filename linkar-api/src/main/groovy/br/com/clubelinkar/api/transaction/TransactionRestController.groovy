package br.com.clubelinkar.api.transaction

import br.com.clubelinkar.api.user.User
import br.com.clubelinkar.support.date.IDateFactory
import br.com.clubelinkar.support.security.service.ILoggedUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

/**
 * @author Lennon Jesus
 */
@RestController("/transaction")
public class TransactionRestController {

    @Autowired
    private TransactionRepository transactionRepository

    @Autowired
    private IDateFactory dateFactory

    @Autowired ILoggedUserService loggedUserService

    @RequestMapping(value = "/transaction", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Transaction create(@RequestBody @Valid Transaction transaction) {

        User user = loggedUserService.getCurrentLoggedUser()

        transaction.creatorId = user.id
        transaction.creatorName = user.name
        transaction.creatorEmail= user.email

        transaction.dateCreated = dateFactory.now()

        transaction.amount = transaction.productUnitPrice * transaction.productQuantity

        return transactionRepository.save(transaction)
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public List<Transaction> findAll() {
        return transactionRepository.findAll()
    }

    @RequestMapping(value = "/transaction/{id}")
    public Transaction getById(@PathVariable("id") String id) {
        return transactionRepository.findOne(id)
    }


}