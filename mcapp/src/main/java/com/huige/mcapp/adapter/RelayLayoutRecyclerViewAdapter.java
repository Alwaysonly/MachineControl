package com.huige.mcapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.huige.mcapp.R;
import com.huige.mcapp.vo.VoRelayParameter;


import java.util.ArrayList;
import java.util.List;

public class RelayLayoutRecyclerViewAdapter extends RecyclerView.Adapter<RelayLayoutRecyclerViewAdapter.RelayLayoutRecyclerView> {

    private List<VoRelayParameter> voRelayParameters;
    private OnRelayLayoutListener onRelayLayoutListener;

    public RelayLayoutRecyclerViewAdapter(List<VoRelayParameter> voRelayParameters, OnRelayLayoutListener onRelayLayoutListener) {
        this.voRelayParameters = new ArrayList<>();
        this.onRelayLayoutListener = onRelayLayoutListener;
        if (voRelayParameters != null) {
            for (int i = 0; i < voRelayParameters.size(); i++) {
                if (voRelayParameters.get(i).getRelayEnable()) {
                    voRelayParameters.get(i).setIndex(i+1);
                    this.voRelayParameters.add(voRelayParameters.get(i));
                }
            }
        }
    }

    public interface OnRelayLayoutListener {
        void onClick(View view, int index);
    }

    @Override
    public RelayLayoutRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recyclerview_relaylayout, null);
        return new RelayLayoutRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(RelayLayoutRecyclerView holder, int position) {
        final VoRelayParameter relayParameter = voRelayParameters.get(position);
        switch (relayParameter.getRelayIcon()) {
            case "电子书":
                holder.item_button.setBackgroundResource(R.drawable.dzs);
                break;
            case "二维码墙":
                holder.item_button.setBackgroundResource(R.drawable.ewmq);
                break;
            case "广告机":
                holder.item_button.setBackgroundResource(R.drawable.ggj);
                break;
            case "互动橱窗":
                holder.item_button.setBackgroundResource(R.drawable.hdcc);
                break;
            case "互动五连屏":
                holder.item_button.setBackgroundResource(R.drawable.hdwlp);
                break;
            case "环形LED屏":
                holder.item_button.setBackgroundResource(R.drawable.hxledp);
                break;
            case "欢迎探索墙":
                holder.item_button.setBackgroundResource(R.drawable.hytsq);
                break;
            case "叫号机":
                holder.item_button.setBackgroundResource(R.drawable.jhj);
                break;
            case "金融超市":
                holder.item_button.setBackgroundResource(R.drawable.jrcs);
                break;
            case "全息贵金属":
                holder.item_button.setBackgroundResource(R.drawable.qxgjs);
                break;
            case "手机互动同屏":
                holder.item_button.setBackgroundResource(R.drawable.sjhdtp);
                break;
            case "微信打印机":
                holder.item_button.setBackgroundResource(R.drawable.wxdyj);
                break;
            case "营销互动屏":
                holder.item_button.setBackgroundResource(R.drawable.yxhdp);
                break;
            case "智能导览":
                holder.item_button.setBackgroundResource(R.drawable.zndl);
                break;
            case "自助借还书":
                holder.item_button.setBackgroundResource(R.drawable.zzjhs);
                break;
            default:
                holder.item_button.setBackgroundResource(R.drawable.common);
                break;

        }
        holder.item_text.setText(relayParameter.getRelayTitle());
        holder.item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRelayLayoutListener.onClick(view, relayParameter.getIndex());
            }
        });
    }

    @Override
    public int getItemCount() {
        return voRelayParameters == null ? 0 : voRelayParameters.size();
    }

    public class RelayLayoutRecyclerView extends RecyclerView.ViewHolder {
        public Button item_button;
        public TextView item_text;

        public RelayLayoutRecyclerView(View itemView) {
            super(itemView);
            item_button = (Button) itemView.findViewById(R.id.item_button);
            item_text = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

}
