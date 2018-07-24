package br.com.maisidiomas.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import br.com.maisidiomas.controller.ControllerCadastro;
import br.com.maisidiomas.controller.ControllerLogin;
import br.com.maisidiomas.model.dao.FabricaDeDAOSSQLite;
import br.com.maisidiomas.model.dao.Fachada;
import br.com.maisidiomas.model.vo.Usuario;

public class FirebaseConecty {

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef;


    public static void salvar(Usuario usuario){
        myRef = database.getReference();
        myRef.child("usuarios").child(String.valueOf(usuario.getId())).setValue(usuario);
    }

    public static void getListUsuarios() {

        if(isConected(UtilsParametros.getContext())){
            System.out.println("Tem conexao");
            final ProgressDialog progressDialog = ProgressDialog.show(UtilsParametros.getContext(), "", "Carregando ...", true);
            progressDialog.setCancelable(false);
            final ArrayList<Usuario> usuarios = new ArrayList<>();
            myRef = database.getReference().child("usuarios");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    usuarios.clear();
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        Usuario u = postSnapshot.getValue(Usuario.class);
                        usuarios.add(u);
                    }
                    UtilsParametros.carregarListaUsuarios(usuarios);
                    progressDialog.dismiss();
                    UtilsParametros.getControllerDashBoard().exibirRanking();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("Erro", "Failed to read value.", error.toException());
                }
            });
        }else{
            System.out.println("Não tem conexao");
            UtilsParametros.getControllerDashBoard().alertarFaltaDeConexao();
        }
    }

    public static void  getUsuario(final ControllerLogin controllerLogin, final int id, final String senha, final ProgressDialog progressDialog) {
        progressDialog.setCancelable(false);
        UtilsParametros.carregarContexto(controllerLogin.getLoginActivity());
        myRef = database.getReference().child("usuarios").child(String.valueOf(id));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               if(isConected(UtilsParametros.getContext())){
                   Usuario u = dataSnapshot.getValue(Usuario.class);
                   if(u != null){
                       if(u.getSenha().equalsIgnoreCase(senha)) {
                           System.out.println(u);
                           UtilsParametros.carregarUsuario(u);
                           progressDialog.dismiss();
                           controllerLogin.verificarUsuarioLogado();
                       }else{
                           progressDialog.dismiss();
                           controllerLogin.verificarUsuarioLogado();
                       }

                   }else {
                       progressDialog.dismiss();
                       controllerLogin.verificarUsuarioLogado();
                   }
               }else {
                   progressDialog.dismiss();
                   controllerLogin.verificarUsuarioLogado();
               }


            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Erro", "Failed to read value.", error.toException());
            }
        });
    }


    public static void  getUsuarioByLogin(final ControllerLogin controllerLogin, final String login, final String senha, final ProgressDialog progressDialog) {
        if(isConected(controllerLogin.getLoginActivity())){
            UtilsParametros.carregarContexto(controllerLogin.getLoginActivity());
            myRef = (DatabaseReference) database.getReference().child("usuarios");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        Usuario u = postSnapshot.getValue(Usuario.class);
                        if(u.getLogin().equals(login) && u.getSenha().equals(senha)){
                            UtilsParametros.carregarUsuario(u);
                            progressDialog.dismiss();
                            controllerLogin.verificarUsuarioLogado();

                            return;
                        }

                    }
                    progressDialog.dismiss();
                    controllerLogin.verificarUsuarioLogado();

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("Erro", "Failed to read value.", error.toException());
                }
            });


            /*myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Usuario u = dataSnapshot.getValue(Usuario.class);
                    controllerLogin.getLoginActivity().exibirMensagem(u.getSenha());

                        if(u != null){
                            controllerLogin.getLoginActivity().exibirMensagem("existe usuario");
                            if(u.getSenha().equalsIgnoreCase(senha)) {
                                UtilsParametros.carregarUsuario(u);
                                progressDialog.dismiss();
                                controllerLogin.verificarUsuarioLogado();
                            }else{
                                progressDialog.dismiss();
                                controllerLogin.verificarUsuarioLogado();
                            }

                        }else {
                            controllerLogin.getLoginActivity().exibirMensagem("nulo");
                            progressDialog.dismiss();
                            controllerLogin.verificarUsuarioLogado();
                        }

                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("Erro", "Failed to read value.", error.toException());
                }
            });*/
        }else {
            controllerLogin.getLoginActivity().exibirMensagem("Não tem conexão");
            progressDialog.dismiss();
            controllerLogin.verificarUsuarioLogado();
        }
    }

     public static boolean isConected(Context cont){
        ConnectivityManager conmag = (ConnectivityManager)cont.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = conmag.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

     public static void loginDisponivel(final ProgressDialog progressDialog, final String login, final ControllerCadastro controllerCadastro){
        final ArrayList<Usuario> usuarios = new ArrayList<>();
        myRef = database.getReference().child("usuarios");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuarios.clear();
                Usuario usuario = null;
                int id = 0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Usuario u = postSnapshot.getValue(Usuario.class);

                    if(u.getLogin().equals(login)){
                        usuario = u;
                    }

                    if(u.getId() > id){
                        id = u.getId();
                    }
                }

                if(usuario == null){
                    UtilsParametros.carregarContexto(controllerCadastro.getCadastroActivity());
                    UtilsParametros.carregarIdCadastro(id+1);
                    controllerCadastro.cadastrarUsuario();
                }else{
                    progressDialog.dismiss();
                    controllerCadastro.getCadastroActivity().alertarLoginIndisponivel();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Erro", "Failed to read value.", error.toException());
            }
        });
    }
}
