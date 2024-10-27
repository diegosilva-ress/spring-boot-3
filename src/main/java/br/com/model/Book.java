package br.com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book implements Serializable {

  @Serial
  private static final long serialVersionUID = 7780554997223513513L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "author", nullable = false, length = 180)
  private String author;

  @Column(name = "launch_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date launchDate;

  @Column(name = "price", nullable = false)
  private double price;

  @Column(name = "title", nullable = false)
  private String title;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getLaunchDate() {
    return launchDate;
  }

  public void setLaunchDate(Date launchDate) {
    this.launchDate = launchDate;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Double.compare(price, book.price) == 0 && Objects.equals(id, book.id)
        && Objects.equals(author, book.author) && Objects.equals(launchDate,
        book.launchDate) && Objects.equals(title, book.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, author, launchDate, price, title);
  }
}
