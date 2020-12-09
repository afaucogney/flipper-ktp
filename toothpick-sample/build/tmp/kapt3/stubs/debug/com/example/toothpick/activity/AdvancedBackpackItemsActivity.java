package com.example.toothpick.activity;

import java.lang.System;

/**
 * Advanced version of the BackpackItemsActivity.
 *
 * In this example, the backpack is retained on configuration
 * changes as it belongs to the view model scope which follows
 * the lifecycle of view model instances: when an instance is
 * destroyed, and later recreated, the scope remains unchanged
 * and the backpack instance will be the same.
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0007J\"\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0012\u0010\u001e\u001a\u00020\u00162\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006#"}, d2 = {"Lcom/example/toothpick/activity/AdvancedBackpackItemsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "coordinatorLayout", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "notificationHelper", "Lcom/example/toothpick/helper/NotificationHelper;", "getNotificationHelper", "()Lcom/example/toothpick/helper/NotificationHelper;", "notificationHelper$delegate", "Ltoothpick/ktp/delegate/InjectDelegate;", "viewAdapter", "Lcom/example/toothpick/adapter/IBackpackAdapter;", "getViewAdapter", "()Lcom/example/toothpick/adapter/IBackpackAdapter;", "viewAdapter$delegate", "viewModel", "Lcom/example/toothpick/viewmodel/BackpackViewModel;", "getViewModel", "()Lcom/example/toothpick/viewmodel/BackpackViewModel;", "viewModel$delegate", "goToAddNewActivity", "", "injectDependencies", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupUIComponents", "Companion", "toothpick-sample_debug"})
public final class AdvancedBackpackItemsActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final toothpick.ktp.delegate.InjectDelegate notificationHelper$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final toothpick.ktp.delegate.InjectDelegate viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final toothpick.ktp.delegate.InjectDelegate viewAdapter$delegate = null;
    private androidx.coordinatorlayout.widget.CoordinatorLayout coordinatorLayout;
    public static final int ADD_NEW_REQUEST = 1;
    public static final com.example.toothpick.activity.AdvancedBackpackItemsActivity.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.toothpick.helper.NotificationHelper getNotificationHelper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.toothpick.viewmodel.BackpackViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.toothpick.adapter.IBackpackAdapter getViewAdapter() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @androidx.annotation.VisibleForTesting()
    public final void injectDependencies() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void setupUIComponents() {
    }
    
    private final void goToAddNewActivity() {
    }
    
    public AdvancedBackpackItemsActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/toothpick/activity/AdvancedBackpackItemsActivity$Companion;", "", "()V", "ADD_NEW_REQUEST", "", "toothpick-sample_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}