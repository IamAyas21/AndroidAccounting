package com.stratone.accounting.adapter;
import com.stratone.accounting.model.NeracaClass2;
import com.stratone.accounting.model.NeracaClass3;
import com.stratone.accounting.model.ProfitLossClass2;
import com.stratone.accounting.model.ProfitLossClass3;

import java.util.HashMap;
import java.util.List;

public class NeracaClass2Adapter {
    public static HashMap<NeracaClass3, List<NeracaClass2>> getData(List<NeracaClass3> neracaClass3s) {
        HashMap<NeracaClass3, List<NeracaClass2>> expandableListDetail = new HashMap<NeracaClass3, List<NeracaClass2>>();
        List<NeracaClass2> neracaClass2s;
        for (int i = 0;i < neracaClass3s.size();i++) {
            neracaClass2s = neracaClass3s.get(i).getData();
            expandableListDetail.put(neracaClass3s.get(i),neracaClass2s);
        }
        return expandableListDetail;
    }
}
