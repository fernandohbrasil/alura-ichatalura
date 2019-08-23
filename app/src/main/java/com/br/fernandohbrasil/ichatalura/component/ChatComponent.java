package com.br.fernandohbrasil.ichatalura.component;

import com.br.fernandohbrasil.ichatalura.MainActivity;
import com.br.fernandohbrasil.ichatalura.adapter.MensagemAdapter;
import com.br.fernandohbrasil.ichatalura.module.ChatModule;

import dagger.Component;

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);

    void inject(MensagemAdapter adapter);
}
