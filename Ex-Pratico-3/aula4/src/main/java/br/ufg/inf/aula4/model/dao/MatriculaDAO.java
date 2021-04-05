package br.ufg.inf.aula4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.app.DB;
import br.ufg.inf.aula4.ctrl.exception.MatriculaException;
import br.ufg.inf.aula4.model.entities.Aluno;
import br.ufg.inf.aula4.model.entities.Oferta;
import br.ufg.inf.aula4.model.entities.Matricula;

public class MatriculaDAO {

	public Matricula inserir(Matricula matricula) throws MatriculaException {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO tb_matricula " + "(id_aluno, id_oferta)" + "VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, matricula.getAluno().getIdAluno());
			st.setInt(2, matricula.getOferta().getIdOferta());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);
			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					matricula.setIdMatricula(id);
				}
			}
		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
		return matricula;
	}

	public List<Matricula> buscaTodos() throws MatriculaException {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Matricula> matriculas = new ArrayList<Matricula>();
		try {
			Connection conn = DB.getConnection();
			String query = "SELECT id_matricula, id_aluno, id_oferta FROM tb_matricula ORDER BY id_matricula ";
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				matriculas.add(this.vo(rs));
			}
		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
		return matriculas;
	}

	private Matricula vo(ResultSet rs) throws SQLException {
		Matricula matricula = new Matricula();
		matricula.setIdMatricula(rs.getInt("id_matricula"));
		matricula.setAluno(new Aluno(rs.getInt("id_aluno"), null, null, null, null));
		matricula.setOferta(new Oferta(rs.getInt("id_oferta"), null, null, null, null, null, null));
		return matricula;
	}

	public Matricula buscaPorId(Integer id) throws MatriculaException {
		Matricula matricula = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = "SELECT id_matricula, id_aluno, id_oferta FROM tb_matricula WHERE id_matricula = ? ";
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				matricula = this.vo(rs);
			}
		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
		return matricula;
	}

	public Matricula alterar(Matricula matricula) throws MatriculaException {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = "UPDATE tb_matricula SET id_matricula = ?, id_aluno = ?, id_oferta = 0 WHERE id_matricula = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, matricula.getIdMatricula());
			st.setInt(2, matricula.getAluno().getIdAluno());
			st.setInt(3, matricula.getOferta().getIdOferta());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);
		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
		return matricula;
	}

	public void excluir(Integer id) throws MatriculaException {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = " DELETE FROM tb_matricula WHERE id_matricula = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
	}
}
