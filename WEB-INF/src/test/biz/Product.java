package test.biz;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name="product")
@SequenceGenerator(
		name="SEQ_STORE",
		sequenceName="product_seq"
)
public class Product {

	private int id;
	private String name;
	private String description;
	private ProductType type;

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Length(min=5, max=128)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=5, max=128)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="type")
	@NotNull
	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

}
