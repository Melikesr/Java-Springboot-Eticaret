package com.example.Eticaret.Customers;
import com.example.Eticaret.Entity.User;
import com.example.Eticaret.Entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       User user=repo.findByEmail(email);

        if (user==null){
            throw new UsernameNotFoundException("Kullanıcı bulunamadı.");
        }

       return new CustomerDetails(user);
    }
}
