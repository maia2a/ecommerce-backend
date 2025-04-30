package com.example.ecommerce.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private double price;
  private int quantity;

  protected Product() {
  } // Construtor vazio para o JPA

  public Product(String name, String description, double price, int quantity) {
    validate(name, description, price, quantity);
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
  }

  private void validate(String name, String description, double price, int quantity) {
    Objects.requireNonNull(name, "Name cannot be null");
    Objects.requireNonNull(description, "Description cannot be null");
    if (price <= 0) {
      throw new IllegalArgumentException("Price must be greater than zero");
    }
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity cannot be negative");
    }
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    Objects.requireNonNull(name, "Name cannot be null");
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    Objects.requireNonNull(description, "Description cannot be null");
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    if (price <= 0) {
      throw new IllegalArgumentException("Price must be greater than zero");
    }
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity cannot be negative");
    }
    this.quantity = quantity;
  }

  // Sobrescreve o método toString para exibir as informações do produto
  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        '}';
  }
}
