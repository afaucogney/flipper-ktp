package com.example.toothpick.helper;

import java.lang.Override;
import toothpick.Factory;
import toothpick.Scope;

public final class NotificationHelper__Factory implements Factory<NotificationHelper> {
  @Override
  public NotificationHelper createInstance(Scope scope) {
    NotificationHelper notificationHelper = new NotificationHelper();
    return notificationHelper;
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
