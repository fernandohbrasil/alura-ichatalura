package com.br.fernandohbrasil.ichatalura.callback;

import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.br.fernandohbrasil.ichatalura.MainActivity;
import com.br.fernandohbrasil.ichatalura.event.MensagemEvent;
import com.br.fernandohbrasil.ichatalura.model.Mensagem;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagensCallBack implements Callback<Mensagem> {

    Context context;
    EventBus eventBus;

    public OuvirMensagensCallBack(Context context, EventBus eventBus) {
        this.context = context;
        this.eventBus = eventBus;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if (response.isSuccessful()) {
            Mensagem mensagem = response.body();

            eventBus.post(new MensagemEvent(mensagem));
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {

    }
}
