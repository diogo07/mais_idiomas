package br.com.maisidiomas.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import br.com.maisidiomas.model.dao.ConexaoSQLite;
import br.com.maisidiomas.model.dao.UsuarioDAOMySQL;
import br.com.maisidiomas.model.dao.UsuarioDAOSQLite;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.view.CadastroActivity;

import br.com.maisidiomas.R;

public class ControllerCadastro implements View.OnClickListener{

    private CadastroActivity cadastroActivity;
    private UsuarioDAOSQLite usuarioDAO;

    public ControllerCadastro(CadastroActivity cadastroActivity) {
        this.cadastroActivity = cadastroActivity;
        this.cadastroActivity.getBtCadastrar().setOnClickListener(this);
        this.cadastroActivity.getBtLimpar().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btCadastrar){
            cadastrar();
        }
        if(id == R.id.btLimpar){

        }
    }

    private void cadastrar() {
        if(!camposVazios()){
            if(senhasConferem()){
                /*Usuario usuario = new Usuario(cadastroActivity.getEdtLogin().getText().toString(),
                        cadastroActivity.getEdtSenha().getText().toString(),
                        cadastroActivity.getEdtNome().getText().toString());
                try {
                    new UsuarioDAOMySQL(this.cadastroActivity).insert(usuario);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                usuarioDAO = new UsuarioDAOSQLite(ConexaoSQLite.getInstance(cadastroActivity));


                if(usuarioDAO.findByLogin(cadastroActivity.getEdtLogin().getText().toString()+"") != null){
                    cadastroActivity.alertarLoginIndisponivel();
                }else{
                    Usuario usuario = new Usuario(cadastroActivity.getEdtLogin().getText().toString(),
                            cadastroActivity.getEdtSenha().getText().toString(),
                            cadastroActivity.getEdtNome().getText().toString());
                    usuario.setPontuacao(0);

                    if(usuarioDAO.insert(usuario)){
                        cadastroActivity.AlertSucessoCadastro();
                        cadastroActivity.limparCampos();
                        cadastroActivity.finish();
                    }else{
                        cadastroActivity.AlertErroCadastro();
                    }
                }
            }else{
                cadastroActivity.alertarSenhasIncompativeis();
            }
        }else{
            cadastroActivity.alertarCampoVazio();
        }
    }

    private boolean senhasConferem() {
        if(cadastroActivity.getEdtSenha().getText().toString().equals(cadastroActivity.getEdtConfirmSenha().getText().toString())){
            return true;
        }else{
            return false;
        }
    }

    private boolean camposVazios() {
        if(cadastroActivity.getEdtNome().getText().toString().length() > 0
                && cadastroActivity.getEdtLogin().getText().toString().length() > 0
                && cadastroActivity.getEdtSenha().getText().toString().length() > 0
                && cadastroActivity.getEdtConfirmSenha().getText().toString().length() > 0){
            return false;
        }else{
            return true;
        }
    }
}
