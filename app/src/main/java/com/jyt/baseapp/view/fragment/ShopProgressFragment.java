package com.jyt.baseapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jyt.baseapp.R;
import com.jyt.baseapp.view.widget.AppendItem;
import com.jyt.baseapp.view.widget.ProgressLine;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author LinWei on 2017/11/2 13:54
 */
public class ShopProgressFragment extends BaseFragment {
    @BindView(R.id.pl_measure)
    ProgressLine mPlMeasure;
    @BindView(R.id.pl_offer)
    ProgressLine mPlOffer;
    @BindView(R.id.pl_approval)
    ProgressLine mPlApproval;
    @BindView(R.id.pl_confirm)
    ProgressLine mPlConfirm;
    @BindView(R.id.pl_stocking)
    ProgressLine mPlStocking;
    @BindView(R.id.pl_logistics)
    ProgressLine mPlLogistics;
    @BindView(R.id.pl_construction)
    ProgressLine mPlConstruction;
    @BindView(R.id.pl_complete)
    ProgressLine mPlComplete;
    @BindView(R.id.pl_settlement)
    ProgressLine mPlSettlement;
    Unbinder unbinder;
    private AppendItem at_Measure;
    private AppendItem at_Measured;
    private AppendItem at_Design;
    private AppendItem at_Designing;
    private AppendItem at_Designed;
    private AppendItem at_Offer;
    private AppendItem at_Offered;
    private AppendItem at_Approval;
    private AppendItem at_Approvaled;
    private AppendItem at_Confirm;
    private AppendItem at_Confirmed;
    private AppendItem at_BudgetConfirm;
    private AppendItem at_Paper1;
    private AppendItem at_Paper2;
    private AppendItem at_Paper3;
    private AppendItem at_Paper4;
    private AppendItem at_Paper5;
    private AppendItem at_Paper6;
    private AppendItem at_Material1;
    private AppendItem at_Material2;
    private AppendItem at_Material3;
    private AppendItem at_Material4;
    private AppendItem at_Material5;
    private AppendItem at_Material6;
    private AppendItem at_Material7;
    private AppendItem at_Logistics1;
    private AppendItem at_Logistics2;
    private AppendItem at_Logistics3;
    private AppendItem at_Logistics4;
    private AppendItem at_Construction;
    private AppendItem at_Complete;
    private AppendItem at_Settlement1;
    private AppendItem at_Settlement2;
    private AppendItem at_Settlement3;
    private AppendItem at_Settlement4;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopprogress;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initMeasure();
        initOffer();
        initApproval();
        initConfirm();
        initStock();
        initLogistics();
        initConstruction();
        initComplete();
        initSettlement();
    }

    private void init() {
        at_Measure = new AppendItem(getActivity());
        at_Measured = new AppendItem(getActivity());

        at_Design = new AppendItem(getActivity());
        at_Designing = new AppendItem(getActivity());
        at_Designed = new AppendItem(getActivity());
        at_Offer = new AppendItem(getActivity());
        at_Offered = new AppendItem(getActivity());

        at_Approval = new AppendItem(getActivity());
        at_Approvaled = new AppendItem(getActivity());

        at_Confirm = new AppendItem(getActivity());
        at_Confirmed = new AppendItem(getActivity());

        at_BudgetConfirm = new AppendItem(getActivity());
        at_Paper1 = new AppendItem(getActivity());
        at_Paper2 = new AppendItem(getActivity());
        at_Paper3 = new AppendItem(getActivity());
        at_Paper4 = new AppendItem(getActivity());
        at_Paper5 = new AppendItem(getActivity());
        at_Paper6 = new AppendItem(getActivity());
        at_Material1 = new AppendItem(getActivity());
        at_Material2 = new AppendItem(getActivity());
        at_Material3 = new AppendItem(getActivity());
        at_Material4 = new AppendItem(getActivity());
        at_Material5 = new AppendItem(getActivity());
        at_Material6 = new AppendItem(getActivity());
        at_Material7 = new AppendItem(getActivity());

        at_Logistics1 = new AppendItem(getActivity());
        at_Logistics2 = new AppendItem(getActivity());
        at_Logistics3 = new AppendItem(getActivity());
        at_Logistics4 = new AppendItem(getActivity());

        at_Construction = new AppendItem(getActivity());

        at_Complete = new AppendItem(getActivity());

        at_Settlement1 = new AppendItem(getActivity());
        at_Settlement2 = new AppendItem(getActivity());
        at_Settlement3 = new AppendItem(getActivity());
        at_Settlement4 = new AppendItem(getActivity());
    }

    private void initMeasure() {
        at_Measure.setTv_msg("测量中");
        at_Measured.setTv_msg("测量完毕");
        mPlMeasure.addAppendItem(at_Measure);
        mPlMeasure.addAppendItem(at_Measured);
    }

    private void initOffer(){
        at_Design.setTv_msg("待设计");
        at_Design.setNext(false);
        at_Designing.setTv_msg("设计中");
        at_Designing.setNext(false);
        at_Designed.setTv_msg("设计完毕");
        at_Offer.setTv_msg("待报价");
        at_Offer.setNext(false);
        at_Offered.setTv_msg("报价完毕");
        at_Offered.setNext(false);
        mPlOffer.addAppendItem(at_Design);
        mPlOffer.addAppendItem(at_Designing);
        mPlOffer.addAppendItem(at_Designed);
        mPlOffer.addAppendItem(at_Offer);
        mPlOffer.addAppendItem(at_Offered);
    }

    private void initApproval(){
        at_Approval.setTv_msg("待客户审批");
        at_Approval.setNext(false);
        at_Approvaled.setTv_msg("客户已审批");
        mPlApproval.addAppendItem(at_Approval);
        mPlApproval.addAppendItem(at_Approvaled);
    }

    private void initConfirm(){
        at_Confirm.setTv_msg("待店主确认");
        at_Confirmed.setTv_msg("店主已确认");
        mPlConfirm.addAppendItem(at_Confirm);
        mPlConfirm.addAppendItem(at_Confirmed);
    }

    private void initStock(){
        at_BudgetConfirm.setTv_msg("预算确认下单");
        at_BudgetConfirm.setNext(false);
        at_Paper1.setTv_msg("待下图纸");
        at_Paper1.setNext(false);
        at_Paper2.setTv_msg("图纸下单");
        at_Paper2.setNext(false);
        at_Paper3.setTv_msg("待审图纸");
        at_Paper4.setTv_msg("已审图纸");
        at_Paper5.setTv_msg("待预算复核图纸");
        at_Paper5.setNext(false);
        at_Paper6.setTv_msg("预算已复核纸");
        at_Paper6.setNext(false);
        at_Material1.setTv_msg("待⽣产招牌");
        at_Material2.setTv_msg("待下材料单");
        at_Material2.setNext(false);
        at_Material3.setTv_msg("待审材料单");
        at_Material4.setTv_msg("已审材料单");
        at_Material5.setTv_msg("待备料");
        at_Material5.setNext(false);
        at_Material6.setTv_msg("钢挂已完成");
        at_Material6.setNext(false);
        at_Material7.setTv_msg("所有材料已打包");
        at_Material7.setEditor();
        mPlStocking.addAppendItem(at_BudgetConfirm);
        mPlStocking.addAppendItem(at_Paper1);
        mPlStocking.addAppendItem(at_Paper2);
        mPlStocking.addAppendItem(at_Paper3);
        mPlStocking.addAppendItem(at_Paper4);
        mPlStocking.addAppendItem(at_Paper5);
        mPlStocking.addAppendItem(at_Paper6);
        mPlStocking.addAppendItem(at_Material1);
        mPlStocking.addAppendItem(at_Material2);
        mPlStocking.addAppendItem(at_Material3);
        mPlStocking.addAppendItem(at_Material4);
        mPlStocking.addAppendItem(at_Material5);
        mPlStocking.addAppendItem(at_Material6);
        mPlStocking.addAppendItem(at_Material7);
    }

    private void initLogistics(){
        at_Logistics1.setTv_msg("待发货");
        at_Logistics1.setEditor();
        at_Logistics2.setTv_msg("已发货");
        at_Logistics2.setEditor();
        at_Logistics3.setTv_msg("货到待施工");
        at_Logistics3.setEditor();
        at_Logistics4.setTv_msg("安排施工人员完毕");
        at_Logistics4.setEditor();
        mPlLogistics.addAppendItem(at_Logistics1);
        mPlLogistics.addAppendItem(at_Logistics2);
        mPlLogistics.addAppendItem(at_Logistics3);
        mPlLogistics.addAppendItem(at_Logistics4);
    }

    private void initConstruction(){
        at_Construction.setTv_msg("施工中");
        at_Construction.setEditor();
        mPlConstruction.addAppendItem(at_Construction);
    }

    private void initComplete(){
        at_Complete.setTv_msg("施工完毕");
        at_Complete.setEditor();
        mPlComplete.addAppendItem(at_Complete);
    }

    private void initSettlement(){
        at_Settlement1.setTv_msg("预算审核照⽚并已回访");
        at_Settlement1.setNext(false);
        at_Settlement2.setTv_msg("待寄报销资料");
        at_Settlement2.setNext(false);
        at_Settlement3.setTv_msg("已寄报销资料");
        at_Settlement3.setNext(false);
        at_Settlement4.setTv_msg("已收款");
        at_Settlement4.setNext(false);
        mPlSettlement.addAppendItem(at_Settlement1);
        mPlSettlement.addAppendItem(at_Settlement2);
        mPlSettlement.addAppendItem(at_Settlement3);
        mPlSettlement.addAppendItem(at_Settlement4);
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
