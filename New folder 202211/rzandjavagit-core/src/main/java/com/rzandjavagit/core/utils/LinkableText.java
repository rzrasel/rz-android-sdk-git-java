package com.rzandjavagit.core.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LinkableText {
    //ClickableTextView
    private OnClickListener onClickListener;
    private TextView textView;
    private List<String> linkedTextList;
    private SpannableString spannableString = null;
    private int textColor, pressedColor;
    //private ClickableSpan clickableSpan = null;
    private List<ClickableSpanProperty> clickableSpanList = null;
    private int counter = 0;

    public LinkableText() {
        linkedTextList = new ArrayList<>();
        clickableSpanList = new ArrayList<>();
        linkedTextList.clear();
        clickableSpanList.clear();
        textColor = Color.parseColor("#666666");
        pressedColor = Color.parseColor("#b22222");
    }

    public LinkableText setTextColor(String argColorHexCode) {
        try {
            textColor = Color.parseColor(argColorHexCode);
        } catch (IllegalArgumentException e) {
            textColor = Color.parseColor("#666666");
        }
        return this;
    }

    public LinkableText setTappedColor(String argColorHexCode) {
        try {
            pressedColor = Color.parseColor(argColorHexCode);
        } catch (IllegalArgumentException e) {
            pressedColor = Color.parseColor("#b22222");
        }
        return this;
    }

    public LinkableText setTextView(TextView argTextView) {
        textView = argTextView;
        //textView.setSelected(false);
        textView.setLongClickable(false);
        textView.setTextIsSelectable(false);
        return this;
    }

    public LinkableText setLinkedText(String argLinkText) {
        linkedTextList.add(argLinkText);
        return this;
    }

    public LinkableText setLinkedText(List<String> argLinkText) {
        linkedTextList.addAll(argLinkText);
        return this;
    }

    public LinkableText setOnClickListener(OnClickListener argOnClickListener) {
        onClickListener = argOnClickListener;
        return this;
    }

    public void build() {
        if (textView == null || linkedTextList.size() <= 0) {
            return;
        }
        String fullContentText = textView.getText() + "";
        if (isNullOrEmpty(fullContentText)) {
            return;
        }
        int itemPosition = 0;
        for (String item : linkedTextList) {
            //System.out.println("LINKED_ITEM: " + item);
            itemPosition++;
            onBuildClickableSpan(fullContentText, item, itemPosition);
        }
            /*if (clickableSpanList.size() > 0) {
                spannableString = new SpannableString(fullContentText);
                for (ClickableSpanProperty item : clickableSpanList) {
                    //spannableString.setSpan(new BackgroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)), item.getStart(), item.getEnd(), 0);
                    //spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)), item.getStart(), item.getEnd(), 0);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)), item.getStart(), item.getEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(new RelativeSizeSpan(1.2f), item.getStart(), item.getEnd(), 0);
                    spannableString.setSpan(item.getClickableSpan(), item.getStart(), item.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //spannableString.setSpan(clickableSpan, startIndex, endIndex, 0);
                }
                textView.setText(spannableString);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }*/
        if (clickableSpanList.size() > 0) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(fullContentText);
            for (ClickableSpanProperty item : clickableSpanList) {
                //spannableString.setSpan(new BackgroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)), item.getStart(), item.getEnd(), 0);
                //spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)), item.getStart(), item.getEnd(), 0);
                //spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)), item.getStart(), item.getEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //spannableStringBuilder.setSpan(new RelativeSizeSpan(1.2f), item.getStart(), item.getEnd(), 0);
                spannableStringBuilder.setSpan(item.getClickableSpan(), item.getStart(), item.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                //spannableString.setSpan(clickableSpan, startIndex, endIndex, 0);
            }
            textView.setText(spannableStringBuilder);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setHighlightColor(Color.TRANSPARENT);
        }
    }

    private void onBuildClickableSpan(final String argFullContentText, String argLinkedText, final int argItemPosition) {
        final int startIndex = argFullContentText.indexOf(argLinkedText);
        final int endIndex = startIndex + argLinkedText.length();
        //counter++;
        if (startIndex < 0) {
            return;
        }
            /*System.out.println("LINKED_ITEM: " + argLinkedText + " INDEX: " + startIndex + " LAST: " + endIndex);
            if (spannableString == null) {
                spannableString = new SpannableString(argFullContentText);
            }*/
        //counter++;
        final ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View argView) {
                //startActivity(new Intent(MyActivity.this, NextActivity.class));
                TextView spanTextView = (TextView) argView;
                // TODO add check if tv.getText() instanceof Spanned
                Spanned spanned = (Spanned) spanTextView.getText();
                int start = spanned.getSpanStart(this);
                int end = spanned.getSpanEnd(this);
                String spanText = argFullContentText.substring(start, end);
                argView.setTag(spanText);
                    /*System.out.println("asldfkjskdfjasdf START: " + start + " END: " + end);
                    System.out.println("LINKED_TEXT: " + argFullContentText.substring(start, end));
                    System.out.println("TAG_TEXT: " + argView.getTag());*/
                if (onClickListener != null) {
                    onClickListener.onClick(argView, spanText, argItemPosition);
                }
                    /*CharSequence charSequence = tv.getText();
                    if (charSequence instanceof Spannable) {
                        Spannable spannableText = (Spannable) charSequence;
                        ClickableSpan[] spans = spannableText.getSpans(0, tv.length(), ClickableSpan.class);
                        for (ClickableSpan span : spans) {
                            int start = spannableText.getSpanStart(span);
                            int end = spannableText.getSpanEnd(span);
                            System.out.println("asldfkjskdfjasdf " + charSequence.subSequence(start, end));
                        }
                    }*/
                //argView.invalidate();
            }

            @Override
            public void updateDrawState(TextPaint argTextPaint) {
                super.updateDrawState(argTextPaint);
                //argTextPaint.setColor(Color.BLUE);
                //argTextPaint.setColor(Color.parseColor("#666666"));
                argTextPaint.setUnderlineText(true);
                //argTextPaint.setColor(Color.BLUE);
                if (textView.isPressed()) {
                    //argTextPaint.setColor(Color.BLUE);
                    argTextPaint.setColor(pressedColor);
                } else {
                    //argTextPaint.setColor(Color.RED);
                    argTextPaint.setColor(textColor);
                }
                textView.invalidate();
            }
        };
        clickableSpanList.add(new ClickableSpanProperty(clickableSpan, startIndex, endIndex));
            /*spannableString.setSpan(new BackgroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)), startIndex, endIndex, 0);
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)), startIndex, endIndex, 0);
            spannableString.setSpan(new RelativeSizeSpan(1.2f), startIndex, endIndex, 0);
            spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //spannableString.setSpan(clickableSpan, startIndex, endIndex, 0);
            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setHighlightColor(Color.TRANSPARENT);*/
    }

    public class ClickableSpanProperty {
        private ClickableSpan clickableSpan;
        private int start;
        private int end;

        public ClickableSpanProperty(ClickableSpan argClickableSpan, int argStart, int argEnd) {
            clickableSpan = argClickableSpan;
            start = argStart;
            end = argEnd;
        }

        public ClickableSpan getClickableSpan() {
            return clickableSpan;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public ClickableSpanProperty onGetSet(ClickableSpan argClickableSpan, int argStart, int argEnd) {
            return new ClickableSpanProperty(argClickableSpan, argStart, argEnd);
        }
    }

    public interface OnClickListener {
        public void onClick(View argView, String argItemValue, int argItemPosition);
    }

    public static boolean isNullOrEmpty(String argValue) {
        if (argValue == null) {
            return true;
        }
        if (argValue.trim().isEmpty()) {
            return true;
        }
        if (argValue.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }
}
/*sysTvRegistration.setText("Android is a Software stack");
List<String> linkedTextList = new ArrayList<>();
linkedTextList.add("Sof");
linkedTextList.add("stack");
LinkableText linkableText = new LinkableText()
        .setTextView(sysTvRegistration)
        .setLinkedText("And")
        .setLinkedText(linkedTextList);
linkableText.build();*/
//https://stackoverflow.com/questions/20856105/change-the-text-color-of-a-single-clickablespan-when-pressed-without-affecting-o
//https://android--examples.blogspot.com/2016/08/android-clickablespan-example.html
//https://medium.com/@jerryhanksokafor/string-manipulation-using-spannablestring-regular-expression-and-custom-textview-part1-24e4bd3eda92
//https://stackoverflow.com/questions/19750458/android-clickablespan-get-text-onclick
//https://stackoverflow.com/questions/24792353/add-tag-to-clickablespan
//https://gist.github.com/parthdave93/e9be2a0b20fcb91a52b7cd5fb5855f8f
//https://medium.com/androiddevelopers/spantastic-text-styling-with-spans-17b0c16b4568
//https://en.proft.me/2017/07/22/decorating-textview-spannablestring-android/
/*
String mainText = null;
String linkedText = null;
mainText = sysTvRegistration.getText() + "";
mainText = "Android is a Software stack";
linkedText = "stack";
int startIndex = mainText.indexOf(linkedText);
int endIndex = startIndex + linkedText.length();
System.out.println("INDEX: " + startIndex + " LAST: " + endIndex);
SpannableString spannableString = new SpannableString(mainText);
ClickableSpan clickableSpan = new ClickableSpan() {
    @Override
    public void onClick(View argView) {
        //startActivity(new Intent(MyActivity.this, NextActivity.class));
        System.out.println("asldfkjskdfjasdf");
        argView.invalidate();
    }

    @Override
    public void updateDrawState(TextPaint argTextPaint) {
        super.updateDrawState(argTextPaint);
        argTextPaint.setUnderlineText(false);
        argTextPaint.setColor(Color.BLUE);
        /==*if (textView.isPressed()) {
            textPaint.setColor(Color.BLUE);
        } else {
            textPaint.setColor(Color.RED);
        }*==/
    }
};
spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
sysTvRegistration.setText(spannableString);
sysTvRegistration.setMovementMethod(LinkMovementMethod.getInstance());
sysTvRegistration.setHighlightColor(Color.TRANSPARENT);
*/