package test.seam.action;

import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.log.Log;

import test.biz.Product;
import test.biz.ProductType;

@Name("productAction")
@Scope(ScopeType.EVENT)
public class ProductAction {

	@Logger
	private Log log;

	@In
	EntityManager em;

	@In
	FacesMessages facesMessages;

	@RequestParameter
	private Integer id;
	private Product product;

	@Create
	public void create() {
		if (id != null) {
			product = em.find(Product.class, id);
		} else {
			product = new Product();
			product.setType(new ProductType());
		}
	}

	public String save() {
		try {
			boolean isValidationOk = true;
			if ("belonga".equals(product.getName())) {
				facesMessages.addFromResourceBundle("global.error", "You can't belonga me.");
				isValidationOk = false;
			}

			if (isValidationOk) {
				em.clear();
				em.merge(product);
				em.flush();

				return "/private/productList.xhtml";
			}
		} catch (Exception e) {
			log.error(e, e);
			facesMessages.addFromResourceBundle(Severity.ERROR, "global.error", e);
		}
		return null;
	}

	public String delete() {
		em.remove(product);
		em.flush();

		return "/private/productList.xhtml";
	}

	public List<Product> getProductList() {
		return em.createQuery("from Product order by id").getResultList();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
