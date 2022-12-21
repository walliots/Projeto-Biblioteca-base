package projetoVendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import projetoVendas.domain.entity.Cliente;
import projetoVendas.domain.repository.Clientes;

@SpringBootApplication
public class VendasApplication {
//    @Bean
//    public CommandLineRunner commandLineRunner (@Autowired Clientes clientes){
//        return args -> {
//            Cliente cliente = new Cliente(null, "walliots");
//            clientes.save(cliente);
//
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }


}
