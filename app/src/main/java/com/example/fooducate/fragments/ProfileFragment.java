package com.example.fooducate.fragments;

import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.CompoundButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.fooducate.NotificationBroadcast;
import com.example.fooducate.R;
import com.example.fooducate.activities.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.Objects;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment_layout, container, false);

        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        TextView signOut = view.findViewById(R.id.sign_out);
        CardView cardView = view.findViewById(R.id.cardSignOut);

        ObjectAnimator moveAnim = ObjectAnimator.ofFloat(cardView, "Y", 1000);
        moveAnim.setDuration(2000);
        moveAnim.setInterpolator(new BounceInterpolator());
        moveAnim.start();

        VideoView videoView = view.findViewById(R.id.video_view);
        String videoPath = "android.resource://" + getContext().getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        final SwitchCompat notificationToggle = view.findViewById(R.id.switch1);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        String emailCurrentUSer = currentUser.getEmail();
        int index = emailCurrentUSer.indexOf('@');
        String username = emailCurrentUSer.substring(0, index);

        TextView name = view.findViewById(R.id.username);
        appendColoredText(name, username, ContextCompat.getColor(getContext(),R.color.grey));

        TextView emailTextView = view.findViewById(R.id.email);
        appendColoredText(emailTextView, emailCurrentUSer, ContextCompat.getColor(getContext(),R.color.grey));

        boolean value = false;

        final SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("isChecked", 0);

        value = sharedPreferences.getBoolean("isChecked", value);

        notificationToggle.setChecked(value);

        notificationToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedPreferences.edit().putBoolean("isChecked", true).apply();

                    createNotificationChannel();

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, 12);
                    calendar.set(Calendar.MINUTE, 00);
                    calendar.set(Calendar.SECOND, 0);

                    Intent intent = new Intent(getActivity(), NotificationBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager alarmManager = (AlarmManager) Objects.requireNonNull(getActivity()).getSystemService(Context.ALARM_SERVICE);

                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                    long timeAtButtonClick = System.currentTimeMillis();
                    long secondsInMills = 1000 * 10;
                    alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + secondsInMills, pendingIntent);

                } else {
                    sharedPreferences.edit().putBoolean("isChecked", false).apply();
                }
            }
        });
        return view;
    }
    public static void appendColoredText(TextView textView, String text, int color) {
        int start = textView.getText().length();
        textView.append(text);
        int end = textView.getText().length();

        Spannable spannableText = (Spannable) textView.getText();
        spannableText.setSpan(new ForegroundColorSpan(color), start, end, 0);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "NotificationChannel";
            String description = "Channel for daily remainder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("notifyMe", name, importance);

            channel.setDescription(description);

            NotificationManager notificationManager = Objects.requireNonNull(getActivity()).getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
