package sv.com.ventaboletos.models.entities;
//
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoices_details")
public class InvoiceDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column
	private Integer quantity;
	
	
	@JoinColumn(name="product_id") // si no se indica lo crea
	@ManyToOne(fetch=FetchType.LAZY)	
	private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
		
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double calcularImporte() {
		return quantity * product.getPrice()  ;
	}

}
