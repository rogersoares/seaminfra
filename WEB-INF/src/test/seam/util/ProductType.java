package test.seam.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

@Name("productType")
public class ProductType {

	@In
	EntityManager em;

	public List<SelectItem> getTypes() {
		List<test.biz.ProductType> l = em.createQuery("from ProductType").getResultList();

		List<SelectItem> retList = new ArrayList<SelectItem>();
		SelectItem si = new SelectItem();
		si.setLabel("--");
		retList.add(si);
		for (test.biz.ProductType p : l) {
			si = new SelectItem();
			si.setLabel(p.getDescription());
			si.setValue(p.getId());
			retList.add(si);
		}

		return retList;
	}

}
