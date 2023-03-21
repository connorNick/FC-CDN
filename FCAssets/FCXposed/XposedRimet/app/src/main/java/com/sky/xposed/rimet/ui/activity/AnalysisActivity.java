/*
 * Copyright (c) 2020 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.xposed.rimet.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.sky.xposed.common.util.Alog;
import com.sky.xposed.common.util.ToastUtil;
import com.sky.xposed.rimet.R;
import com.sky.xposed.rimet.XConstant;
import com.sky.xposed.rimet.task.AnalysisTask;
import com.sky.xposed.rimet.util.ExecutorUtil;
import com.sky.xposed.ui.dialog.LoadingDialog;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by sky on 2020-03-23.
 */
public class AnalysisActivity extends Activity {

    private LoadingDialog mLoadingDialog;
    private TextView mTvOutInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_analysis);

//        ActionBar actionBar = getActionBar();
//        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        mTvOutInfo = findViewById(R.id.tv_out_info);
        mTvOutInfo.setMovementMethod(ScrollingMovementMethod.getInstance());

        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.setTip("分析中...");

        AnalysisTask task = new AnalysisTask(getApplicationContext());
        task.setOnPreCallback(() -> mLoadingDialog.show());
        task.setOnProgressCallback(text -> {
            mTvOutInfo.append(text);
            int offset = mTvOutInfo.getLineCount() * mTvOutInfo.getLineHeight();
            if (offset > mTvOutInfo.getHeight()) {
                mTvOutInfo.scrollTo(0, offset - mTvOutInfo.getHeight());
            }
        });
        task.setCompleteCallback(this::onAnalysisResult);
        task.setThrowableCallback(tr -> onAnalysisError("分析信息失败!"));
        String packageName = getIntent().getStringExtra(XConstant.Key.PACKAGE_NAME);
        Alog.d(">>>>>>>>>>packageName:", packageName);
        task.executeOnExecutor(ExecutorUtil.getBackExecutor(), packageName);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_analysis_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int itemId = item.getItemId();
//
//        if (android.R.id.home == itemId) {
//            // 退出
//            onBackPressed();
//            return true;
//        } else if (R.id.menu_ok == itemId) {
//            // 确定
//            returnChooseResult();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void onAnalysisResult(Map<Integer, String> result) {
        mLoadingDialog.dismiss();

        Intent data = new Intent();
        data.putExtra(XConstant.Key.DATA, (Serializable) result);

        setResult(Activity.RESULT_OK, data);
        finish();
    }

    private void onAnalysisError(String msg) {
        ToastUtil.show(msg);
        onAnalysisResult(null);
    }
}
