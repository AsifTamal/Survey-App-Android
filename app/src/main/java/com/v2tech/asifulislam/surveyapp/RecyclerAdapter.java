package com.v2tech.asifulislam.surveyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.v2tech.asifulislam.surveyapp.models.SavedDataModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewholder> {
    public  List<SavedDataModel>  item_List=new ArrayList<>();
   Context mcontx;

    public RecyclerAdapter(Context mcontx) {
        this.mcontx = mcontx;
    }

    @NonNull
    @Override
    public RecyclerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_recyclrvw,parent,false);
        return new RecyclerViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewholder holder, int i) {
        try {

            holder.mtxtQuestiondropdown.setText(item_List.get(i).getDropdownQuestion());

            holder.mtxtAnswerdropdown.setText(item_List.get(i).getDropdownAnswar());
            holder.mtxtQuestionRadio.setText(item_List.get(i).getMultipleChoiceQuestion());
            holder.mtxtAnswerRadio.setText(item_List.get(i).getMultipleChoiceAnswar());
            holder.mtxtQuestionChk.setText(item_List.get(i).getCheckQuestion());
            holder.mtxtAnswerChk.setText(item_List.get(i).getCheckAnswar());
            holder.mtxtQuestionText.setText(item_List.get(i).getTextQuestion());
            holder.mtxtAnswerText.setText(item_List.get(i).getTextAnswar());
            holder.mtxtQuestionPhn.setText(item_List.get(i).getNumberQuestion());
            holder.mtxtAnswerPhn.setText(item_List.get(i).getNumberAnswar());

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mcontx, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return item_List.size();
    }

    public void setWordlist(List<SavedDataModel> savedDataModels) {
        this.item_List.clear();
        this.item_List=savedDataModels;
        notifyDataSetChanged();
    }

    public class RecyclerViewholder  extends RecyclerView.ViewHolder {

        TextView mtxtQuestiondropdown,mtxtAnswerdropdown,mtxtQuestionRadio,mtxtAnswerRadio,mtxtQuestionChk,mtxtAnswerChk
                ,mtxtQuestionText,mtxtAnswerText,mtxtQuestionPhn,mtxtAnswerPhn;
      public RecyclerViewholder(@NonNull View itemView) {

          super(itemView);

          mtxtQuestiondropdown=(TextView)itemView.findViewById(R.id.txtQuestiondropdown);
                  mtxtAnswerdropdown=(TextView)itemView.findViewById(R.id.txtAnswerdropdown);
                  mtxtQuestionRadio=(TextView)itemView.findViewById(R.id.txtQuestionRadio);
                  mtxtAnswerRadio=(TextView)itemView.findViewById(R.id.txtAnswerRadio);
                  mtxtQuestionChk=(TextView)itemView.findViewById(R.id.txtQuestionChk);
                  mtxtAnswerChk=(TextView)itemView.findViewById(R.id.txtAnswerChk);
                  mtxtQuestionText=(TextView)itemView.findViewById(R.id.txtQuestionText);
                  mtxtAnswerText=(TextView)itemView.findViewById(R.id.txtAnswerText);
                  mtxtQuestionPhn=(TextView)itemView.findViewById(R.id.txtQuestionPhn);
                  mtxtAnswerPhn=(TextView)itemView.findViewById(R.id.txtAnswerPhn);
      }
  }
}
