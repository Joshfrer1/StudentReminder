package com.example.studentreminder;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.example.studentreminder.databinding.EditEventItemBinding;
import com.example.studentreminder.models.ToDoItem;
import com.example.studentreminder.viewmodels.ToDoViewModel;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private ObservableArrayList<ToDoItem> todos;
    private ToDoViewModel viewModel;
    private OnEditClicked listener;

    public interface OnEditClicked {
        void onEditClicked(ToDoItem todo);
    }

    public EventAdapter(ToDoViewModel viewModel, OnEditClicked listener) {
        this.todos = viewModel.getTodos();
        this.listener = listener;
        this.viewModel = viewModel;
        this.todos.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<ToDoItem>>() {
            @Override
            public void onChanged(ObservableList<ToDoItem> sender) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<ToDoItem> sender, int positionStart, int itemCount) {
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList<ToDoItem> sender, int positionStart, int itemCount) {
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<ToDoItem> sender, int fromPosition, int toPosition, int itemCount) {
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onItemRangeRemoved(ObservableList<ToDoItem> sender, int positionStart, int itemCount) {
                notifyItemRangeRemoved(positionStart, itemCount);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EditEventItemBinding binding = EditEventItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoItem todo = todos.get(position);
        holder.binding.todo.setText(todo.title);
        System.out.println(todo.isCompleted);
        if (todo.isCompleted) {
            holder.binding.todo.setTextColor(Color.RED);
        } else {
            holder.binding.todo.setTextColor(Color.BLACK);
        }
        holder.binding.todo.setChecked(todo.isCompleted);
        holder.binding.todo.setOnClickListener(view -> {

            viewModel.markTodoCommand(todo, !todo.isCompleted);
        });
        holder.binding.edit.setOnClickListener((view) -> {
            listener.onEditClicked(todo);
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public EditEventItemBinding binding;
        public ViewHolder(@NonNull EditEventItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
