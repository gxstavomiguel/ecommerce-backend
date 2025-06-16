package com.example.ecommerce.service;

import com.example.ecommerce.entity.Produto;
import com.example.ecommerce.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto save(Produto produto) {

        return produtoRepository.save(produto);
    }

    public Produto update(Produto produto, Long id) {
        Produto produtoParaSerAtualizado = produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrad com id" + id));

        produtoParaSerAtualizado.setNome(produto.getNome());
        produtoParaSerAtualizado.setDescricao(produto.getDescricao());
        produtoParaSerAtualizado.setPreco(produto.getPreco());
        produtoParaSerAtualizado.setQuantidade(produto.getQuantidade());
        produtoParaSerAtualizado.setCategoria(produto.getCategoria());
        return produtoRepository.save(produtoParaSerAtualizado);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }
}
