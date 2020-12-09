package com.example.toothpick.activity;

import java.lang.Override;
import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class AddNewActivity__Factory implements Factory<AddNewActivity> {
  private MemberInjector<AddNewActivity> memberInjector = new com.example.toothpick.activity.AddNewActivity__MemberInjector();

  @Override
  public AddNewActivity createInstance(Scope scope) {
    scope = getTargetScope(scope);
    AddNewActivity addNewActivity = new AddNewActivity();
    memberInjector.inject(addNewActivity, scope);
    return addNewActivity;
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
