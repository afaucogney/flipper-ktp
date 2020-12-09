package com.example.toothpick.helper;

import java.lang.Override;
import toothpick.Factory;
import toothpick.Scope;

public final class BackpackItemValidator__Factory implements Factory<BackpackItemValidator> {
  @Override
  public BackpackItemValidator createInstance(Scope scope) {
    BackpackItemValidator backpackItemValidator = new BackpackItemValidator();
    return backpackItemValidator;
  }

  @Override
  public Scope getTargetScope(Scope scope) {
    return scope.getRootScope();
  }

  @Override
  public boolean hasScopeAnnotation() {
    return true;
  }

  @Override
  public boolean hasSingletonAnnotation() {
    return true;
  }

  @Override
  public boolean hasReleasableAnnotation() {
    return true;
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
