package w1n.backend.springbackend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import w1n.backend.springbackend.model.Role;
import w1n.backend.springbackend.model.User;
import w1n.backend.springbackend.model.tipiutente.Amministratore;
import w1n.backend.springbackend.model.tipiutente.Datore;
import w1n.backend.springbackend.model.tipiutente.Lavoratore;
import w1n.backend.springbackend.repositories.*;

import java.util.*;

@Service
public class UserDetailService  implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LavoratoreRepository lavoratoreRepository;

    @Autowired
    private DatoreRepository datoreRepository;

    @Autowired
    private AmministratoreRepository amministratoreRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // UTENTE = LAVORATORE = USER
    // DATORE = DATORE = DATORE

    public List<User> findAllUser(){
        return userRepository.findAll();
    }


    public void saveUser(Lavoratore lavoratore) {

        System.out.println("SALVO LAVORATORE " + lavoratore.toString() + "   PW IN CHIARO " + lavoratore.getPassword());
        lavoratore.setPassword(bCryptPasswordEncoder.encode(lavoratore.getPassword()));
        lavoratore.setEnabled(true);
        Role userRole = roleRepository.findByRole("USER");
        lavoratore.setRoles(new HashSet<>(Arrays.asList(userRole)));
        lavoratoreRepository.save(lavoratore);
        userRepository.save(lavoratore);
    }

    public void salvauseradmin(User user) {
        Amministratore amministratore = (Amministratore) user;
        amministratoreRepository.save(amministratore);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public boolean salvauserdatore(Datore datore){


        datore.setPassword(bCryptPasswordEncoder.encode(datore.getPassword()));
        datore.setEnabled(true);
        Role userRole = roleRepository.findByRole("DATORE");
        datore.setRoles(new HashSet<>(Arrays.asList(userRole)));
        try {
            userRepository.save(datore);
            datoreRepository.save(datore);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if(user != null) {

            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            UserDetails userDetails = buildUserForAuthentication(user, authorities);
            System.out.println(userDetails.getPassword());
            return userDetails;
        } else {
            System.out.println("NON HO TROVATO L'UTENTE");
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }


    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }


}



