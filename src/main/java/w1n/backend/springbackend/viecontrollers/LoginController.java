package w1n.backend.springbackend.viecontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import w1n.backend.springbackend.model.Annuncio;
import w1n.backend.springbackend.model.User;
import w1n.backend.springbackend.model.tipiutente.Datore;
import w1n.backend.springbackend.model.tipiutente.Lavoratore;
import w1n.backend.springbackend.services.AnnuncioService;
import w1n.backend.springbackend.services.UserDetailService;


import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserDetailService userService;


    @Autowired
    private AnnuncioService annuncioService;




    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {

        if (utenteGiaLoggato()) {
            // Se l'utente è già loggato lo mando direttamente alla dashboard
            return new ModelAndView("forward:/dashboarduser");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value = "/paginaprovapeppe", method = RequestMethod.GET)
    public ModelAndView paginaprovapeppe() {
       List<Annuncio> listaannunci = annuncioService.annunciIniziali();

     //   annuncioService.annunciIniziali();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageNumber",0);
        modelAndView.addObject("listaannunci",listaannunci);
        modelAndView.setViewName("paginaprovapeppe");
        return modelAndView;
    }

    @RequestMapping(value = "/paginaprovapeppefiltered/{pageNumber}", method = RequestMethod.GET)
    public ModelAndView paginaprovapeppefiltered(@PathVariable int pageNumber) {

        int numeropagina = pageNumber;
        if(numeropagina != 0){
            numeropagina--;
        }
        annuncioService.annuncidopofiltro(numeropagina);
        List<Annuncio> listaannunci = annuncioService.getAnnunciCorrente();
        ModelAndView modelAndView = new ModelAndView();
        //   annuncioService.annunciIniziali();
        modelAndView.addObject("pageNumber",numeropagina);
        modelAndView.addObject("listaannunci",listaannunci);
        modelAndView.setViewName("paginaprovapeppe");
        return modelAndView;
    }

    @RequestMapping(value = "/paginaprovapeppefilteredavanti/{pageNumber}", method = RequestMethod.GET)
    public ModelAndView paginaprovapeppefilteredavanti(@PathVariable int pageNumber) {
        annuncioService.annuncidopofiltro(pageNumber+1);
        List<Annuncio> listaannunci = annuncioService.getAnnunciCorrente();
        ModelAndView modelAndView = new ModelAndView();

        //   annuncioService.annunciIniziali();
        modelAndView.addObject("pageNumber",pageNumber+1);
        modelAndView.addObject("listaannunci",listaannunci);
        modelAndView.setViewName("paginaprovapeppe");
        return modelAndView;
    }




    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @RequestMapping(value = "/incentivitermici", method = RequestMethod.GET)
    public ModelAndView incentivitermici() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("incentivitermici");
        return modelAndView;
    }


    @RequestMapping(value = "/zone", method = RequestMethod.GET)
    public ResponseEntity<Resource> zone(){

        try {

            File file = new File("C:\\Users\\GMC\\Desktop\\provafrancesco\\springbackend\\src\\main\\resources\\templates\\zone.xls");

            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));


            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/incentivifile", method = RequestMethod.GET)
    public ResponseEntity<Resource> incentivifile(){

        try {

            File file = new File("C:\\Users\\GMC\\Desktop\\provafrancesco\\springbackend\\src\\main\\resources\\templates\\incentiviricreato.xlsx");

            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));


            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Lavoratore user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {



            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", user);
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/signupdatore", method = RequestMethod.POST)
    public ModelAndView creaDatore(@Valid Datore user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("SONO ALL'INIZIO DEL METODO PER SALVARE IL DATORE");
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {


            System.out.println("SALVO IL DATORE");
            boolean salvato = userService.salvauserdatore(user);
            if(salvato){
                System.out.println("SALVATO IL DATORE");
            }else{
                System.out.println("NONONONONONONONON SALVATO IL DATORE");
            }
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/signupamministratore", method = RequestMethod.POST)
    public ModelAndView creaAmministratore(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {



            userService.salvauseradmin(user);

            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }


    @RequestMapping(value = "/dashboarddatore", method = RequestMethod.GET)
    public ModelAndView dashboarddatore() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("dashboarddatore");
        return modelAndView;
    }



    @RequestMapping(value = "/dashboarduser", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        System.out.println("AUTENTICATO COME " + auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " );// + user.getFullname()
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("dashboarduser");
        return modelAndView;
    }


    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        if (utenteGiaLoggato()) {
            // Se l'utente è già loggato lo mando direttamente alla dashboard
            return new ModelAndView("forward:/dashboarduser");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }


    public boolean utenteGiaLoggato(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return true;
        }
        return false;
    }


    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboardAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @RequestMapping(value = {"/loginamministrazione"}, method = RequestMethod.GET)
    public ModelAndView amministrazione() {
        if (utenteGiaLoggato()) {
            // Se l'utente è già loggato lo mando direttamente alla dashboard
            return new ModelAndView("forward:/dashboard");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginamministrazione");
        return modelAndView;
    }



}
