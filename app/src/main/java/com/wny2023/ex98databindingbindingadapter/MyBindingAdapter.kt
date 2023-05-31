package com.wny2023.ex98databindingbindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//기존 뷰들에는 없는 새로은 xml속성을 연결하는 기증 메소드를 가지는 객체
//[보통 static 메소드를 가진 class로 사용]

object MyBindingAdapter { //같은 이름으로 객체가 더이상 생기지 않게 하도록 object키워드를 사용
    //object키워드 - 싱글톤 패턴으로 객체를 만들어주는 키워드

    //1)이미지뷰에 새로운 xml속성을 만들기 -[속성명: imageUrl]
    //kotlin annotation전용 해독기를 module's build.gradle에 넣어야함
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view:ImageView, url:String){ //메소드명은 임의, 파라미터가 중요함[뷰타입, 속성값]
        Glide.with(view.context).load(url).into(view)
    }

    //2)리사이클러뷰에 리스트를 설정할 수 있는 새로운 xml속성을 만들기 -[속성명: itemList]
    //제약사항: 컬렉션 타입은 사용 불가능-> Any로 줘야함
    @BindingAdapter("itemList")
    @JvmStatic
    fun setItemList(view:RecyclerView, items:Any){
        view.adapter=RecyclerItemAdapter(view.context, items as List<Item>)
    }
}