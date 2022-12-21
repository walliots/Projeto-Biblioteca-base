package projetoVendas.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import projetoVendas.domain.entity.ItensPedido;
import projetoVendas.domain.entity.Pedido;
import projetoVendas.domain.enums.StatusPedido;
import projetoVendas.rest.dto.AtualizacaoStatusPedidoDTO;
import projetoVendas.rest.dto.InformacoesItemPedidoDTO;
import projetoVendas.rest.dto.InformacoesPedidoDTO;
import projetoVendas.rest.dto.PedidoDTO;
import projetoVendas.service.PedidoService;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service
                .obterPedidoCompleto(id)
                .map(pedido -> converter(pedido))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,@RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    public InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                //esse name pega a enum e transforma em string
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItemPedidos()))
                .build();
    }

    private List<InformacoesItemPedidoDTO> converter (List<ItensPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacoesItemPedidoDTO
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }


}
