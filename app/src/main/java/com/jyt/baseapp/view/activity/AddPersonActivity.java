package com.jyt.baseapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jyt.baseapp.R;
import com.jyt.baseapp.bean.MapBean;
import com.jyt.baseapp.model.MapModel;
import com.jyt.baseapp.util.BaseUtil;
import com.jyt.baseapp.view.widget.CircleImageView;
import com.jyt.baseapp.view.widget.JumpItem;
import com.jyt.baseapp.view.widget.MapSelector;
import com.jyt.baseapp.view.widget.MyDialog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

public class AddPersonActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.civ_pic)
    CircleImageView mCivPic;
    @BindView(R.id.rl_upload)
    RelativeLayout mRlUpload;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.jump_work)
    JumpItem mJumpWork;
    @BindView(R.id.jump_city)
    JumpItem mJumpCity;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    @BindView(R.id.rl_parent)
    RelativeLayout mRlParent;
    private File pictureFile;
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private int mtotalWidth;
    private CircleImageView mCiv;

    private MyDialog mDialog_city;
    private MyDialog mDialog_work;
    private MapModel mMapModel;
    private MapBean mMapBean;
    public StringBuilder Scity;//城市区域
    public String SProvince;//省
    public String SWork;//工种
    public String SName;//姓名
    public String SPhone;//联系方式
    private MapSelector mCitySelector;
    private TextView tv_hydropower;
    private TextView tv_carpentry;
    private TextView tv_civil;
    private TextView tv_welder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_person;
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTextTitle("添加机动人员");
        WindowManager wm = (WindowManager) BaseUtil.getContext().getSystemService(Context.WINDOW_SERVICE);
        mtotalWidth = wm.getDefaultDisplay().getWidth();
        mMapBean=new MapBean();
        mMapModel = new MapModel();
        Scity=new StringBuilder();
        initFile();
        initDialog();
        initData();
        initListener();
    }

    /**
     * 创建pic文件
     */
    private void initFile() {
        pictureFile = new File(getCacheDir(), "pic.jpg");
        if (!pictureFile.exists()) {
            try {
                pictureFile.getParentFile().mkdirs();
                pictureFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initDialog() {
        mDialog_city=new MyDialog(this,R.layout.pop_city);
        View view_city = mDialog_city.getView();
        mCitySelector = (MapSelector) view_city.findViewById(R.id.selector_city);
        mCitySelector.setHideDeleteIV(true);
        mCitySelector.getLayoutParams().width = (int) (mtotalWidth * 0.8);
        mCitySelector.requestLayout();
        mCitySelector.setOnMapClickListener(new MapSelector.OnMapClickListener() {
            @Override
            public void onClickProvince(int ProvinceID, final String ProvinceName) {
                mMapModel.getCityData(ProvinceID, new MapModel.onResultCityListener() {
                    @Override
                    public void ResultData(boolean isSuccess, Exception e, final ArrayList<MapBean.City> data) {
                        if (isSuccess) {
                            mMapBean.mCities = data;
                            mCitySelector.notifyData(mMapBean);
                            SProvince=ProvinceName;
                        }
                    }
                });
            }

            @Override
            public void onClickArea(int CityID, String CityName, int AreaID, String AreaName) {
                Scity.setLength(0);
                Scity.append(SProvince+CityName+AreaName);
                Log.e("@#", "=" + Scity.toString());
                mJumpCity.setNext(false,Scity.toString());
                mDialog_city.dismiss();
            }

            @Override
            public void onClickBack() {

            }
        });

        mDialog_work=new MyDialog(this,R.layout.pop_work);
        View view_work=mDialog_work.getView();
        LinearLayout ll= (LinearLayout) view_work.findViewById(R.id.ll_work);
        tv_hydropower = (TextView) view_work.findViewById(R.id.btn_hydropower);
        tv_carpentry = (TextView) view_work.findViewById(R.id.btn_carpentry);
        tv_civil = (TextView) view_work.findViewById(R.id.btn_civil);
        tv_welder = (TextView) view_work.findViewById(R.id.btn_welder);
        ll.getLayoutParams().width=(int) (mtotalWidth * 0.8);
        ll.requestLayout();
    }

    private void initData() {
        mMapModel.getProvinceData(new MapModel.onResultProvinceListener() {
            @Override
            public void ResultData(boolean isSuccess, Exception e, ArrayList<MapBean.Province> data) {
                if (isSuccess) {
                    mMapBean.mProvinces = data;
                    mMapBean.mProvinces.get(0).isCheckProvince=true;
                    SProvince= mMapBean.mProvinces.get(0).ProvinceName;
                    mCitySelector.setProvinceAdapter(mMapBean, AddPersonActivity.this);
                }
            }
        });
        mMapModel.getCityData(1, new MapModel.onResultCityListener() {
            @Override
            public void ResultData(boolean isSuccess, Exception e, final ArrayList<MapBean.City> data) {
                if (isSuccess) {
                    mMapBean.mCities = data;
                    mCitySelector.setCityAdapter(mMapBean, AddPersonActivity.this);
                }
            }
        });
    }

    private void initListener(){
        mJumpCity.setOnClickListener(this);
        mJumpWork.setOnClickListener(this);
        tv_hydropower.setOnClickListener(this);
        tv_carpentry.setOnClickListener(this);
        tv_civil.setOnClickListener(this);
        tv_welder.setOnClickListener(this);
    }


    /**
     * 生产PopupWindow
     *
     * @param popView
     * @return
     */
    private PopupWindow CreatePopWindow(View popView) {
        final PopupWindow popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
//        popupWindow.setAnimationStyle(R.style.popAnim);
        popupWindow.getContentView().setFocusableInTouchMode(false);
        popupWindow.getContentView().setFocusable(true);
        popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e("@#",keyCode+"");
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
        return popupWindow;
    }


    /**
     * 按下头像的操作
     */
    public void gallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /**
     * 从相册返回图片的相关处理
     */
    private void Logigallery(Intent data) {
        if (data != null) {
            Uri uri = data.getData();
            crop(uri);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_GALLERY:
                Logigallery(data);
                break;
            case PHOTO_REQUEST_CUT:
                if (data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    savePicture(bitmap, pictureFile);
                }
                break;
        }
    }


    /*
    * 剪切图片
    */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    //保存图片到本地,以便今后显示头像
    public void savePicture(Bitmap bitmap, File picture) {
        if (picture.exists()) {
            //删除上一张头像
            picture.delete();
        }
        BufferedOutputStream ops = null;
        try {
            ops = new BufferedOutputStream(new FileOutputStream(picture));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ops);
            Glide.with(AddPersonActivity.this)
                    .load(picture)
                    .into(mCiv);
            ops.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ops != null) {
                try {
                    ops.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jump_city:
                if (mDialog_city.isShowing()){
                    mDialog_city.dismiss();
                }else {
                    mDialog_city.show();
                }
                break;
            case R.id.jump_work:
                if (mDialog_work.isShowing()){
                    mDialog_work.dismiss();
                }else {
                    mDialog_work.show();
                }
                break;
            case R.id.btn_hydropower:
                setPopWork(0);
                break;
            case R.id.btn_carpentry:
                setPopWork(1);
                break;
            case R.id.btn_civil:
                setPopWork(2);
                break;
            case R.id.btn_welder:
                setPopWork(3);
                break;

            default:
                break;
        }
    }

    public void setPopWork(int index){
        tv_hydropower.setTextColor(getResources().getColor(R.color.text_color1));
        tv_carpentry.setTextColor(getResources().getColor(R.color.text_color1));
        tv_civil.setTextColor(getResources().getColor(R.color.text_color1));
        tv_welder.setTextColor(getResources().getColor(R.color.text_color1));
        switch (index) {
            case 0:
                tv_hydropower.setTextColor(getResources().getColor(R.color.map_text2));
                SWork= tv_hydropower.getText().toString();
                break;
            case 1:
                tv_carpentry.setTextColor(getResources().getColor(R.color.map_text2));
                SWork= tv_carpentry.getText().toString();
                break;
            case 2:
                tv_civil.setTextColor(getResources().getColor(R.color.map_text2));
                SWork= tv_civil.getText().toString();
                break;
            case 3:
                tv_welder.setTextColor(getResources().getColor(R.color.map_text2));
                SWork= tv_welder.getText().toString();
                break;
            default:
                break;
        }
        mJumpWork.setNext(false,SWork);
        mDialog_work.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pictureFile.delete();
    }


}