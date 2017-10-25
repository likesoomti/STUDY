package com.onthecoffee.hanplace.UTIL;

import android.app.Dialog;
        import android.content.Context;
        import android.os.Bundle;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.TextView;

import com.onthecoffee.hanplace.R;

public class FacDialogue extends Dialog {

    private TextView mTitleView;
    private TextView mDetailView;
    private Button mOkButton;

    private String mTitle;
    private String mContent;

    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_map2);

        mTitleView = (TextView) findViewById(R.id.fac_title);
       // mDetailView = (TextView) findViewById(R.id.fac_detail);
        mOkButton = (Button) findViewById(R.id.fac_dialogue_btn);

        // 제목과 내용을 생성자에서 셋팅한다.
        mTitleView.setText(mTitle);
        mDetailView.setText(mContent);

        // 클릭 이벤트 셋팅
        if (mLeftClickListener != null && mRightClickListener != null) {
            mOkButton.setOnClickListener(mLeftClickListener);

        } else if (mLeftClickListener != null
                && mRightClickListener == null) {

        } else {

        }
    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public FacDialogue(Context context, String title,String detail,
                        View.OnClickListener singleListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mLeftClickListener = singleListener;
    }


}
