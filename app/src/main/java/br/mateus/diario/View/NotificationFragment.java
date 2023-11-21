package br.mateus.diario.View;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

import br.mateus.diario.R;

public class NotificationFragment extends Fragment {

    private TimePicker timePicker;
    private Button btnScheduleNotification;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        timePicker = view.findViewById(R.id.timePicker);
        btnScheduleNotification = view.findViewById(R.id.btnScheduleNotification);

        btnScheduleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleNotification();
            }
        });

        return view;
    }

    private void scheduleNotification() {
        // Obter o horário selecionado do TimePicker
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        // Lógica para agendar a notificação
        // Utilize AlarmManager e BroadcastReceiver ou WorkManager para agendar a notificação diária
        // Criar e exibir a notificação com o NotificationManager

        // Exemplo simples (não funcional) de agendamento de notificação:
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(getContext(), NotificationFragment.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
