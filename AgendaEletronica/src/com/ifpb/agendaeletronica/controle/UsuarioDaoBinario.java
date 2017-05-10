package com.ifpb.agendaeletronica.controle;

import com.ifpb.agendaeletronica.interfaces.UsuarioDao;
import com.ifpb.agendaeletronica.modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe que contém o CRUD em arquivo binário para a classe<code>Usuario<code>
 *
 * @author Jozimar Soares da Costa
 * @author Rômulo Soares Bezerra
 * @see ArrayList
 * @see Usuario
 */
public class UsuarioDaoBinario implements UsuarioDao {

    private File arquivo;

    /**
     * Contrutor da classe UsuarioDaoBinario
     */
    public UsuarioDaoBinario() {
        arquivo = new File("Usuarios.bin");

        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Falha na Conexão Com o Arquivo",
                        "Mensagem de Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método criado para listar os usuários presentes no arquivo
     *
     * @return uma lista contendo todos os usuários que estão no arquivo
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Usuario> listUsuario() throws IOException, ClassNotFoundException {

        if (arquivo.length() > 0) {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(arquivo));

            return (List<Usuario>) input.readObject();

        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Método criado para retornar um usuário aravés do email, caso o mesmo
     * exista
     *
     * @param email representa o email do usuário a ser retornado
     * @return o usuário caso o mesmo exista no arquivo, caso contrário null
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public Usuario readUsuario(String email) throws IOException, ClassNotFoundException {

        List<Usuario> usuarios = listUsuario();

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Método criado para adicionar um usuário no arquivo
     *
     * @param u representa o usuário a ser adicionado
     * @return true caso o usuário seja adicionado, caso contrário false
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean createUsuario(Usuario u) throws IOException, ClassNotFoundException {

        List<Usuario> usuarios = listUsuario();

        for (Usuario user : usuarios) {
            if (user.getEmail().equals(u.getEmail())) {
                return false;
            }
        }

        usuarios.add(u);

        atualizarArquivo(usuarios);

        return true;

    }

    /**
     * Método criado para remover um usuário do arquivo
     *
     * @param email representa o email do usuário a ser deletado
     * @return true caso o usuário seja deletado, caso contrário false
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean deleteUsuario(String email) throws IOException, ClassNotFoundException {

        List<Usuario> usuarios = listUsuario();

        for (Usuario u : usuarios) {

            if (u.getEmail().equals(email)) {
                usuarios.remove(u);
                atualizarArquivo(usuarios);
                return true;
            }
        }
        return false;

    }

    /**
     * Método criado para atualizar um usuário no arquivo
     *
     * @param u representa o usuário a ser atualizado
     * @return true caso o usuário seja atualizado, caso contrário false
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean updateUsuario(Usuario u) throws IOException, ClassNotFoundException {

        List<Usuario> usuarios = listUsuario();

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().
                    equals(u.getEmail())) {
                usuarios.set(i, u);

                atualizarArquivo(usuarios);

                return true;
            }
        }
        return false;
    }

    /**
     * Método criado para atualizar o arquivo binário que está sendo utilizado
     *
     * @param usuarios representa a lsta de usuários presentes no arquivo
     * @throws IOException
     */
    private void atualizarArquivo(List<Usuario> usuarios) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivo));

        out.writeObject(usuarios);
        out.close();
    }
}
