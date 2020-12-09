package com.example.toothpick.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0018\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/toothpick/adapter/BackpackAdapter;", "Lcom/example/toothpick/adapter/IBackpackAdapter;", "backpack", "Lcom/example/toothpick/model/Backpack;", "(Lcom/example/toothpick/model/Backpack;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "Lcom/example/toothpick/adapter/BackpackAdapter$ItemHolder;", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ItemHolder", "toothpick-sample_debug"})
@toothpick.InjectConstructor()
public final class BackpackAdapter extends com.example.toothpick.adapter.IBackpackAdapter {
    private final com.example.toothpick.model.Backpack backpack = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.toothpick.adapter.BackpackAdapter.ItemHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.toothpick.adapter.BackpackAdapter.ItemHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public BackpackAdapter(@org.jetbrains.annotations.NotNull()
    com.example.toothpick.model.Backpack backpack) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/toothpick/adapter/BackpackAdapter$ItemHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "textView", "Landroid/widget/TextView;", "(Landroid/widget/TextView;)V", "getTextView", "()Landroid/widget/TextView;", "toothpick-sample_debug"})
    public static final class ItemHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView textView = null;
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTextView() {
            return null;
        }
        
        public ItemHolder(@org.jetbrains.annotations.NotNull()
        android.widget.TextView textView) {
            super(null);
        }
    }
}