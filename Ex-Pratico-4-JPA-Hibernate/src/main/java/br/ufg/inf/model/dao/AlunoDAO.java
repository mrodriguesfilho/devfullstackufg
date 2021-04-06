package br.ufg.inf.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufg.inf.ctrl.exception.AlunoException;
import br.ufg.inf.model.entities.Aluno;

public class AlunoDAO {

	EntityManager em = DaoFactory.getEntityManager();

	// CREATE
	public Aluno inserir(Aluno aluno) throws AlunoException {

		this.em.getTransaction().begin();
		this.em.persist(aluno);
		this.em.getTransaction().commit();

		return aluno;
	}

	// READ
	public List<Aluno> buscaTodos() throws AlunoException {
		return this.em.createQuery("from Aluno", Aluno.class).getResultList();
	}

	public Aluno buscaPorId(Integer id) throws AlunoException {
		return this.em.find(Aluno.class, id);
	}

	// UPDATE

	public Aluno alterar(Aluno aluno) throws AlunoException {

		this.em.getTransaction().begin();
		this.em.persist(aluno);
		this.em.getTransaction().commit();

		return aluno;
	}

	// DELETE

	public void excluir(Integer id) throws AlunoException {
		this.em.remove(this.buscaPorId(id));
	}
	
	
	public List<Aluno> buscarPorNome(String str){
		String sql = "from Aluno d where d.nmAluno like :str";
		TypedQuery<Aluno> query = em.createQuery(sql, Aluno.class);
		query.setParameter("str", "%"+str+"%");
		return query.getResultList();
	}
}
