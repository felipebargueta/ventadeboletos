package sv.com.ventaboletos.models.entities;
//
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="invoices")
public class Invoice {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "invoices_Seq")
	@SequenceGenerator(name = "invoices_Seq", sequenceName = "INVOICES_SEQ")
	private Long id;
	
	
	private String description;
	@Column(name="create_at")
	private Date createAt;
	
	//muchas facturas un cliente
	//EAGER: trae todo de una vez, toda la consulta, va a traer tambien el cliente, carga demasiado
	//LAZY: Evitar que traiga, solo hacer consulta cuando se le llama
	@ManyToOne(fetch=FetchType.LAZY)
	private Customer customer;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="invoice_id")// como la relacion es en un solo sentido tenemos que indicar cual es la llave foranea.
	private List<InvoiceDetail> invoiceDetails;

	
	
	public Invoice() {
		this.invoiceDetails = new ArrayList<>();
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}


	public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}
	
	public void addInvoiceDetail(InvoiceDetail invoiceDetail) {
		this.invoiceDetails.add(invoiceDetail);
	}
	
	//suma el total de cada linea para obtener el total de la factura.
	public Double getTotal() {
		Double total = 0.0;
		int size = invoiceDetails.size();
		for(int i = 0; i < size;i++ ) {
			total += invoiceDetails.get(i).calcularImporte();
		}
		return total;
	}
	
}
