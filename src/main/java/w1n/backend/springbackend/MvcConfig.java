package w1n.backend.springbackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/dashboarduser").setViewName("dashboarduser");
        registry.addViewController("/dashboarddatore").setViewName("dashboarddatore");
        registry.addViewController("/loginamministrazione").setViewName("loginamministrazione");
        registry.addViewController("/annunci").setViewName("annunci");
        registry.addViewController("/paginaprovapeppe").setViewName("paginaprovapeppe");
        registry.addViewController("/incentivitermici").setViewName("incentivitermici");
        registry.addViewController("/zone").setViewName("zone");
        registry.addViewController("/incentivifile").setViewName("incentivifile");
        registry.addViewController("/menutoggle").setViewName("menutoggle");
        registry.addViewController("/menulaptop").setViewName("menulaptop");
        registry.addViewController("/layoutdevice").setViewName("layoutdevice");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
