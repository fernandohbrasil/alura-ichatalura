package com.br.fernandohbrasil.ichatalura;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.br.fernandohbrasil.ichatalura.adapter.MensagemAdapter;
import com.br.fernandohbrasil.ichatalura.app.ChatApplication;
import com.br.fernandohbrasil.ichatalura.callback.EnviarMensagemCallback;
import com.br.fernandohbrasil.ichatalura.callback.OuvirMensagensCallBack;
import com.br.fernandohbrasil.ichatalura.component.ChatComponent;
import com.br.fernandohbrasil.ichatalura.event.MensagemEvent;
import com.br.fernandohbrasil.ichatalura.model.Mensagem;
import com.br.fernandohbrasil.ichatalura.service.ChatService;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

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

    @Inject
    EventBus eventBus;

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

        eventBus.register(this);
    }

    @OnClick(R.id.btn_enviar)
    public void enviarMensagem(){
        chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString())).enqueue(new EnviarMensagemCallback());
    }


    @Subscribe
    public void colocaNaLista(MensagemEvent mensagemEvent) {
        mensagens.add(mensagemEvent.getMensagem());
        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(adapter);

        ouvirMensagem();
    }

    @Subscribe
    public void ouvirMensagem(){
        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagensCallBack(this, eventBus));
    }

    @Override
    protected void onStop() {
        super.onStop();

        eventBus.unregister(this);
    }
}