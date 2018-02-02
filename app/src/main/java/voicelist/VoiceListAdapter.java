package voicelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.voicerecorder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nazila on 1/31/2018.
 */

public class VoiceListAdapter extends RecyclerView.Adapter<VoiceListAdapter.VoiceListHolder> {
    private Context mContext;
    List<VoiceRecordedModel> voiceModels = new ArrayList<>();

    private VoiceListHolder holder;

    public VoiceListAdapter(Context mContext, List<VoiceRecordedModel> voiceModels) {
        this.mContext = mContext;
        this.voiceModels = voiceModels;
    }

    public void addNewItem(List<VoiceRecordedModel> newVoiceModels) {
        voiceModels.clear();
        voiceModels.addAll(newVoiceModels);
        notifyDataSetChanged();
    }

    @Override
    public VoiceListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View voiceItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.voice_list_item, parent, false);
        holder = new VoiceListHolder(voiceItemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(VoiceListHolder holder, int position) {
        holder.voiceNameTextView.setText(voiceModels.get(position).getVoiceName());
        //  holder.voicePathTextView.setText(voiceModels.get(position).getVoicePath());
    }

    @Override
    public int getItemCount() {
        return voiceModels.size();
    }

    class VoiceListHolder extends RecyclerView.ViewHolder {
        TextView voiceNameTextView;
//        TextView voicePathTextView;

        VoiceListHolder(View itemView) {
            super(itemView);
            voiceNameTextView = itemView.findViewById(R.id.voiceName_textView);

            //  itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View clickedView) {
//
//                    MainActivity.playClickedVoice(voiceModels.get(getAdapterPosition())
//                            .getVoicePath());
//
//        }
    }
}
