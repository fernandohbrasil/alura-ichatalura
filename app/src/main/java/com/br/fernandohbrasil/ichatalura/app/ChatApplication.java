package com.br.fernandohbrasil.ichatalura.app;

import android.app.Application;

import com.br.fernandohbrasil.ichatalura.component.ChatComponent;
import com.br.fernandohbrasil.ichatalura.component.DaggerChatComponent;
import com.br.fernandohbrasil.ichatalura.module.ChatModule;


public class ChatApplication extends Application {

    private ChatComponent component;

    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent.builder()
                .chatModule(new ChatModule(this))
                .build();
    }

    public ChatComponent getComponent() {
        return component;
    }
}
