package com.example.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Retorna todos os produtos
   * 
   * @return List<Product>
   */

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  /**
   * Retorna um produto pelo ID
   * 
   * @param id
   * @return Optional<Product>
   */
  public Optional<Product> getProductById(Long id) {
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("ID do produto deve ser positivo");
    }
    return productRepository.findById(id);
  }

  /**
   * Remove o produto pelo ID
   * 
   * @param id
   * @return void
   */

  public void deleteProductById(Long id) {
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("ID do produto deve ser positivo");
    }
    productRepository.deleteById(id);
  }

  /**
   * Salva um novo produto
   * 
   * @param product
   * @return Product
   */

  public Product saveProduct(Product product) {
    validateProduct(product);
    return productRepository.save(product);
  }

  /**
   * Valida as propriedades do produto antes de salvar
   * 
   * @param product Produto a ser validado
   * @return void
   */

  private void validateProduct(Product product) {
    if (product == null) {
      throw new IllegalArgumentException("Product cannot be null");
    }
    if (product.getName() == null || product.getName().isBlank()) {
      throw new IllegalArgumentException("Product name cannot be empty");
    }
    if (product.getDescription() == null || product.getDescription().isBlank()) {
      throw new IllegalArgumentException("Product description cannot be empty");
    }
    if (product.getPrice() <= 0) {
      throw new IllegalArgumentException("Product price must be greater than zero");
    }
    if (product.getQuantity() < 0) {
      throw new IllegalArgumentException("Product quantity cannot be negative");
    }
  }
}
