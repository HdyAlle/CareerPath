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
 * Updated: 2025-07-08 16:22:48 UTC - Current User: Arsieruuu
 */
public class BottomNav extends FrameLayout {

    public static final int MENU_HOME = 0;
    public static final int MENU_SEARCH = 1;
    public static final int MENU_ADD = 2; // ✅ NEW: Add Job button
    public static final int MENU_MESSAGE = 3;
    public static final int MENU_PROFILE = 4;

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
        setupAddButtonStyle();
    }

    private void setupListeners() {
        navHome.setOnClickListener(v -> {
            if (menuSelectedListener != null) {
                menuSelectedListener.onMenuSelected(MENU_HOME);
            }
        });

        navSearch.setOnClickListener(v -> {
            if (menuSelectedListener != null) {
                menuSelectedListener.onMenuSelected(MENU_SEARCH);
            }
        });

        navMessage.setOnClickListener(v -> {
            if (menuSelectedListener != null) {
                menuSelectedListener.onMenuSelected(MENU_MESSAGE);
            }
        });

        navProfile.setOnClickListener(v -> {
            if (menuSelectedListener != null) {
                menuSelectedListener.onMenuSelected(MENU_PROFILE);
            }
        });

        // ✅ ADD BUTTON: Navigate to AddJobActivity - User: Arsieruuu
        navAdd.setOnClickListener(v -> {
            if (menuSelectedListener != null) {
                menuSelectedListener.onMenuSelected(MENU_ADD);
            }
            animateAddButton();
        });
    }

    private void setupAddButtonStyle() {
        if (navAdd != null) {
            navAdd.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle_add_background));
            navAdd.setColorFilter(ContextCompat.getColor(getContext(), android.R.color.white));
            navAdd.setElevation(8f);
        }
    }

    private void animateAddButton() {
        if (navAdd != null) {
            navAdd.animate()
                    .scaleX(0.9f)
                    .scaleY(0.9f)
                    .setDuration(100)
                    .withEndAction(() -> {
                        navAdd.animate()
                                .scaleX(1.0f)
                                .scaleY(1.0f)
                                .setDuration(100)
                                .start();
                    })
                    .start();
        }
    }

    public void setOnMenuSelectedListener(OnMenuSelectedListener listener) {
        this.menuSelectedListener = listener;
    }

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

        setupAddButtonStyle();
    }
}