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
import com.mobile.mobilewallet.MorePayment;
import com.mobile.mobilewallet.Objects.TransactionHistory;
import com.mobile.mobilewallet.R;
import com.mobile.mobilewallet.Reciept;


import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

     private static List<TransactionHistory> itemList;
        private static Context context;
        public TransactionAdapter(List<TransactionHistory> itemList, Context context) {
            TransactionAdapter.itemList = itemList;
            TransactionAdapter.context = context;
            if (itemList.isEmpty()) {
                TransactionHistory history = new TransactionHistory();
                history.setName("None");
                Bitmap bit = drawableToBitmap(ContextCompat.getDrawable(context, R.drawable.default_avatar));
                history.setAvatarByte(bitmapToByteArray(bit));
                history.setAmount("₦0.00");
                itemList.add(history);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_adapter_layout, parent, false);
            LinearLayout layout = itemView.findViewById(R.id.transCont);
            ((ViewGroup)layout.getParent()).removeView(layout);
            return new MyViewHolder(layout);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind(itemList.get(position));
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView transactionMade;
            private  TextView date;
            ImageView avatar;
            TextView amountMade;
            LinearLayout transactionCont;
            public MyViewHolder(View view) {
                super(view);
                transactionMade = view.findViewById(R.id.tranName);
                date = view.findViewById(R.id.tranDate);
                amountMade = view.findViewById(R.id.transAmount);
                transactionCont = view.findViewById(R.id.transactionCont);
                avatar = view.findViewById(R.id.transAvatar);
            }

            public void bind(TransactionHistory transactionHistory) {
                if (transactionHistory.getAvatarByte()!=null && transactionHistory.getAvatarByte().length !=0) {
//                    Bitmap bitmap = BitmapFactory.decodeByteArray(transactionHistory.getAvatarByte(), 0, transactionHistory.getAvatarByte().length);
//                    avatar.setImageBitmap(bitmap);
                    transactionMade.setText(transactionHistory.getTranType());
                    if (transactionHistory.getDateTime() != null) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yy");
                            TemporalAccessor ldt = df.parse(transactionHistory.getDateTime());
                            LocalDateTime localDate = LocalDateTime.from(ldt);
                            if (localDate.equals(LocalDateTime.now().minusDays(1))) {
                                date.setText(String.format("Yesterday %d:%d", localDate.getHour(), localDate.getMinute()));
                            } else if (localDate.equals(LocalDateTime.now())) {
                                date.setText(String.format("Today %d:%d", localDate.getHour(), localDate.getMinute()));
                            } else {
                                date.setText(localDate.getDayOfMonth() + "/" + localDate.getMonth() + "/" + localDate.getYear());
                            }
                            amountMade.setText(transactionHistory.getAmount());
                        }
                    }else {
                        date.setText("none");
                    }
                }

                transactionMade.setText(transactionHistory.getName());
                transactionCont.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent;
                        if (transactionHistory.getName().equalsIgnoreCase("none")) {
                            intent = new Intent(context, MorePayment.class);
                        } else {
                            intent = new Intent(context, Reciept.class);
                            Gson gson = new Gson();
                            intent.putExtra("jsonHistory", gson.toJson(transactionHistory));
                            intent.putExtra("actionMade", transactionHistory.getTranType());
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