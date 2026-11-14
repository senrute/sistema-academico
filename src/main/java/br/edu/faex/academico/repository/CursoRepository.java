package br.edu.faex.academico.repository;

import br.edu.faex.academico.config.Conexao;
import br.edu.faex.academico.model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {
    private List<Curso> cursos = new ArrayList<>();
    public void salvar (Curso curso){
        String sql = "INSERT INTO cursos (nome, modalidade) VALUES (?,?)";
    try{
        Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql);
        comando.setString(1, curso.getNome());
        comando.setString(2, curso.getModalidade());
        comando.executeUpdate();
        comando.close();
        conexao.close();
        System.out.println("Curso Salvo com sucesso");
    }catch(SQLException e){
        System.out.println("Erro ao salvar: "+e.getMessage());
        }
    }

    public List<Curso> listar(){
        String sql = "SELECT * FROM cursos";
        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                Curso curso = new Curso();
                curso.setNome(resultado.getString("nome"));
                curso.setModalidade(resultado.getString("modalidade"));
                curso.setId(resultado.getLong("id"));
                cursos.add(curso);
            }
            resultado.close();
            comando.close();
            conexao.close();
        }catch(SQLException e){
            System.out.println("Erro ao listar: "+e.getMessage());
        }
        return cursos;
    }
    public Curso buscarPorId(Long id){
        String sql = "SELECT * FROM cursos WHERE id = ?";
        try{
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()){
                Curso curso = new Curso();
                curso.setId(resultado.getLong("id"));
                curso.setNome(resultado.getString("nome"));
                curso.setModalidade(resultado.getString("modalidade"));
                resultado.close();
                comando.close();
                conexao.close();
                return curso;
            }
        }catch(SQLException e){
            System.out.println("Erro ao buscar: " + e.getMessage());
        }
        return null;
    }

    public void excluir(Long id){
        String sql = "DELETE FROM cursos WHERE id = ?";

        try{
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, id);

            int linhasAfetadas = comando.executeUpdate();

            comando.close();
            conexao.close();

            if(linhasAfetadas > 0){
                System.out.println("Curso excluído com sucesso!");
            } else {
                System.out.println("Nenhum Curso encontrado com o ID informado.");
            }

        } catch(SQLException e){
            System.out.println("Erro ao excluir curso: " + e.getMessage());
        }
    }

    public void atualizar(Curso cursoEditado) {

        String sql = "UPDATE cursos SET nome = ?, modalidade = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, cursoEditado.getNome());
            comando.setString(2, cursoEditado.getModalidade());
            comando.setLong(3, cursoEditado.getId());

            int linhasAfetadas = comando.executeUpdate();

            comando.close();
            conexao.close();

            if (linhasAfetadas > 0) {
                System.out.println("Curso atualizado com sucesso!");
            } else {
                System.out.println("Nenhum curso encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar curso: " + e.getMessage());
        }
    }

}
