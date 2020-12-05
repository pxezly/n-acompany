package th.ac.kmitl.SleepingBeauty.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import th.ac.kmitl.SleepingBeauty.model.Customer;
import th.ac.kmitl.SleepingBeauty.model.CustomerRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void createCustomer(Customer customer) {
        String hashPin = hash(customer.getPassword());
        customer.setPassword(hashPin);
        repository.save(customer);
    }

    public Customer findCustomer(int id) {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<Customer> getCustomers() {
        return repository.findAll();
    }


    public Customer checkPassword(Customer inputCustomer) {
        Customer storedCustomer = findCustomer(inputCustomer.getId());

        if (storedCustomer != null) {
            String storedPassword = storedCustomer.getPassword();
            if (BCrypt.checkpw(inputCustomer.getPassword(), storedPassword))
                return storedCustomer;
        }
        return null;
    }

    private String hash(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }
}


