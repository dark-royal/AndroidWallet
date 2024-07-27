package com.mobile.mobilewallet.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.mobile.mobilewallet.Objects.TransactionHistory;
import com.mobile.mobilewallet.R;
import com.mobile.mobilewallet.Reciept;
import com.mobile.mobilewallet.Transfer;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.MyViewHolder> {
    private static List<TransactionHistory> itemList;
    private static Context context;
    public TransferAdapter(List<TransactionHistory> itemList, Context context) {
        TransferAdapter.itemList = itemList;
        TransferAdapter.context = context;
        if (itemList.isEmpty()) {
            TransactionHistory history = new TransactionHistory();
            history.setName("Add");
            Bitmap bit = drawableToBitmap(ContextCompat.getDrawable(context, R.drawable.combined_shape));
            history.setAvatarByte(bitmapToByteArray(bit));
            itemList.add(history);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_adapter_layout, parent, false);
        LinearLayout layout = itemView.findViewById(R.id.avatarCont);
        ((ViewGroup)layout.getParent()).removeView(layout);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TransactionHistory item = itemList.get(position);
        holder.bind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView avatarName;
        private ImageView avatar;
        public MyViewHolder(View view) {
            super(view);
            avatar = view.findViewById(R.id.myAccount);
            avatarName = view.findViewById(R.id.avatarName);

        }

        public void bind(TransactionHistory transactionHistory) {
            if (transactionHistory.getAvatarByte()!=null && transactionHistory.getAvatarByte().length !=0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(transactionHistory.getAvatarByte(), 0, transactionHistory.getAvatarByte().length);
//                BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
                avatar.setImageBitmap(bitmap);
            }else {
                avatar.setImageDrawable(
                        ContextCompat.getDrawable(context, R.drawable.default_avatar));
            }
//            if (itemList.isEmpty()){
//                avatar.setCompoundDrawablesWithIntrinsicBounds(
//                        ContextCompat.getDrawable(context, R.drawable.combined_shape),
//                        null,null, null);
//                avatar.setText("Add");
//            }

            avatarName.setText(transactionHistory.getName());
            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    if (avatarName.getText().toString().equalsIgnoreCase("add")){
                        intent  = new Intent(context, Transfer.class);
                    }else {
                        intent  = new Intent(context, Reciept.class);
                        Gson gson = new Gson();
                        intent.putExtra("jsonHistory", gson.toJson(transactionHistory));
                    }
                    context.startActivity(intent);
                }
            });
        }




    }
    public byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    public Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}