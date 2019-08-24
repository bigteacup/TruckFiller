package com.example.truckfiller;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    public int nombre80 = 0 ;

    public DrawView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //rang a
        for(int i =0 ; i < nombre80; i++){
            paint.setColor(Color.BLACK);
            paint2.setColor(Color.WHITE);
            paint.setStrokeWidth(0);
            canvas.drawRect(0 , (0 + i *120)/2, (80)/2,  (120 + 120 * i)/2, paint);
            paint.setColor(Color.WHITE);
            canvas.drawRect(0 , (0 +i *120)/2, (80)/2,  (3 + 120 * i)/2, paint);
            canvas.drawRect(0, 0 , 3/2,  ( 120)/2, paint);
        }

        //rang b
        for(int i =0 ; i < nombre80; i++){
            paint.setColor(Color.BLACK);
            paint2.setColor(Color.WHITE);
            paint.setStrokeWidth(0);
            canvas.drawRect(80/2 , (0 + i *120)/2, (160)/2,  (120 + 120 * i)/2, paint);
            paint.setColor(Color.WHITE);
            canvas.drawRect(80/2 , (0 +i *120)/2, (160)/2,  (3 + 120 * i)/2, paint);
            canvas.drawRect(80/2, 0 , 83/2,  ( 120 * (i+1))/2, paint);
        }

        //rang c
        for(int i =0 ; i < nombre80; i++){
            paint.setColor(Color.BLACK);
            paint2.setColor(Color.WHITE);
            paint.setStrokeWidth(0);
            canvas.drawRect(160/2, (0 + i *120)/2, (240)/2,  (120 + 120 * i)/2, paint);
            paint.setColor(Color.WHITE);
            canvas.drawRect(160/2 , (0 +i *120)/2, 240/2,  (3 + 120 * i)/2, paint);
            canvas.drawRect(160/2, 0 , 163/2,  ( 120 * (i+1))/2, paint);
        }
        //  paint.setStrokeWidth(0);
     //   paint.setColor(Color.CYAN);
     //   canvas.drawRect(33, 60, 77, 77, paint );
     //   paint.setColor(Color.YELLOW);
     //   canvas.drawRect(33, 33, 77, 60, paint );
   //     Bitmap result = Bitmap.createBitmap(25, 25, Bitmap.Config.ARGB_8888);

    }
public void testim(){


}
}