package lib.foundation.uikit.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * 价格展示
 * ￥20.00 ->
 * ￥20.oo
 */
public class PriceTextView extends AppCompatTextView {

    public PriceTextView(Context context) {
        super(context);
        init();
    }

    public PriceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PriceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }

    public void setPrice(String price){

        if(TextUtils.isEmpty(price))
            return;

        if(price.contains(".")){
            int lastIndexOfDot = price.lastIndexOf(".");
            SpannableString spannableString = new SpannableString(price);
            RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.7f);
            spannableString.setSpan(sizeSpan, lastIndexOfDot, price.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            setText(spannableString);
        }else{
            setText(price);
        }
    }

}
