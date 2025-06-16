package com.example.ecommerce.service;

import com.example.ecommerce.entity.ItemPedido;
import com.example.ecommerce.entity.Pedido;
import com.example.ecommerce.repositories.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido save(Pedido pedido){
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(Pedido.Status.PAGO);

        for(ItemPedido item : pedido.getItens()){
            item.setPedido(pedido);
        }

        return pedidoRepository.save(pedido);
    }

    public Pedido update(Pedido pedido, Long id){
        Pedido pedidoParaSerAtualizado = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com id" + id));

        pedidoParaSerAtualizado.setUsuario(pedido.getUsuario());
        pedidoParaSerAtualizado.setDataPedido(pedido.getDataPedido());
        pedidoParaSerAtualizado.setValorTotal(pedido.getValorTotal());
        pedidoParaSerAtualizado.setStatus(pedido.getStatus());
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id){
        return pedidoRepository.findById(id);
    }

    public void deleteById(Long id){
        pedidoRepository.deleteById(id);
    }
}
