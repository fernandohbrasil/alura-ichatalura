package com.br.fernandohbrasil.ichatalura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.br.fernandohbrasil.ichatalura.adapter.MensagemAdapter;
import com.br.fernandohbrasil.ichatalura.app.ChatApplication;
import com.br.fernandohbrasil.ichatalura.component.ChatComponent;
import com.br.fernandohbrasil.ichatalura.callback.EnviarMensagemCallback;
import com.br.fernandohbrasil.ichatalura.model.Mensagem;
import com.br.fernandohbrasil.ichatalura.service.ChatService;

import java.util.ArrayList;
import java.util.List;

import com.br.fernandohbrasil.ichatalura.callback.OuvirMensagensCallBack;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    int idDoCliente = 20;

    @BindView(R.id.btn_enviar)
    Button button;
    @BindView(R.id.et_texto)
    EditText editText;
    @BindView(R.id.lv_mensagens)
    ListView listaDeMensagens;
    @BindView(R.id.iv_avatar_usuario)
    ImageView avatar;

    @Inject
    Picasso picasso;

    private List<Mensagem> mensagens;

    @Inject
    ChatService chatService;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        picasso.get()
                .load("https://api.adorable.io/avatars/285/" + idDoCliente + ".png")
                .into(avatar);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

        mensagens = new ArrayList<>();

        ouvirMensagem();
    }

    @OnClick(R.id.btn_enviar)
    public void enviarMensagem(){
        chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString())).enqueue(new EnviarMensagemCallback());
    }

    public void colocaNaLista(Mensagem mensagem) {
        mensagens.add(mensagem);
        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(adapter);

        ouvirMensagem();
    }

    public void ouvirMensagem(){
        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagensCallBack(this));
    }
}