package com.example.fooducate;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment_layout, container, false);

        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        TextView signOut = view.findViewById(R.id.sign_out);
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
        return view;
    }
    public static void appendColoredText(TextView textView, String text, int color) {
        int start = textView.getText().length();
        textView.append(text);
        int end = textView.getText().length();

        Spannable spannableText = (Spannable) textView.getText();
        spannableText.setSpan(new ForegroundColorSpan(color), start, end, 0);
    }
}
