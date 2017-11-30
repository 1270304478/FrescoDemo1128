package ch.bwei.com.frescodemo1128.adpter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import ch.bwei.com.frescodemo1128.R;
import ch.bwei.com.frescodemo1128.bean.ImageBean;

/**
 * Created by Menglucywhh on 2017/11/28.
 */
public class ListAdapter extends BaseAdapter{
    Context context;
    List<ImageBean.DataBean> listImage;
    public ListAdapter(Context context, List<ImageBean.DataBean> listImage) {
        this.listImage = listImage;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listImage.size();
    }

    @Override
    public Object getItem(int i) {
        return listImage.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view = View.inflate(context, R.layout.item_liat,null);

            holder = new ViewHolder();

            holder.imageView = view.findViewById(R.id.list_image);
            holder.textView01 = view.findViewById(R.id.list_text);
            holder.textView02 = view.findViewById(R.id.list_time);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        String[] split = listImage.get(i).getImages().split("\\|");

        Uri uri= Uri.parse(split[0]);
      // GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder();
      //  RoundingParams params = RoundingParams.asCircle();

        holder.imageView.setImageURI(uri);
        holder.textView01.setText(listImage.get(i).getTitle());
        holder.textView02.setText(listImage.get(i).getCreatetime());
        return view;
    }

    class ViewHolder{
        SimpleDraweeView imageView;
        TextView textView01;
        TextView textView02;
    }
}
