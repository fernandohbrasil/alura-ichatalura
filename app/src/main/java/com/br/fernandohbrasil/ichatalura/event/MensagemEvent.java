package com.br.fernandohbrasil.ichatalura.event;

import com.br.fernandohbrasil.ichatalura.model.Mensagem;

public class MensagemEvent {
    private Mensagem mensagem;

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }
}
