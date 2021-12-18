package com.example.studentreminder;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentreminder.models.CategoryItem;
import com.example.studentreminder.viewmodels.CategoryViewModel;

public class NewCategoryFragment extends Fragment {
    public NewCategoryFragment() {
        super(R.layout.new_category);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CategoryItem item = new CategoryItem();
        EditText editText = view.findViewById(R.id.cat_edit_text);
        CategoryViewModel categoryViewModel = new ViewModelProvider(getActivity()).get(CategoryViewModel.class);
        if (categoryViewModel.getCurrentCategory().getValue() != null) {
            item = categoryViewModel.getCurrentCategory().getValue();
            editText.setText(item.category);
            item.category = editText.getText().toString();
            categoryViewModel.updateCategory(item);
        }
        else{
            item.category = editText.getText().toString();
            categoryViewModel.saveNewCategory(item.category);
        }



    }
}
