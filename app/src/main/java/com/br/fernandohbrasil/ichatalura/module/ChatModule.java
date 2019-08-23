package com.br.fernandohbrasil.ichatalura.module;

import android.app.Application;

import com.br.fernandohbrasil.ichatalura.service.ChatService;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ChatModule{

    private Application app;

    public ChatModule(Application app) {
        this.app = app;
    }

    @Provides
    public ChatService getChatService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.79:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatService chatService = retrofit.create(ChatService.class);
        return chatService;
    }

    @Provides
    public Picasso picasso() {
        Picasso picasso = new Picasso.Builder(app).build();
        return picasso;
    }

}
