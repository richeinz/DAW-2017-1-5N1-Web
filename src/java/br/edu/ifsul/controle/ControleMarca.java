package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MarcaDAO;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleMarca")
@SessionScoped
public class ControleMarca implements Serializable{
    
    private MarcaDAO dao;
    private Marca objeto;
    
    public ControleMarca(){
        dao = new MarcaDAO();
    }
    
    public String listar(){
        return"/privado/marca/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Marca();
        return "formulario";
    }
    
    public String salvar(){
        if(dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar";
        }else{
            Util.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public String editar(Integer id){
        try{
            objeto = dao.localizar(id);
            return "formulario";
        }catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar";
        }
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if(dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }
    

    public MarcaDAO getDao() {
        return dao;
    }

    public void setDao(MarcaDAO dao) {
        this.dao = dao;
    }

    public Marca getObjeto() {
        return objeto;
    }

    public void setObjeto(Marca objeto) {
        this.objeto = objeto;
    }
    
    
    
}
