package com.test.nesting;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;
import com.test.nesting.adapter.InfoAdapter;
import com.test.nesting.bean.InfoBean;
import com.test.view.BaseAdapter;
import com.test.view.PullBaseView;
import com.test.view.PullRecyclerView;
import com.test.zhyrecycleview.R;
import java.util.ArrayList;
import java.util.List;

/*
* 嵌套
*/
public class NestingActivity extends AppCompatActivity implements BaseAdapter.OnItemClickListener, BaseAdapter.OnItemLongClickListener,
        BaseAdapter.OnViewClickListener, PullBaseView.OnRefreshListener{

    PullRecyclerView recyclerView;
    List<Object> mDatas;
    InfoAdapter infoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nesting);
        initData();
        initRecyclerView();
    }

    protected void initData() {

        mDatas = new ArrayList<>();
        longLongLongData();

//        for (int i = 1; i < 30; i++) {
//            InfoBean info = new InfoBean();
//            info.setText("TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT" + i);
//            info.setImgList(imageList01);
//            mDatas.add(info);
//        }
    }


    private void initRecyclerView() {
        recyclerView = (PullRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        infoAdapter = new InfoAdapter(this, mDatas, this);
        infoAdapter.setOnItemClickListener(this);
        infoAdapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(infoAdapter);
    }

    /**
     * 子View点击事件
     *
     * @param position item position
     * @param viewtype 点击的view的类型，调用时根据不同的view传入不同的值加以区分
     */
    @Override
    public void onViewClick(int position, int viewtype) {
        switch (viewtype) {
            case 1://赞
                Toast.makeText(NestingActivity.this, "赞-position>>" + position, Toast.LENGTH_SHORT).show();
                break;
            case 2://评论
                Toast.makeText(NestingActivity.this, "评论-position>>" + position, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * item点击事件
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        Toast.makeText(NestingActivity.this, "点击-position>>" + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * item长按事件
     *
     * @param position
     */
    @Override
    public void onItemLongClick(int position) {
        Toast.makeText(NestingActivity.this, "长按-position>>" + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * 上拉加载
     *
     * @param view
     */
    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InfoBean info = new InfoBean();
                info.setText("更多更多更多更多更多更多更多更多更多更多更多更多更多更多更多更多更多");
                mDatas.add(info); // 添加到最后面，然后刷新数据
                infoAdapter.notifyDataSetChanged();
                recyclerView.onFooterRefreshComplete();
            }
        }, 1500);
    }

    /**
     * 下拉刷新
     *
     * @param view
     */
    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InfoBean info = new InfoBean();
                info.setText("新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增");
                mDatas.add(0, info); // 添加到最顶部，刷新数据
                infoAdapter.notifyDataSetChanged();
                recyclerView.onHeaderRefreshComplete();
            }
        }, 1500);
    }

    /****************************************************后面是模拟数据，不用看了，太长了***********************************************************************************************/

    private void longLongLongData() {
        // 01
        List<Object> imageList01 = new ArrayList<>();
        imageList01.add("http://avatar.csdn.net/3/B/9/1_baiyuliang2013.jpg");
        imageList01.add("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=a22d53b052fbb2fb2b2b5f127f482043/ac345982b2b7d0a2f7375f70ccef76094a369a65.jpg");
        imageList01.add("https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=57c485df7cec54e75eec1d1e893a9bfd/241f95cad1c8a786bfec42ef6009c93d71cf5008.jpg");
        imageList01.add("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg");
        imageList01.add("https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=71cd4229be014a909e3e41bd99763971/472309f7905298221dd4c458d0ca7bcb0b46d442.jpg");
        imageList01.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1564533037,3918553373&fm=116&gp=0.jpg");

        InfoBean info1 = new InfoBean();
        info1.setText("TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT");
        info1.setImgList(imageList01);
        mDatas.add(info1);

        // 02
        List<Object> imageList02 = new ArrayList<>();
        imageList02.add("http://ofydu65mj.bkt.clouddn.com/QINIU816771488967981465.jpg");
        imageList02.add("http://ofydu65mj.bkt.clouddn.com/QINIU816771487666232861.jpg");
        imageList02.add("http://ofydu65mj.bkt.clouddn.com/QINIU816771487928557012.jpg");
        imageList02.add("http://ofydu65mj.bkt.clouddn.com/QINIU816811489199232197.jpg");
        imageList02.add("http://img5.imgtn.bdimg.com/it/u=1241400971,487894938&fm=23&gp=0.jpg");
        imageList02.add("http://ofydu65mj.bkt.clouddn.com/QINIU816771487928557012.jpg");
        imageList02.add("http://img2.imgtn.bdimg.com/it/u=2433730207,1999628675&fm=11&gp=0.jpg");
        imageList02.add("http://img5.imgtn.bdimg.com/it/u=951310838,1609291065&fm=11&gp=0.jpg");
        imageList02.add("http://img0.imgtn.bdimg.com/it/u=4170356278,3543262212&fm=23&gp=0.jpg");

        InfoBean info2 = new InfoBean();
        info2.setText("TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT");
        info2.setImgList(imageList02);
        mDatas.add(info2);

        // 03
        List<Object> imageList03 = new ArrayList<>();
        imageList03.add("http://img1.imgtn.bdimg.com/it/u=2679851752,3059261011&fm=23&gp=0.jpg");
        imageList03.add("http://img4.imgtn.bdimg.com/it/u=2660954522,1217255966&fm=23&gp=0.jpg");
        imageList03.add("http://img5.imgtn.bdimg.com/it/u=3012363796,4184788121&fm=11&gp=0.jpg");
        imageList03.add("http://img4.imgtn.bdimg.com/it/u=1745371451,1894784999&fm=23&gp=0.jpg");
        imageList03.add("http://img4.imgtn.bdimg.com/it/u=1919490862,311696022&fm=11&gp=0.jpg");
        imageList03.add("http://img2.imgtn.bdimg.com/it/u=2795383773,1437486659&fm=11&gp=0.jpg");
        imageList03.add("http://img3.imgtn.bdimg.com/it/u=2406727629,872122866&fm=23&gp=0.jpg");
        imageList03.add("http://img4.imgtn.bdimg.com/it/u=1860307276,2612987848&fm=23&gp=0.jpg");
        imageList03.add("http://img1.imgtn.bdimg.com/it/u=228946408,3395999961&fm=23&gp=0.jpg");

        InfoBean info3 = new InfoBean();
        info3.setText("TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT");
        info3.setImgList(imageList03);
        mDatas.add(info3);

        // 04
        List<Object> imageList04 = new ArrayList<>();
        imageList04.add("http://img3.imgtn.bdimg.com/it/u=1072328066,261279349&fm=11&gp=0.jpg");
        imageList04.add("http://img4.imgtn.bdimg.com/it/u=664065290,1135591436&fm=23&gp=0.jpg");
        imageList04.add("http://img1.imgtn.bdimg.com/it/u=248172175,2864510088&fm=11&gp=0.jpg");
        imageList04.add("http://img1.imgtn.bdimg.com/it/u=195439178,3811053557&fm=23&gp=0.jpg");
        imageList04.add("http://img2.imgtn.bdimg.com/it/u=2568462839,1744660675&fm=11&gp=0.jpg");
        imageList04.add("http://img0.imgtn.bdimg.com/it/u=1130084733,3258893011&fm=23&gp=0.jpg");
        imageList04.add("http://img0.imgtn.bdimg.com/it/u=3850906771,3420306297&fm=23&gp=0.jpg");
        imageList04.add("http://img2.imgtn.bdimg.com/it/u=2306853928,2618211896&fm=23&gp=0.jpg");
        imageList04.add("http://img3.imgtn.bdimg.com/it/u=61840435,2866388648&fm=23&gp=0.jpg");

        InfoBean info4 = new InfoBean();
        info4.setText("TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT");
        info4.setImgList(imageList04);
        mDatas.add(info4);

        // 05
        List<Object> imageList05 = new ArrayList<>();
        imageList05.add("http://img5.imgtn.bdimg.com/it/u=512523417,2352909290&fm=23&gp=0.jpg");
        imageList05.add("http://img3.imgtn.bdimg.com/it/u=3779408348,3918767693&fm=23&gp=0.jpg");
        imageList05.add("http://img4.imgtn.bdimg.com/it/u=1886697224,2844144924&fm=23&gp=0.jpg");
        imageList05.add("http://img2.imgtn.bdimg.com/it/u=2461291218,2449566402&fm=11&gp=0.jpg");
        imageList05.add("http://img4.imgtn.bdimg.com/it/u=2846031622,217041228&fm=23&gp=0.jpg");
        imageList05.add("http://img1.imgtn.bdimg.com/it/u=3726940589,1500120594&fm=23&gp=0.jpg");
        imageList05.add("http://img5.imgtn.bdimg.com/it/u=3397208567,100081482&fm=23&gp=0.jpg");
        imageList05.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1880243990,2577219667&fm=23&gp=0.jpg");
        imageList05.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2010091340,2250117520&fm=23&gp=0.jpg");

        InfoBean info5 = new InfoBean();
        info5.setText("TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT");
        info5.setImgList(imageList05);
        mDatas.add(info5);

        // 06
        List<Object> imageList06 = new ArrayList<>();
        imageList06.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3626544658,2923146622&fm=23&gp=0.jpg");
        imageList06.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3661492776,1943453868&fm=23&gp=0.jpg");
        imageList06.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3698023755,2775567896&fm=23&gp=0.jpg");
        imageList06.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=743049896,975136860&fm=23&gp=0.jpg");
        imageList06.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2541908264,2366399512&fm=23&gp=0.jpg");
        imageList06.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=219685084,3152177074&fm=23&gp=0.jpg");
        imageList06.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2999145994,565443483&fm=23&gp=0.jpg");
        imageList06.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3458204791,212164356&fm=23&gp=0.jpg");
        imageList06.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3324973495,2883736827&fm=23&gp=0.jpg");

        InfoBean info6 = new InfoBean();
        info6.setText("TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT");
        info6.setImgList(imageList06);
        mDatas.add(info6);

        // 07
        List<Object> imageList07 = new ArrayList<>();
        imageList07.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4113510133,462024918&fm=23&gp=0.jpg");
        imageList07.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1681175678,15923432&fm=23&gp=0.jpg");
        imageList07.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=35611542,1995343331&fm=11&gp=0.jpg");
        imageList07.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=649302024,3079708172&fm=23&gp=0.jpg");
        imageList07.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=131553455,1995684945&fm=23&gp=0.jpg");
        imageList07.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=707749577,2230008698&fm=23&gp=0.jpg");
        imageList07.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3437853011,465546580&fm=23&gp=0.jpg");
        imageList07.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=656357005,1865812798&fm=23&gp=0.jpg");
        imageList07.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=976542000,2610984256&fm=23&gp=0.jpg");

        InfoBean info7 = new InfoBean();
        info7.setText("TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT");
        info7.setImgList(imageList07);
        mDatas.add(info7);

        // 08
        List<Object> imageList08 = new ArrayList<>();
//        imageList08.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1279911946,1587331600&fm=23&gp=0.jpg");
        imageList08.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2989928569,1906605590&fm=23&gp=0.jpg");
        imageList08.add("http://img1.imgtn.bdimg.com/it/u=3540477897,3161180246&fm=15&gp=0.jpg"); // 动态
        imageList08.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2560916989,1452257396&fm=206&gp=0.jpg");// 动态
        imageList08.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1583624538,502028945&fm=23&gp=0.jpg");
        imageList08.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3983575162,1853828180&fm=23&gp=0.jpg");
        imageList08.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2285850890,1799327873&fm=23&gp=0.jpg ");
        imageList08.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3968721516,1729538557&fm=23&gp=0.jpg");
        imageList08.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1939795378,301004710&fm=23&gp=0.jpg");
        imageList08.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2048899080,1055976232&fm=23&gp=0.jpg");

        InfoBean info8 = new InfoBean();
        info8.setText("TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT");
        info8.setImgList(imageList08);
        mDatas.add(info8);


    }
}
