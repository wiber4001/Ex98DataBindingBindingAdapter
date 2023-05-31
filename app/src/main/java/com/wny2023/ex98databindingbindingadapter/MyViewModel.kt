package com.wny2023.ex98databindingbindingadapter

import androidx.databinding.ObservableField
import java.util.Date


class MyViewModel {

    //1) 이미지의 URL변수 [ 관찰 가능한 타입 -- ObservableXXX ]
    var img: ObservableField<String> = ObservableField("https://images.unsplash.com/photo-1685371863474-90594391bc95?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80")
    //1.1) 버튼 클릭시 이벤트 콜백에서 호출하는 메소드
    fun changeImage(){
        img.set("https://i0.wp.com/www.printmag.com/wp-content/uploads/2021/02/4cbe8d_f1ed2800a49649848102c68fc5a66e53mv2.gif?resize=476%2C280&ssl=1")
    }
    //2) 리사이클러뷰가 보여줄 대량의 데이터 컬렉션
    val items:ObservableField<MutableList<Item>> = ObservableField(mutableListOf(Item("sam","Hello"),
        Item("hong","hoghog")))//2개의 아이템을 가진 리스트 객체로 초기화
    //2.1) 버튼 클릭이벤트 콜백에서 호출하는 메소드
    fun addItem(){
        //원래는 서버에서 새로운 데이터를 읽어오는 코드...
        //테스트 목적으로 그냥 Item추가
//        val list=items.get()
//        list?.add(0,Item("new",Date().toString()))
//        items.set(list) //같은 객체(리스트를 다시 설정하면 화면갱신 안됨
        val list: MutableList<Item> = mutableListOf()
        list.add(Item("new", Date().toString()))
        list.addAll(items.get()!!)
        items.set(list)

    }
}

// ###############################
// ObservableXXX 는 몇가지 단점이 있음.
// 1. 새로 set 하는 객체가 변경되지 않으면 화면갱신이 안됨.
// 2. 액티비티나 프래그먼트의 라이프사이클을 고려하지 않고 데이터 변경에 반응함.. 화면이 종료되는 상황에서도 화면갱신을 시도함..
// 이런 단점을 개선하기 위해 Jetpack 라이브러리로 배포된 LiveData 라는 녀석이 등장함.