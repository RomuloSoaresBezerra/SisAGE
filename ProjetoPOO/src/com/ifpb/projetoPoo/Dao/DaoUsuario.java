
package com.ifpb.projetoPoo.Dao;

import com.ifpb.projetoPoo.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {
   
    private List<Usuario> usuarios;

    public DaoUsuario() {
        this.usuarios = new ArrayList<>();
    }
   
    public boolean create(Usuario u){
        for(Usuario user : usuarios){
            if(user.getEmail().equals(u.getEmail())){
                return false;
            }
        }
        return usuarios.add(u);
    }
    
    public Usuario read(String email){
        for(Usuario u : usuarios){
            if(u.getEmail().equals(email)) return u;
        }
        return null;
    }
    
    public boolean update(Usuario u){
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(u.getEmail())){
                usuarios.set(i, u);
                return true;
            }
        }
        return false;
    }
    
    public boolean delete (Usuario u){
        return usuarios.remove(u);
    }
    
}
