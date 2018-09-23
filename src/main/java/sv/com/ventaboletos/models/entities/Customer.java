package sv.com.ventaboletos.models.entities;
//
import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="customers")
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//@GeneratedValue(strategy = GenerationType.IDENTITY) //para mysql
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_Seq")
	@SequenceGenerator(name = "customers_Seq", sequenceName = "CUSTOMERS_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id; 
	
	@Column(name="first_name")
	@NotEmpty
	private String firstName;
	
	@Column(name="last_name")
	
	private String lastName;
	@NotEmpty
	@Email
	private String email;
	
	@NegativeOrZero
	private Integer valor;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private Date createAt;
	//en la tabla facturas va a crear cliente id para relacionar ambas tablas
	@OneToMany(mappedBy="customer", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Invoice> invoices;
	
	public Customer() {
		invoices = new ArrayList<Invoice>();
	}
	
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getCreateAt() {
		return createAt;
	}
	
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	public void addInvoices(Invoice invoice) {
		this.invoices.add(invoice);
	}



	public Integer getValor() {
		return valor;
	}



	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
