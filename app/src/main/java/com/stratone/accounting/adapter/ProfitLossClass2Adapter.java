package com.stratone.accounting.adapter;
import com.stratone.accounting.model.CashFlowDetail;
import com.stratone.accounting.model.CashFlowHeader;
import com.stratone.accounting.model.ProfitLossClass2;
import com.stratone.accounting.model.ProfitLossClass3;

import java.util.HashMap;
import java.util.List;

public class ProfitLossClass2Adapter {
    public static HashMap<ProfitLossClass3, List<ProfitLossClass2>> getData(List<ProfitLossClass3> profitLossClass3s) {
        HashMap<ProfitLossClass3, List<ProfitLossClass2>> expandableListDetail = new HashMap<ProfitLossClass3, List<ProfitLossClass2>>();
        List<ProfitLossClass2> profitLossClass2s;
        for (int i = 0;i < profitLossClass3s.size();i++) {
            profitLossClass2s = profitLossClass3s.get(i).getData();
            expandableListDetail.put(profitLossClass3s.get(i),profitLossClass2s);
        }
        return expandableListDetail;
    }
}
