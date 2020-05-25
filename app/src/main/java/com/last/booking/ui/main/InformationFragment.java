package com.last.booking.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.last.booking.R;
import com.last.booking.data.Userdata;
import com.last.booking.ui.newInformation.NewInformationActivity;
import com.last.booking.ui.rebind.RebindActivity;
import com.last.booking.uitl.FileUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class InformationFragment extends Fragment {

    private static final int CHOOSE_PICTURE = 0;
    private static final int TAKE_PICTURE = 1;

    private Uri imgUri;

    private MainViewModel mainViewModel;

    private Context context;

    private SimpleDraweeView sdv_bg;
    private SimpleDraweeView sdv_icon;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information,null);
        mainViewModel = ViewModelProviders.of(this,new MainViewModelFactory()).get(MainViewModel.class);
        context = view.getContext();

        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());

       sdv_icon = view.findViewById(R.id.information_icon);
       sdv_bg = view.findViewById(R.id.information_bg);

        RoundingParams circle = RoundingParams.asCircle();
        GenericDraweeHierarchy hierarchy = builder.setRoundingParams(circle).build();
        sdv_icon.setHierarchy(hierarchy);

        setIcon(sdv_icon, sdv_bg);


        TextView tv_name = view.findViewById(R.id.information_name);
        tv_name.setText(Userdata.getInstance().getUserInfo().getUserName());

        Button btn_upload = view.findViewById(R.id.information_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePictureDialog();
            }
        });


        mainViewModel.getUploadResult().observe(this, new Observer<UploadResult>() {
            @Override
            public void onChanged(@Nullable UploadResult uploadResult) {
                if(uploadResult == null)
                    return;

                if(uploadResult.getError() != null)
                {
                    Toast.makeText(context,uploadResult.getError(),Toast.LENGTH_SHORT).show();
                }

                if(uploadResult.isValid())
                {
                    Toast.makeText(context,"上传成功",Toast.LENGTH_SHORT).show();
                    mainViewModel.getUserInfo();
                }
            }
        });


        mainViewModel.getUserInfoResult().observe(this, new Observer<UserInfoResult>() {
            @Override
            public void onChanged(@Nullable UserInfoResult userInfoResult) {
                if(userInfoResult == null)
                    return;

                if(userInfoResult.getError() != null)
                {
                    Toast.makeText(context,userInfoResult.getError(),Toast.LENGTH_SHORT).show();
                }

                if(userInfoResult.getUserInfo() != null)
                {
                    setIcon(sdv_icon,sdv_bg);
                }
            }
        });

        Button btn_change = view.findViewById(R.id.information_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewInformationActivity.class);
                startActivity(intent);
            }
        });

        Button btn_rebind = view.findViewById(R.id.information_rebind);
        btn_rebind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RebindActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setIcon(SimpleDraweeView sdv_icon, SimpleDraweeView sdv_bg) {
        if(Userdata.getInstance().getUserInfo().getUserIcon() != null)
        {
            sdv_icon.setImageURI(Uri.parse(Userdata.getInstance().getUserInfo().getUserIcon()));
            blur(sdv_bg);
        }
    }

    private void blur(SimpleDraweeView sdv_bg) {
        try {
            Uri uri = Uri.parse((Userdata.getInstance().getUserInfo().getUserIcon()));
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(5, 10))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(sdv_bg.getController())
                    .setImageRequest(request)
                    .build();
            sdv_bg.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setIcon(SimpleDraweeView sdv_icon, SimpleDraweeView sdv_bg,Uri u) {
        if(u != null)
        {
            sdv_icon.setImageURI(Uri.parse(Userdata.getInstance().getUserInfo().getUserIcon()));
            blur(sdv_bg);
        }
    }

    private void blur(SimpleDraweeView sdv_bg,Uri u) {
        try {
            Uri uri = u;
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(5, 10))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(sdv_bg.getController())
                    .setImageRequest(request)
                    .build();
            sdv_bg.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void showChoosePictureDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片","拍照"};
        builder.setNegativeButton("取消",null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which)
                {
                    case CHOOSE_PICTURE:
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent,CHOOSE_PICTURE);
                        break;

                    case TAKE_PICTURE:
                        //调用系统相机
                        Intent intentCamera = new Intent();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            intentCamera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
                        }
                        intentCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        //将拍照结果保存至photo_file的Uri中，不保留在相册中
                        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                        startActivityForResult(intentCamera, TAKE_PICTURE);
                        break;
                }
            }
        });

        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case TAKE_PICTURE:
                    Bundle bundle = data.getExtras();
                    File file = saveBitmapFile((Bitmap)bundle.get("data"));
                    mainViewModel.upload(Userdata.getInstance().getUserInfo().getUserId(),file);
                    break;
                case CHOOSE_PICTURE:
                    if(data.getData() != null)
                    {
                        File file1 = new File(FileUtil.getFilePathByUri(getContext(),data.getData()));
                        setIcon(sdv_icon,sdv_bg,data.getData());
                        mainViewModel.upload(Userdata.getInstance().getUserInfo().getUserId(),file1);
                    }
                    break;
            }
        }
    }

    public  File saveBitmapFile(Bitmap bitmap){
        File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/_image.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


}
