package projetoVendas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetoVendas.domain.entity.Cliente;
import projetoVendas.domain.entity.ItensPedido;
import projetoVendas.domain.entity.Pedido;
import projetoVendas.domain.entity.Produto;
import projetoVendas.domain.enums.StatusPedido;
import projetoVendas.domain.repository.Clientes;
import projetoVendas.domain.repository.ItemPedido;
import projetoVendas.domain.repository.Pedidos;
import projetoVendas.domain.repository.Produtos;
import projetoVendas.exception.PedidoNaoEncontradoException;
import projetoVendas.exception.RegraNegocioException;
import projetoVendas.rest.dto.ItemPedidoDTO;
import projetoVendas.rest.dto.PedidoDTO;
import projetoVendas.service.PedidoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
// anotation que gera um construtor com todos os argumentos obrigatorios
// (argumentos obrigatorios são todos aqueles atributos com final)
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos pedidosRepository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemPedido itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(()-> new RegraNegocioException("Código de cliente invalido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItensPedido> itensPedidos = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedidos);
        pedido.setItemPedidos(itensPedidos);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItemPedidos(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidosRepository.findById(id).map(pedido -> {
            pedido.setStatus(statusPedido);
            return pedidosRepository.save(pedido);
        }).orElseThrow(()-> new PedidoNaoEncontradoException());
    }

    private List<ItensPedido> converterItems(Pedido pedido, List<ItemPedidoDTO>items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items");
        }
        return items.stream().map(dto ->{
            Integer idProduto = dto.getProduto();
            Produto produto =
            produtosRepository
                    .findById(idProduto)
                    .orElseThrow(()-> new RegraNegocioException("Código de produto invalido: "+ idProduto));

            ItensPedido itensPedido = new ItensPedido();
            itensPedido.setQuantidade(dto.getQuantidade());
            itensPedido.setPedido(pedido);
            itensPedido.setProduto(produto);
            return itensPedido;
        }).collect(Collectors.toList());
    }
}
