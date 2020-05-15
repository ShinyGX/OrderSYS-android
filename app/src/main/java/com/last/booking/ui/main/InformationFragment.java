package com.last.booking.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class InformationFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information,null);

        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());

        SimpleDraweeView sdv_icon = view.findViewById(R.id.information_icon);
        SimpleDraweeView sdv_bg = view.findViewById(R.id.information_bg);

        RoundingParams circle = RoundingParams.asCircle();
        GenericDraweeHierarchy hierarchy = builder.setRoundingParams(circle).build();
        sdv_icon.setHierarchy(hierarchy);

        if(Userdata.getInstance().getUserInfo().getUserIcon() != null) {
            Uri uri = Uri.parse(Userdata.getInstance().getUserInfo().getUserIcon());
            sdv_icon.setImageURI(uri);


            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(10,200))
                    .build();

            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(sdv_bg.getController())
                    .setImageRequest(request)
                    .build();

            sdv_bg.setController(controller);
        }



        TextView tv_name = view.findViewById(R.id.information_name);
        tv_name.setText(Userdata.getInstance().getUserInfo().getUserName());


        return view;
    }
}
