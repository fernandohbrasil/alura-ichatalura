package com.br.fernandohbrasil.ichatalura.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.fernandohbrasil.ichatalura.R;
import com.br.fernandohbrasil.ichatalura.model.Mensagem;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MensagemAdapter extends BaseAdapter {

    private List<Mensagem> mensagens;
    private Activity activity;
    private int idDoCliente;

    @BindView(R.id.tv_texto)
    TextView texto;
    @BindView(R.id.iv_avatar_mensagem)
    ImageView avatar;

    @Inject
    Picasso picaso;

    public MensagemAdapter(int idDoCliente, List<Mensagem> mensagens, Activity activity) {
        this.idDoCliente = idDoCliente;
        this.mensagens = mensagens;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Mensagem getItem(int i) {
        return mensagens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View linha = activity.getLayoutInflater().inflate(R.layout.mensagem, viewGroup, false);

        ButterKnife.bind(this, linha);

        picaso.get()
                .load("https://api.adorable.io/avatars/285/" + idDoCliente + ".png")
                .into(avatar);

        Mensagem mensagem = getItem(i);
        int idMensagem = mensagem.getId();

        picaso.get()
                .load("https://api.adorable.io/avatars/285/" + idMensagem + ".png")
                .into(avatar);

        if (idDoCliente != mensagem.getId()) {
            linha.setBackgroundColor(Color.GREEN);
        }

        texto.setText(mensagem.getTexto());

        return linha;
    }


}
