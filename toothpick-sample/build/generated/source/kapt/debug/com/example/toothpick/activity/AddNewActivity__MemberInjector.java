package com.example.toothpick.activity;

import com.example.toothpick.helper.BackpackItemValidator;
import java.lang.Override;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class AddNewActivity__MemberInjector implements MemberInjector<AddNewActivity> {
  @Override
  public void inject(AddNewActivity target, Scope scope) {
    target.backpackItemValidator = scope.getInstance(BackpackItemValidator.class);
  }
}
