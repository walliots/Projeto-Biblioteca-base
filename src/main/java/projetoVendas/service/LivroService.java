package projetoVendas.service;

import org.springframework.stereotype.Service;
import projetoVendas.domain.repository.LivroRepository;

@Service
public class LivroService {
    private LivroRepository _livroRepository;

    public LivroService(LivroRepository _livroRepository){
        this._livroRepository = _livroRepository;
    }

    

}