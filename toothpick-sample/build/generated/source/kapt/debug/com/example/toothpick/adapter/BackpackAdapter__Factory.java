package com.example.toothpick.adapter;

import com.example.toothpick.model.Backpack;
import java.lang.Override;
import toothpick.Factory;
import toothpick.Scope;

public final class BackpackAdapter__Factory implements Factory<BackpackAdapter> {
  @Override
  public BackpackAdapter createInstance(Scope scope) {
    scope = getTargetScope(scope);
    Backpack param1 = scope.getInstance(Backpack.class);
    BackpackAdapter backpackAdapter = new BackpackAdapter(param1);
    return backpackAdapter;
  }

  @Override
  public Scope getTargetScope(Scope scope) {
    return scope;
  }

  @Override
  public boolean hasScopeAnnotation() {
    return false;
  }

  @Override
  public boolean hasSingletonAnnotation() {
    return false;
  }

  @Override
  public boolean hasReleasableAnnotation() {
    return false;
  }

  @Override
  public boolean hasProvidesSingletonAnnotation() {
    return false;
  }

  @Override
  public boolean hasProvidesReleasableAnnotation() {
    return false;
  }
}
