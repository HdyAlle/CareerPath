package com.example.careerpath;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * Custom Bottom Navigation View for CareerPath App.
 *
 * - Letakkan <com.example.careerpath.BottomNav .../> di layout utama (misal: main_activity.xml)
 * - Di MainActivity, gunakan bottomNav.setOnMenuSelectedListener((menuId) -> ...)
 */
public class BottomNav extends FrameLayout {

    public static final int MENU_HOME = 0;
    public static final int MENU_SEARCH = 1;
    public static final int MENU_MESSAGE = 2;
    public static final int MENU_PROFILE = 3;

    private ImageView navHome, navSearch, navMessage, navProfile, navAdd;
    private OnMenuSelectedListener menuSelectedListener;

    public interface OnMenuSelectedListener {
        void onMenuSelected(int menuId);
    }

    public BottomNav(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BottomNav(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomNav(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.bottom_nav, this, true);

        navHome = findViewById(R.id.nav_home);
        navSearch = findViewById(R.id.nav_search);
        navMessage = findViewById(R.id.nav_message);
        navProfile = findViewById(R.id.nav_profile);
        navAdd = findViewById(R.id.nav_add);

        setupListeners();
    }

    private void setupListeners() {
        navHome.setOnClickListener(v -> {
            if (menuSelectedListener != null) menuSelectedListener.onMenuSelected(MENU_HOME);
        });
        navSearch.setOnClickListener(v -> {
            if (menuSelectedListener != null) menuSelectedListener.onMenuSelected(MENU_SEARCH);
        });
        navMessage.setOnClickListener(v -> {
            if (menuSelectedListener != null) menuSelectedListener.onMenuSelected(MENU_MESSAGE);
        });
        navProfile.setOnClickListener(v -> {
            if (menuSelectedListener != null) menuSelectedListener.onMenuSelected(MENU_PROFILE);
        });
        navAdd.setOnClickListener(v -> {
            // TODO: Aksi tombol Add
        });
    }

    /**
     * Listener untuk dipanggil saat menu dipilih.
     */
    public void setOnMenuSelectedListener(OnMenuSelectedListener listener) {
        this.menuSelectedListener = listener;
    }

    /**
     * Set active menu (ubah warna icon sesuai menuId)
     * @param menuId use one of: MENU_HOME, MENU_SEARCH, MENU_MESSAGE, MENU_PROFILE
     */
    public void setActiveMenu(int menuId) {
        int colorActive = ContextCompat.getColor(getContext(), R.color.blue_active);
        int colorInactive = ContextCompat.getColor(getContext(), R.color.gray_inactive);

        navHome.setColorFilter(colorInactive);
        navSearch.setColorFilter(colorInactive);
        navMessage.setColorFilter(colorInactive);
        navProfile.setColorFilter(colorInactive);

        switch (menuId) {
            case MENU_HOME:
                navHome.setColorFilter(colorActive);
                break;
            case MENU_SEARCH:
                navSearch.setColorFilter(colorActive);
                break;
            case MENU_MESSAGE:
                navMessage.setColorFilter(colorActive);
                break;
            case MENU_PROFILE:
                navProfile.setColorFilter(colorActive);
                break;
        }
    }
}