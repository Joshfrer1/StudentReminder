// Generated by view binder compiler. Do not edit!
package com.example.studentreminder.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.studentreminder.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class EditEventItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button edit;

  @NonNull
  public final CheckBox todo;

  private EditEventItemBinding(@NonNull ConstraintLayout rootView, @NonNull Button edit,
      @NonNull CheckBox todo) {
    this.rootView = rootView;
    this.edit = edit;
    this.todo = todo;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static EditEventItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static EditEventItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.edit_event_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static EditEventItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edit;
      Button edit = ViewBindings.findChildViewById(rootView, id);
      if (edit == null) {
        break missingId;
      }

      id = R.id.todo;
      CheckBox todo = ViewBindings.findChildViewById(rootView, id);
      if (todo == null) {
        break missingId;
      }

      return new EditEventItemBinding((ConstraintLayout) rootView, edit, todo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
