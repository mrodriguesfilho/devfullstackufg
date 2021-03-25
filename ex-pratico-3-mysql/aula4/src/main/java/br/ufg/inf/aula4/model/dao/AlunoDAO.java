package br.ufg.inf.aula4.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.app.DB;
import br.ufg.inf.aula4.ctrl.exception.AlunoException;
import br.ufg.inf.aula4.model.entities.Pessoa;
import br.ufg.inf.aula4.model.entities.Aluno;
import br.ufg.inf.aula4.model.entities.Curso;

public class AlunoDAO {

	public Aluno inserir(Aluno aluno) throws AlunoException {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			st = conn.prepareStatement(
          "INSERT INTO tb_aluno (dt_inicio, ativo, id_pessoa, id_curso) VALUES (?, ? , ? , ?)",
					Statement.RETURN_GENERATED_KEYS);
			
      st.setDate(1, new Date(aluno.getDtInicio().getTime()));
			st.setBoolean(2, aluno.getAtivo());
      st.setInt(3, aluno.getPessoa().getIdPessoa());
      st.setInt(4, aluno.getCurso().getIdCurso());

			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					aluno.setIdAluno(id);
				}
			}
		} catch (SQLException e) {
			throw new AlunoException(e.getMessage());
		}

		return aluno;
	}

	public List<Aluno> buscaTodos() throws AlunoException {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Aluno> aluno = new ArrayList<Aluno>();
		try {
			Connection conn = DB.getConnection();
			String query = "SELECT id_aluno, dt_inicio, ativo, id_pessoa, id_curso FROM tb_aluno ORDER BY id_aluno ";
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				aluno.add(this.vo(rs));
			}
		} catch (SQLException e) {
			throw new AlunoException(e.getMessage());
		}
		return aluno;
	}

	private Aluno vo(ResultSet rs) throws SQLException {
		Aluno aluno = new Aluno();
		aluno.setIdAluno(rs.getInt("id_aluno"));
    aluno.setDtInicio(rs.getDate("dt_inicio"));
    aluno.setAtivo(rs.getBoolean("ativo"));
    aluno.setPessoa(new Pessoa(rs.getInt("id_pessoa"), null, null, null));
    aluno.setCurso(new Curso(rs.getInt("id_pessoa"), null));
		return aluno;
	}

	public Aluno buscaPorId(Integer id) throws AlunoException {
		Aluno aluno = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
      String query = "SELECT id_aluno, dt_inicio, ativo, id_pessoa, id_curso FROM tb_aluno WHERE id_aluno = ?";
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				aluno = this.vo(rs);
			}
		} catch (SQLException e) {
			throw new AlunoException(e.getMessage());
		}
		return aluno;
	}

	public Aluno alterar(Aluno aluno) throws AlunoException {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
      String query = "UPDATE tb_aluno SET id_aluno = ?, dt_inicio = ?, ativo = ? , id_pessoa = ? , id_curso = ? WHERE id_aluno = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, aluno.getIdAluno());
			st.setDate(2, new Date(aluno.getDtInicio().getTime()));
			st.setBoolean(3, aluno.getAtivo());
      st.setInt(4, aluno.getPessoa().getIdPessoa());
      st.setInt(5, aluno.getCurso().getIdCurso());

			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);
		} catch (SQLException e) {
			throw new AlunoException(e.getMessage());
		}
		return aluno;
	}

	public void excluir(Integer id) throws AlunoException {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = " DELETE FROM tb_aluno WHERE id_aluno = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

		} catch (SQLException e) {
			throw new AlunoException(e.getMessage());
		}
	}
}
