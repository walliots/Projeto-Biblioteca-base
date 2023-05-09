package projetoVendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
