package com.example.toothpick.model;

import java.lang.Override;
import toothpick.Factory;
import toothpick.Scope;

public final class Backpack__Factory implements Factory<Backpack> {
  @Override
  public Backpack createInstance(Scope scope) {
    Backpack backpack = new Backpack();
    return backpack;
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
