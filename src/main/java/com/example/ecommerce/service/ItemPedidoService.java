package com.example.ecommerce.service;

import com.example.ecommerce.entity.ItemPedido;
import com.example.ecommerce.entity.Pedido;
import com.example.ecommerce.entity.Produto;
import com.example.ecommerce.repositories.ItemPedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public ItemPedido save(ItemPedido itemPedido){
        return  itemPedidoRepository.save(itemPedido);
    }

    public ItemPedido update(ItemPedido itemPedido, Long id){
     ItemPedido itemPedidoParaSerAtualizado = itemPedidoRepository.findById(id)
             .orElseThrow(() -> new EntityNotFoundException("Item Pedido não encontrado com id" + id));

        itemPedidoParaSerAtualizado.setProduto(itemPedido.getProduto());
        itemPedidoParaSerAtualizado.setPedido(itemPedido.getPedido());
        itemPedidoParaSerAtualizado.setQuantidade(itemPedido.getQuantidade());
        itemPedidoParaSerAtualizado.setPrecoUnitario(itemPedido.getPrecoUnitario());
        return  itemPedidoRepository.save(itemPedido);
    }

    public List<ItemPedido> findAll(){
        return itemPedidoRepository.findAll();
    }

    public Optional<ItemPedido> findById(Long id){
        return itemPedidoRepository.findById(id);
    }

    public void deleteById(Long id){
        itemPedidoRepository.deleteById(id);
    }

}
