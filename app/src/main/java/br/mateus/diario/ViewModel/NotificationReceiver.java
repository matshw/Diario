package br.mateus.diario.ViewModel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.mateus.diario.View.NotificationHelper;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationHelper notificationHelper = new NotificationHelper(context);
        notificationHelper.createNotification("Escreva seu diário", "Está na hora de você escrever como foi o seu dia!");
    }
}
