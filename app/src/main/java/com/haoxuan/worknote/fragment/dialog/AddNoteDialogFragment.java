package com.haoxuan.worknote.fragment.dialog;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.haoxuan.worknote.R;
import com.haoxuan.worknote.bean.menu.MenuDetail;
import com.haoxuan.worknote.constant.K;

import java.util.ArrayList;
import java.util.Date;

import network.SocketTask;

import static network.SocketTask.Method.SPOST;

/**
 * Created by skateboard on 16-2-19.
 */
public class AddNoteDialogFragment extends DialogFragment implements SocketTask.OnSocketRequestListener {

    private TextView confirm;
    private String pageName;
    private TextInputLayout inPageName;
    private ArrayList<MenuDetail> menus;
    private SocketTask mSocketTask;
    private boolean canCreate;
    private OnCreateNoteFinishedListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCreateNoteFinishedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implements OnCreateNoteFinishedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        menus = (ArrayList<MenuDetail>) bundle.getSerializable("menu_list");
        mSocketTask = new SocketTask();
        mSocketTask.setOnSocketRequestListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_addnote, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirm = (TextView) view.findViewById(R.id.add_note);
        confirm.setTextColor(Color.GRAY);
        confirm.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "iconfont/confirmaddnote.ttf"));
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
        inPageName = (TextInputLayout) view.findViewById(R.id.note_name);
        inPageName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    inPageName.setErrorEnabled(true);
                    refreshConfirmButton(false);
                    canCreate = false;
                } else {
                    inPageName.setErrorEnabled(false);
                    boolean isRepeat = false;
                    for (MenuDetail item : menus) {
                        if ((s.toString()).equals(item.getName())) {
                            isRepeat = true;
                        }
                    }

                    if (isRepeat) {
                        inPageName.setErrorEnabled(true);
                        inPageName.setError(getResources().getString(R.string.tip_note_has_created));
                    }
                    refreshConfirmButton(!isRepeat);

                }
            }
        });
    }

    private void refreshConfirmButton(boolean isCreated) {
        if (isCreated) {
            pageName=inPageName.getEditText().getText().toString();
            confirm.setEnabled(true);
            confirm.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
            canCreate = true;
        } else {
            confirm.setEnabled(false);
            confirm.setTextColor(Color.GRAY);
            canCreate = false;
        }
    }

    private void addNote() {
        if (canCreate) {
            mSocketTask.sendRequest(SPOST, K.CREATE_NOTE, pageName);
        }


    }

    @Override
    public void onError(String message) {

        Toast.makeText(getActivity(), "create note failure", Toast.LENGTH_SHORT).show();
        System.out.println(message);
    }

    @Override
    public void onSuccess(String result) {
        MenuDetail noteInfo = new MenuDetail();
        noteInfo.setId(String.valueOf(System.currentTimeMillis()));
        noteInfo.setName(pageName);
        mListener.onCreateNoteFinished(noteInfo);
        dismiss();
    }

    public interface OnCreateNoteFinishedListener {
       void onCreateNoteFinished(MenuDetail detail);

    }

}
