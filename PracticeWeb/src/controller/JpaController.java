package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import model.Flight;
import model.IModel;
import model.Passenger;
import model.Ticket;


public class JpaController implements IController {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Praktika");

	public List getObjectList(Class clazz) {
		EntityManager em = emf.createEntityManager();
		String queryName = clazz.getSimpleName() + "." + "findAll";
		List list = em.createNamedQuery(queryName).getResultList();
		em.close();
		return list;
	}

	public TableModel getModel(String className) {
		try {
			Class clazz = Class.forName("model." + className);
			IModel obj = (IModel) clazz.newInstance();
			String[] header = obj.getTableHeaders();
			List list = getObjectList(clazz);
			if (list == null || list.size() == 0)
				return new DefaultTableModel(null, header);
			Object[][] array = new Object[list.size()][header.length];
			int i = 0;
			for (Object s : list)
				array[i++] = ((IModel) s).getTableRowData();
			return new DefaultTableModel(array, header);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean exist(IModel obj) {
		Class clazz = obj.getClass();
		List list = getObjectList(clazz);
		if (list != null && list.size() != 0)
			for (Object current : list)
				if (current.equals(obj))
					return true;
		return false;
	}

	@Override
	public void add(Object obj) {
		if (exist((IModel) obj))
			return;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
	}

	@Override
	public void delete(int id, String className) {
		EntityManager em = emf.createEntityManager();
		try {
			Class clazz = Class.forName("model." + className);
			Object delObj = em.find(clazz, id);
			em.getTransaction().begin();
			em.remove(delObj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void edit(int id, Object obj) {
		EntityManager em = emf.createEntityManager();
		try {
			Class clazz = obj.getClass();
			if (exist((IModel) obj))
				return;
			Object editObj = em.find(clazz, id);

			em.getTransaction().begin();
			((IModel) editObj).updateWith(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
/*
	@Override
	public TableModel doQuery1() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery(
				"select  t	 from Ticket t where  t.price > ?1");
		q.setParameter(1, 200);
		List<Ticket> list = q.getResultList();
		String[][] arr = new String[list.size()][4];
		int i = 0;
		for (Ticket t : list) {
			arr[i][0] =  t.getPassenger().getName();
			arr[i][1] =  t.getFlight().getName();
			arr[i++][2] = String.valueOf(t.getPrice());
		}
		DefaultTableModel model = new DefaultTableModel(arr,
				new String[] { "psg_name", "flg_name", "price" });
		return model;
	}
|
*/
	@Override
	public void deleteJpql(int id, String className) {
		EntityManager em = emf.createEntityManager();
		Query q = null;
		if (className.equals("Ticket")) {
			q = em.createQuery("delete from TICKET t where t.id=?1");
			q.setParameter(1, id);
		} else if (className.equals("Passenger")) {
			q = em.createQuery("delete from PASSENGER p where p.id=?1");
			q.setParameter(1, id);
		} else if (className.equals("Flight")) {
			q = em.createQuery("delete from FLIGHT f where f.id=?1");
			q.setParameter(1, id);
		} else if (className.equals("Airport")) {
			q = em.createQuery("delete from AIRPORT a where a.id=?1");
			q.setParameter(1, id);
		}
		if (q == null)
			return;

		em.getTransaction().begin();
		q.executeUpdate();
		em.getTransaction().commit();

	}

	public void operateObject(IModel obj, int operation) {
		if (operation == 1) {
			add(obj);
		} else if (operation == 2) {
			edit(obj.getId(), obj);
		} else if (operation == 4) {
			delete(obj.getId(), obj.getClass().getSimpleName());
		}
	}

}
