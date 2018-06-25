package br.com.maisidiomas.controller;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import br.com.maisidiomas.R;

import br.com.maisidiomas.model.dao.ConexaoSQLite;
import br.com.maisidiomas.model.dao.PalavraDAOSQLite;
import br.com.maisidiomas.model.vo.Palavra;
import br.com.maisidiomas.model.vo.Opcao;
import br.com.maisidiomas.model.vo.Ranking;
import br.com.maisidiomas.model.vo.RankingAdapter;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.view.DashBoardActivity;
import br.com.maisidiomas.view.LoginActivity;

public class ControllerDashBoard {
    private DashBoardActivity dashBoardActivity;
    private int id_usuario;


    public ControllerDashBoard(DashBoardActivity dashBoardActivity, int id_usuario) {
        this.dashBoardActivity = dashBoardActivity;
        this.id_usuario = id_usuario;
        inserirRanking();
        inserirPalavras();
    }

    private void inserirPalavras() {
        ArrayList<Palavra> palavras = new ArrayList<>();
        palavras.add(new Palavra(1, "fork", "garfo"));
        palavras.add(new Palavra(1, "spoon", "colher"));
        palavras.add(new Palavra(1, "knife", "faca"));
        palavras.add(new Palavra(1, "umbrella", "guarda-chuva"));
        palavras.add(new Palavra(1, "socks", "meias"));
        palavras.add(new Palavra(1, "backpack", "mochila"));
        palavras.add(new Palavra(1, "sneakers", "tênis"));
        palavras.add(new Palavra(1, "shirt", "camisa"));
        palavras.add(new Palavra(1, "skirt", "saia"));
        palavras.add(new Palavra(1, "cap", "boné"));
        palavras.add(new Palavra(1, "door", "porta"));
        palavras.add(new Palavra(1, "window", "janela"));
        palavras.add(new Palavra(1, "chair", "cadeira"));
        palavras.add(new Palavra(1, "desk", "carteira"));
        palavras.add(new Palavra(1, "sun", "sol"));
        palavras.add(new Palavra(1, "moon", "lua"));
        palavras.add(new Palavra(1, "eroser", "borracha"));
        palavras.add(new Palavra(1, "crayons", "lápis de cera"));
        palavras.add(new Palavra(1, "shoes", "sapato"));
        palavras.add(new Palavra(1, "ruler", "régua"));
        palavras.add(new Palavra(1, "clock", "relógio de parede"));
        palavras.add(new Palavra(1, "watch", "relógio de pulso"));
        palavras.add(new Palavra(1, "wastebasket", "cesta de lixo"));
        palavras.add(new Palavra(1, "plate", "prato"));
        palavras.add(new Palavra(1, "mirror", "espelho"));
        palavras.add(new Palavra(1, "hand", "mão"));
        palavras.add(new Palavra(1, "foot", "pé"));
        palavras.add(new Palavra(1, "heart", "coração"));
        palavras.add(new Palavra(1, "eye", "olho"));
        palavras.add(new Palavra(1, "ear", "orelha"));
        palavras.add(new Palavra(1, "nose", "nariz"));
        palavras.add(new Palavra(1, "mouth", "boca"));
        palavras.add(new Palavra(1, "bag", "bolsa"));
        palavras.add(new Palavra(1, "finger", "dedo"));
        palavras.add(new Palavra(1, "hat", "chapéu"));
        palavras.add(new Palavra(1, "bed", "cama"));
        palavras.add(new Palavra(1, "boat", "barco"));
        palavras.add(new Palavra(1, "ship", "navio"));
        palavras.add(new Palavra(1, "bottle", "garrafa"));
        palavras.add(new Palavra(1, "box", "caixa"));
        palavras.add(new Palavra(1, "broom", "vassoura"));
        palavras.add(new Palavra(1, "coat", "casaco"));
        palavras.add(new Palavra(1, "dress", "vestido"));
        palavras.add(new Palavra(1, "chicken", "frango"));
        palavras.add(new Palavra(1, "cock", "galo"));
        palavras.add(new Palavra(1, "cow", "vaca"));
        palavras.add(new Palavra(1, "duck", "pato"));
        palavras.add(new Palavra(1, "egg", "ovo"));
        palavras.add(new Palavra(1, "donkey", "burro"));
        palavras.add(new Palavra(1, "fish", "peixe"));
        palavras.add(new Palavra(1, "glass", "copo"));
        palavras.add(new Palavra(1, "glasses", "óculos"));
        palavras.add(new Palavra(1, "beer", "cerveja"));
        palavras.add(new Palavra(1, "bee", "abelha"));
        palavras.add(new Palavra(1, "beetle", "besouro"));
        palavras.add(new Palavra(1, "carrot", "cenoura"));
        palavras.add(new Palavra(1, "root", "raiz"));
        palavras.add(new Palavra(1, "cockroach", "barata"));
        palavras.add(new Palavra(1, "tree", "árvore"));
        palavras.add(new Palavra(1, "three", "três"));
        palavras.add(new Palavra(1, "pineapple", "abacaxi"));
        palavras.add(new Palavra(1, "sheep", "ovelha"));
        palavras.add(new Palavra(1, "guava", "goiaba"));
        palavras.add(new Palavra(1, "watermelon", "melancia"));
        palavras.add(new Palavra(1, "strawberry", "morango"));
        palavras.add(new Palavra(1, "pear", "pera"));
        palavras.add(new Palavra(1, "grape", "uva"));
        palavras.add(new Palavra(1, "avocado", "abacate"));
        palavras.add(new Palavra(1, "cell phone", "celular"));
        palavras.add(new Palavra(1, "helicopter", "helicoptero"));
        palavras.add(new Palavra(2, "OPEN THE DOOR", "ABRA A PORTA"));
        palavras.add(new Palavra(2, "PUSH THE TABLE", "EMPURRE A MESA"));
        palavras.add(new Palavra(2, "PULL THE TABLE", "PUXE A MESA"));
        palavras.add(new Palavra(2, "KICK THE BALL", "CHUTE A BOLA"));
        palavras.add(new Palavra(2, "OPEN THE BOOK", "ABRA O LIVRO"));
        palavras.add(new Palavra(2, "OPEN THE MIND", "ABRA A MENTE"));
        palavras.add(new Palavra(2, "OPEN THE WINDOW", "ABRA A JANELA"));
        palavras.add(new Palavra(2, "THE WOMAN IS COOKING", "A MULHER ESTÁ COZINHANDO"));
        palavras.add(new Palavra(2, "WRITING ON THE SHEET", "ESCREVENDO NA FOLHA"));
        palavras.add(new Palavra(2, "HIT THE TARGET", "ACERTE O ALVO"));
        palavras.add(new Palavra(2, "DANCE THE MUSIC", "DANCE A MÚSICA"));
        palavras.add(new Palavra(2, "I AM SWIMMING", "EU ESTOU NADANDO"));
        palavras.add(new Palavra(2, "I AM RUNNING", "EU ESTOU CORRENDO"));
        palavras.add(new Palavra(2, "TURN ON THE LIGHT", "ACENDA A LUZ"));
        palavras.add(new Palavra(2, "TAKE CARE OF THE TREE", "CUIDAR DA ÁRVORE"));
        palavras.add(new Palavra(2, "TAKE A BATH", "TOMAR UM BANHO"));
        palavras.add(new Palavra(2, "CLIMB THE LADDER", "SUBIR A ESCADA"));
        palavras.add(new Palavra(2, "DRIVE THE CAR", "DIRIGIR O CARRO"));
        palavras.add(new Palavra(2, "PRESS THE RINGER", "PRESSIONE A CAMPAINHA"));
        palavras.add(new Palavra(2, "DRINK WATER", "BEBER ÁGUA"));
        palavras.add(new Palavra(2, "RAISE THE HAND", "LEVANTAR A MÃO"));
        palavras.add(new Palavra(2, "WASH THE DISHES", "LAVAR OS PRATOS"));
        palavras.add(new Palavra(2, "WATCH TV", "ASSISTIR TV"));
        palavras.add(new Palavra(2, "DRINK BEER", "BEBER CERVEJA"));
        palavras.add(new Palavra(2, "THINK", "PENSAR"));
        palavras.add(new Palavra(2, "PUT SUGAR IN THE COFFEE", "COLOCAR AÇÚCAR NO CAFÉ"));

        PalavraDAOSQLite palavraDAOSQLite = new PalavraDAOSQLite(ConexaoSQLite.getInstance(dashBoardActivity));

        for(Palavra p: palavras){
            if(palavraDAOSQLite.palavraIsCadastrada(p.getNome())){

            }else{
                palavraDAOSQLite.insert(p);
            }
        }

        ArrayList<Opcao> opcoes = new ArrayList<>();
        opcoes.add(new Opcao("", new String[]{"", "", ""}, new String[]{"", "", ""}, 1));


    }

    private void inserirRanking() {
        ArrayAdapter arrayAdapter =  new RankingAdapter(this.dashBoardActivity, (ArrayList<Ranking>) Ranking.getListRanking());
        ListView lvOpcoes = (ListView) dashBoardActivity.findViewById(R.id.list_ranking);
        lvOpcoes.setAdapter(arrayAdapter);
    }
}